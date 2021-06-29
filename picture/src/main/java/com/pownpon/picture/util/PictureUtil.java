package com.pownpon.picture.util;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorSpace;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.ExifInterface;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.collection.LruCache;

import com.pownpon.picture.picker.BeanPicture;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import libcore.io.DiskLruCache;

/**
 * Copyright (C), 2021-2030, XXX有限公司
 * FileName: PictureUtil
 * Author: HUA
 * Date: 2021/6/23 10:59
 * Description:
 * History:
 */
public class PictureUtil {

    public static final String KeyAll = "所有图片";


    /**
     * 获取本机图片信息
     *
     * @param context
     * @return
     */
    public static Map<String, ArrayList<BeanPicture>> queryPictureBean(Context context) {
        if (null == context) {
            return null;
        }
        ContentResolver cr = context.getContentResolver();
        if (null == cr) {
            return null;
        }
        //查询uri
        Uri qUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        //查询的列
        String[] qColumn = {MediaStore.Images.ImageColumns._ID, MediaStore.Images.ImageColumns.DATA,
                MediaStore.Images.ImageColumns.WIDTH, MediaStore.Images.ImageColumns.HEIGHT, MediaStore.Images.ImageColumns.ORIENTATION};
        //查询的条件
        String where = MediaStore.Images.ImageColumns.MIME_TYPE + " in (?,?,?,?)";
        //查询的条件值
        String[] whereValue = {"image/jpg", "image/jpeg", "image/png", "image/bmp"};
        //排序方式
        String order = MediaStore.Images.ImageColumns.DATE_ADDED + " DESC";
        Cursor cursor = cr.query(qUri, qColumn, where, whereValue, order);
        if (null == cursor) {
            return null;
        }

        int indexId = cursor.getColumnIndex(MediaStore.Images.ImageColumns._ID);
        int indexData = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        int indexWidth = cursor.getColumnIndex(MediaStore.Images.ImageColumns.WIDTH);
        int indexHeight = cursor.getColumnIndex(MediaStore.Images.ImageColumns.HEIGHT);
        int indexOrientation = cursor.getColumnIndex(MediaStore.Images.ImageColumns.ORIENTATION);

        Map<String, ArrayList<BeanPicture>> result = new HashMap<>();
        result.put(KeyAll, new ArrayList<>());
        while (cursor.moveToNext()) {
            int picId = cursor.getInt(indexId);
            String picData = cursor.getString(indexData);
            int picWidth = cursor.getInt(indexWidth);
            int picHeight = cursor.getInt(indexHeight);
            int picOrientation = cursor.getInt(indexOrientation);
            if (null == picData || picData.length() <= 0) {
                continue;
            }
            Uri picUri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, picId);

            BeanPicture beanPicture = new BeanPicture(picId, picData, picUri, picWidth, picHeight, picOrientation);

            result.get(KeyAll).add(beanPicture);

            char slash = File.separatorChar;
            //父路径
            int lastIndex = picData.lastIndexOf(slash);
            int lastSecIndex = picData.lastIndexOf(slash, lastIndex < 1 ? 0 : lastIndex - 1);
            String parentName = picData.substring(lastSecIndex + 1, lastIndex);
            if (null == parentName || parentName.length() <= 0) {
                continue;
            }

            if (!result.containsKey(parentName)) {
                result.put(parentName, new ArrayList<>());
            }
            result.get(parentName).add(beanPicture);
        }

        cursor.close();
        return result;
    }

    /**
     * 根据路径获取uri
     *
     * @return
     */
    public static Uri getPicUri(Context context, String picPath) {
        if (null == context || null == picPath || picPath.length() <= 0) {
            return null;
        }
        ContentResolver cr = context.getContentResolver();
        if (null == cr) {
            return null;
        }
        Uri qUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] qColumn = {MediaStore.Images.ImageColumns._ID};
        String qWhere = MediaStore.Images.ImageColumns.DATA + "=?";
        String[] qValue = {picPath};
        String qOrder = MediaStore.Images.ImageColumns._ID;
        Cursor cursor = cr.query(qUri, qColumn, qWhere, qValue, qOrder);
        if (null == cursor) {
            return null;
        }
        if (cursor.moveToFirst()) {
            int picId = cursor.getInt(0);
            if (picId > 0) {
                cursor.close();
                return ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, picId);
            }
        }
        cursor.close();
        return null;
    }

    /**
     * 获取bitmap
     *
     * @param context
     * @param beanPicture
     * @param width
     * @param height
     * @return
     */
    public static Bitmap compressBitmap(Context context, BeanPicture beanPicture, int width, int height) {
        if (null == context || null == beanPicture || width <= 0 || height <= 0) {
            return null;
        }
        try {
            if (null == beanPicture.getPictureUri()) {
                beanPicture.setPictureUri(getPicUri(context, beanPicture.getPicturePath()));
            }
            InputStream is = context.getContentResolver().openInputStream(beanPicture.getPictureUri());
            int ratio = getRatio(beanPicture.getPicWidth(), beanPicture.getPicHeight(), width, height);
            Bitmap bitmap = compressBitmap(is, ratio, beanPicture.getPictureConfig());
            if (0 != beanPicture.getPictureOrientation()) {
               return rotateBitmap(bitmap, beanPicture.getPictureOrientation());
            }
            return bitmap;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取压缩后的bitmap,在启用了分区存储的手机调用要注意测试
     *
     * @param filePath
     * @param width
     * @param height
     * @return
     */
    public static Bitmap compressBitmap(String filePath, int width, int height, Bitmap.Config config) {
        if (null == filePath || filePath.length() <= 0 || width <= 0 || height <= 0) {
            return null;
        }
        //加载边界数据
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inPreferredConfig = config;
        BitmapFactory.decodeFile(filePath, options);
        options.inSampleSize = getRatio(options.outWidth, options.outHeight, width, height);
        //真正加载所有数据
        options.inJustDecodeBounds = false;

        Bitmap result = BitmapFactory.decodeFile(filePath, options);
        return result;
    }

    /**
     * 获取压缩后的bitmap
     */
    public static Bitmap compressBitmap(InputStream stream, int ratio, Bitmap.Config config) {
        if (null == stream) {
            return null;
        }

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = config;
        options.inSampleSize = ratio;

        //真正加载所有数据
        options.inJustDecodeBounds = false;
        Bitmap result = BitmapFactory.decodeStream(stream, null, options);
        try {
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取压缩后的bitmap
     *
     * @param context
     * @param uri
     * @param width
     * @param height
     * @param config
     * @return
     */
    public static Bitmap compressBitmap(Context context, Uri uri, int width, int height, Bitmap.Config config) {
        if (null == context || null == uri || width <= 0 || height <= 0) {
            return null;
        }
        try {
            //加载边界
            InputStream is = context.getContentResolver().openInputStream(uri);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = config;
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(is, null, options);
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //真正加载所有数据
            options.inSampleSize = getRatio(options.outWidth, options.outHeight, width, height);
            options.inJustDecodeBounds = false;
            is = context.getContentResolver().openInputStream(uri);
            Bitmap result = BitmapFactory.decodeStream(is, null, options);
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 旋转bitmap
     *
     * @param source
     * @param degree
     * @return
     */
    public static Bitmap rotateBitmap(Bitmap source, int degree) {
        if (null == source || degree == 0) {
            return source;
        }
        long start = System.currentTimeMillis();
        Matrix matrix = new Matrix();
        matrix.setRotate(degree, source.getWidth(), source.getHeight());
        Bitmap rotate = Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
        Log.e("rotate", source.hashCode() + "__2222__" + (System.currentTimeMillis() - start));
        //source.recycle();
        return rotate;
    }

    /**
     * 获取压缩比例
     *
     * @param origalW
     * @param origalH
     * @param reqW
     * @param reqH
     * @return
     */
    public static int getRatio(int origalW, int origalH, int reqW, int reqH) {
        if (origalW <= 0 || origalH <= 0 || reqW <= 0 || reqH <= 0) {
            return 1;
        }
        if (origalW > reqW || origalH > reqH) {
            int wRadio = Math.round(origalW * 1.0f / reqW);
            int hRadio = Math.round(origalH * 1.0f / reqH);
            return Math.max(wRadio, hRadio);
        } else {
            return 1;
        }
    }

    /**
     * 获取图片的旋转角度
     *
     * @param path
     * @return
     */
    public static int getDegree(String path) {
        if (null == path || path.length() <= 0) {
            return 0;
        }
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, -1);
            if (-1 != orientation) {
                switch (orientation) {
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        return 90;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        return 180;
                    case ExifInterface.ORIENTATION_ROTATE_270:
                        return 270;

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
