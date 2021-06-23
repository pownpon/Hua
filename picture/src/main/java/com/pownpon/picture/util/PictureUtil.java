package com.pownpon.picture.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.collection.LruCache;

import java.io.File;
import java.io.IOException;
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
     * 获取本机存储的图片路径
     *
     * @param context
     * @return
     */
    public static Map<String, List<String>> queryPicture(Context context) {
        if (null == context) {
            return null;
        }
        ContentResolver cr = context.getContentResolver();
        //查询uri
        Uri qUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        //查询的列
        String[] qColumn = {MediaStore.Images.ImageColumns.DATA};
        //查询的条件
        String where = MediaStore.Images.ImageColumns.MIME_TYPE + " in (?,?,?,?)";
        //查询的条件值
        String[] whereValue = {"image/jpg", "image/jpeg", "image/png", "image/bmp"};
        //排序方式
        String order = MediaStore.Images.ImageColumns.DATA;
        Cursor cursor = cr.query(qUri, qColumn, where, whereValue, order);
        if (null == cursor) {
            return null;
        }

        Map<String, List<String>> result = new HashMap<>();
        result.put(KeyAll, new ArrayList<>());
        while (cursor.moveToNext()) {
            String picData = cursor.getString(0);
            if (null == picData || picData.length() <= 0) {
                continue;
            }
            result.get(KeyAll).add(picData);
            char slash = picData.charAt(0);
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
            result.get(parentName).add(picData);
        }

        cursor.close();
        return result;
    }

}
