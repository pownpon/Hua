package com.pownpon.picture.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.collection.LruCache;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import libcore.io.DiskLruCache;

/**
 * Copyright (C), 2021-2030, XXX有限公司
 * FileName: PictureMemory
 * Author: HUA
 * Date: 2021/6/23 17:05
 * Description:
 * History:
 */
public class PictureLoader {
    private static PictureLoader mInstance;

    public static synchronized PictureLoader getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new PictureLoader(context);
        }
        return mInstance;
    }

    private LruCache<String, Bitmap> lruCache;

    private DiskLruCache diskLruCache;

    private PictureLoader(Context context) {
        lruCache = new LruCache<String, Bitmap>((int) (Runtime.getRuntime().maxMemory() / 8)) {
            @Override
            protected int sizeOf(@NonNull String key, @NonNull Bitmap value) {
                if (null == value) {
                    return 0;
                }
                return value.getRowBytes() * value.getHeight();
            }
        };
        try {
            diskLruCache = DiskLruCache.open(getBitmapCacheDir(context), 1, 1, 20 * 1024 * 1024);
        } catch (IOException e) {
        }
    }

    /**
     * 获取bitmap对象
     * @param path
     * @return
     */
    public Bitmap get(String path) {
        if (null == path || path.length() <= 0) {
            return null;
        }
        String key = MD5(path);
        //内存缓存中获取
        Bitmap result = lruCache.get(key);
        if (null != result) {
            Log.e("111","mmmmmmm");
            return result;
        }
        //文件缓存中获取
        try {
            DiskLruCache.Snapshot snapshot = diskLruCache.get(key);

            if(null != snapshot){
                result  = BitmapFactory.decodeStream(snapshot.getInputStream(0));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (null != result) {
            //存入内存缓存
            lruCache.put(key,result);
            //返回结果
            Log.e("111","dddddd");
            return result;
        }
        //从网络加载或者从原始文件读取
        result = BitmapFactory.decodeFile(path);
        //存入内存
        lruCache.put(key,result);
        //存入文件缓存
        try {
            DiskLruCache.Editor editor =  diskLruCache.edit(key);
            result.compress(Bitmap.CompressFormat.JPEG,100,editor.newOutputStream(0));
            editor.commit();
            diskLruCache.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.e("111","ffffffffffffff");
        return result;
    }

    /**
     * 获取/创建 bitmap的缓存文件夹
     *
     * @param context
     * @return
     */
    public File getBitmapCacheDir(Context context) {
        String dirPath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
            dirPath = context.getExternalCacheDir().getPath();
        } else {
            dirPath = context.getCacheDir().getPath();
        }
        File dir = new File(dirPath, "bitmap");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir;
    }

    /**
     * MD5加密
     *
     * @param key
     * @return
     */
    public String MD5(String key) {
        String cacheKey;
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(key.getBytes());
            cacheKey = bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(key.hashCode());
        }
        return cacheKey;
    }

    private String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

}
