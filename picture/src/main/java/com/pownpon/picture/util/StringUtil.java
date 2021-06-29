package com.pownpon.picture.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Copyright (C), 2021-2030, XXX有限公司
 * FileName: StringUtil
 * Author: HUA
 * Date: 2021/6/29 10:53
 * Description:
 * History:
 */
public class StringUtil {

    /**
     * MD5加密
     *
     * @param key
     * @return
     */
    public static String MD5(String key) {
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

    private static String bytesToHexString(byte[] bytes) {
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
