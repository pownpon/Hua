package com.pownpon.picture.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.collection.LruCache;

import com.pownpon.picture.picker.BeanPicture;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.security.auth.callback.Callback;

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

    private static final String TAG = "PictureLoader";

    private static final Executor executorPic = Executors.newFixedThreadPool(3);

    private static final int DefauleWidth = 300;

    public interface PicCallBack {
        void onReuslt(boolean success, String path, Bitmap bitmap);
    }

    private Context mContext;
    private static LruCache<String, Bitmap> lruCache;

    private static DiskLruCache diskLruCache;

    private static final List<PictureAysncTack> mTasks = new ArrayList<>();

    public PictureLoader(Context context) {
        this.mContext = context;
        if (null == lruCache) {
            synchronized (executorPic) {
                if (null == lruCache) {
                    int memorySize = (int) (Runtime.getRuntime().maxMemory() / 8);
                    Log.e(TAG,"memorySize="+memorySize);
                    lruCache = new LruCache<String, Bitmap>(memorySize) {
                        @Override
                        protected int sizeOf(@NonNull String key, @NonNull Bitmap value) {
                            if (null == value) {
                                return 0;
                            }
                            return value.getByteCount();
                        }
                    };
                }
            }
        }
        if (null == diskLruCache) {
            synchronized (executorPic) {
                try {
                    int diskSize = (int) (Runtime.getRuntime().maxMemory() / 4);
                    Log.e(TAG,"diskSize="+diskSize);
                    diskLruCache = DiskLruCache.open(getBitmapCacheDir(context), 1, 1, diskSize);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 获取bitmap对象
     */
    public void get(String path, PicCallBack callBack, Integer... wh) {
        get(path, callBack, Bitmap.Config.RGB_565, wh);
    }

    /**
     * 获取bitmap对象
     */
    public void get(String path, PicCallBack callBack, Bitmap.Config config, Integer... wh) {
        if (null == callBack || null == path || path.length() <= 0) {
            return;
        }
        BeanPicture beanPicture = new BeanPicture(0, path, null, 0, 0, 0, false, config);
        get(beanPicture, callBack);
    }

    /**
     * 获取bitmap对象
     */
    public void get(BeanPicture beanPicture, PicCallBack callBack, Integer... wh) {
        if (null == callBack || null == beanPicture || null == beanPicture.getPicturePath() || beanPicture.getPicturePath().length() <= 0) {
            return;
        }
        //内存缓存中获取
        Bitmap result = getFromMemory(beanPicture.getPicturePath());
        if (null != result) {
            Log.e(TAG, "mmmmmmm");
            callBack.onReuslt(true, beanPicture.getPicturePath(), result);
            return;
        }
        PictureAysncTack picTack = new PictureAysncTack(beanPicture, callBack);
        mTasks.add(picTack);
        picTack.executeOnExecutor(executorPic, wh);
    }


    /**
     * 从内存缓存获取
     *
     * @param path
     * @return
     */
    public Bitmap getFromMemory(String path) {
        if (null == path || path.length() <= 0) {
            return null;
        }
        return lruCache.get(StringUtil.MD5(path));
    }

    /**
     * 存入内存缓存
     *
     * @param path
     * @param bitmap
     */
    public void putMemory(String path, Bitmap bitmap) {
        if (null == path || null == bitmap || path.length() <= 0) {
            return;
        }
        lruCache.put(StringUtil.MD5(path), bitmap);
    }

    /**
     * 从磁盘缓存获取
     *
     * @param path
     * @return
     */
    public Bitmap getFromDisk(String path) {
        if (null == path || path.length() <= 0) {
            return null;
        }
        //文件缓存中获取
        try {
            DiskLruCache.Snapshot snapshot = diskLruCache.get(StringUtil.MD5(path));

            if (null != snapshot) {
                return BitmapFactory.decodeStream(snapshot.getInputStream(0));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 存入磁盘缓存
     *
     * @param path
     * @param bitmap
     */
    public void putDisk(String path, Bitmap bitmap) {
        if (null == path || null == bitmap || path.length() <= 0) {
            return;
        }
        //存入文件缓存
        try {
            DiskLruCache.Editor editor = diskLruCache.edit(StringUtil.MD5(path));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, editor.newOutputStream(0));
            editor.commit();
            diskLruCache.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 移除bitmap缓存
     *
     * @param path
     */
    public void removeCache(String path) {
        if (null == path || path.length() <= 0) {
            return;
        }
        String key = StringUtil.MD5(path);
        lruCache.remove(key);
        try {
            diskLruCache.remove(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除所有bitmap缓存
     */
    public void deleteCache() {
        lruCache.evictAll();
        try {
            diskLruCache.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
     * 取消获取
     *
     * @param path
     */
    public static void cancel(String path) {
        if (null == path || path.length() <= 0) {
            for (PictureAysncTack task : mTasks) {
                task.cancel(true);
            }
        } else {
            for (PictureAysncTack task : mTasks) {
                if (path.equals(task.mBeanPicture.getPicturePath())) {
                    task.cancel(true);
                }
            }
        }
    }

    /**
     * 加载bitmap的任务类
     */
    private class PictureAysncTack extends AsyncTask<Integer, Object, Bitmap> {

        PicCallBack mCallBack;
        BeanPicture mBeanPicture;

        public PictureAysncTack(BeanPicture beanPicture, PicCallBack callback) {
            this.mBeanPicture = beanPicture;
            mCallBack = callback;
        }

        @Override
        protected Bitmap doInBackground(Integer... ints) {
            if (null == mBeanPicture || null == mBeanPicture.getPicturePath() || mBeanPicture.getPicturePath().length() <= 0) {
                return null;
            }
            if (null == mBeanPicture.getPictureConfig()) {
                mBeanPicture.setPictureConfig(Bitmap.Config.RGB_565);
            }
            //磁盘缓存
            Bitmap result = getFromDisk(mBeanPicture.getPicturePath());
            if (null != result) {
                //存入内存缓存
                putMemory(mBeanPicture.getPicturePath(), result);
                //返回结果
                Log.e(TAG, "ddddddddd");
                return result;
            }
            //从网络加载
            if (mBeanPicture.getPicturePath().toLowerCase().startsWith("http")) {
                return null;
            } else {//或者从原始文件读取
                int width, height;
                if (null == ints || ints.length <= 0) {
                    width = height = DefauleWidth;
                } else if (1 == ints.length) {
                    width = height = ints[0];
                } else {
                    width = ints[0];
                    height = ints[1];
                }
                result = PictureUtil.compressBitmap(mContext, mBeanPicture, width, height);
                if (null != result) {
                    //存入内存
                    putMemory(mBeanPicture.getPicturePath(), result);
                    //存入磁盘
                    putDisk(mBeanPicture.getPicturePath(), result);
                }
                Log.e(TAG, "fffffffffffff");

                return result;
            }
        }

        @Override
        protected void onCancelled(Bitmap bitmap) {
            Log.e(TAG, "onCancelled111__"+mBeanPicture.getPicturePath());
            mTasks.remove(PictureAysncTack.this);
            if (null != mCallBack) {
                mCallBack.onReuslt(false, mBeanPicture.getPicturePath(), bitmap);
            }
        }

        @Override
        protected void onCancelled() {
            Log.e(TAG, "onCancelled2222__"+mBeanPicture.getPicturePath());
            mTasks.remove(PictureAysncTack.this);
            if (null != mCallBack) {
                mCallBack.onReuslt(false, mBeanPicture.getPicturePath(), null);
            }
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            mTasks.remove(PictureAysncTack.this);
            if (null != mCallBack) {
                mCallBack.onReuslt(true, mBeanPicture.getPicturePath(), bitmap);
            }
        }
    }

}
