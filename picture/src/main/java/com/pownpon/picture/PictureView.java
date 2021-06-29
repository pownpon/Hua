package com.pownpon.picture;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.ExifInterface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.io.IOException;

/**
 * Copyright (C), 2021-2030, XXX有限公司
 * FileName: PitureView
 * Author: HUA
 * Date: 2021/6/26 16:18
 * Description:
 * History:
 */
public class PictureView extends View {

    private static final String TAG = "pictureview";

    public PictureView(Context context) {
        this(context, null, 0, 0);
    }

    public PictureView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0, 0);
    }

    public PictureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public PictureView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private String mPath;
    private Rect mRectView = new Rect(), mRectBitmap = new Rect();
    private BitmapRegionDecoder mDecoder;
    private BitmapFactory.Options mOptions = new BitmapFactory.Options();
    private int mBitmapWidth, mBitmapHeight, mViewWith, mViewHeight, mPicDegree;
    private float mRatio = 0f, mMaxRatio = 1.0f, mWhRatioBitmap;
    private Paint mPaint = new Paint();


    private void init(Context context) {
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e(TAG, "onMeasure");
        mViewWith = getMeasuredWidth();
        mViewHeight = getMeasuredHeight();
        setMaxRatio();
    }

    private float mDownX, mDownY, mLastX, mLastY;

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent event) {
//        float x = event.getX(), y = event.getY();
//        if (event.getAction() == MotionEvent.ACTION_MOVE && mRatio > 1) {
//
//            getParent().requestDisallowInterceptTouchEvent(true);
//
//        }
//        return super.dispatchTouchEvent(event);
//    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        float x = event.getX(), y = event.getY();
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                mDownX = x;
//                mDownY = y;
//                break;
//            case MotionEvent.ACTION_MOVE:
//                int offX = (int) (mLastX - x);
//                int offY = (int) (mLastY - y);
//                Log.e(TAG, "off_x_" + offX + "__y_" + offY);
//                if (offX > 2 || offY > 2) {
//                    int w = mRectBitmap.width();
//                    int h = mRectBitmap.height();
//                    mRectBitmap.offset(offX, offY);
//                    if (mRectBitmap.left < 0) {
//                        mRectBitmap.left = 0;
//                        mRectBitmap.right = w;
//                    } else if (mRectBitmap.right > mBitmapWidth) {
//                        mRectBitmap.right = mBitmapWidth;
//                        mRectBitmap.left = mBitmapWidth - w;
//                    }
//
//                    if (mRectBitmap.top < 0) {
//                        mRectBitmap.top = 0;
//                        mRectBitmap.bottom = h;
//                    } else if (mRectBitmap.bottom > mBitmapHeight) {
//                        mRectBitmap.bottom = mBitmapHeight;
//                        mRectBitmap.top = mBitmapHeight - h;
//                    }
//                    invalidate();
//                }
//                break;
//        }
//        mLastX = x;
//        mLastY = y;
//        return true;
//    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e(TAG, "onDraw");

        Log.e(TAG, "mRectBitmap__" + mRectBitmap.toString());
        Bitmap bitmap = mDecoder.decodeRegion(mRectBitmap, mOptions);
        if (mPicDegree == 0) {
            canvas.drawBitmap(bitmap, new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()), mRectView, mPaint);
        } else {
            Matrix matrix = new Matrix();
            matrix.mapRect(new RectF(mRectView),new RectF(0,0,bitmap.getHeight(),bitmap.getWidth()));
            float targetX, targetY;
            if (0 == mPicDegree %180) {
                targetX = bitmap.getHeight();
                targetY = bitmap.getWidth();
            } else {
                targetX = bitmap.getHeight();
                targetY = 0;
            }
            matrix.setRotate(mPicDegree, bitmap.getWidth() / 2, bitmap.getHeight() / 2);
            final float[] values = new float[9];
            matrix.getValues(values);

            float x1 = values[Matrix.MTRANS_X];
            float y1 = values[Matrix.MTRANS_Y];

            matrix.postTranslate(targetX - x1, targetY - y1);
            canvas.drawBitmap(bitmap,matrix,mPaint);

//            Bitmap rotateBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
//            bitmap.recycle();
//            canvas.drawBitmap(rotateBitmap, new Rect(0, 0, rotateBitmap.getWidth(), rotateBitmap.getHeight()), mRectView, mPaint);
        }

    }

    /**
     * 当缩放时
     *
     * @param ratio
     */
    private void onScale(float ratio) {
        Log.e(TAG, "onScale");
        if (mRatio == ratio) {
            return;
        }
        mRatio = ratio;
        //计算bitmap加载区域的大小
        if (mRatio < 1) {
            mRatio = 1;
        }
        if (mRatio > mMaxRatio) {
            mRatio = mMaxRatio;
        }
        mRectBitmap.set(0, 0, (int) (mBitmapWidth / mRatio), (int) (mBitmapHeight / mRatio));

        //计算显示区域的大小
        //显示以控件宽度为基准，即宽段不变
        int height = (int) (mRatio * mViewWith / mWhRatioBitmap);
        if (height > mViewHeight) {
            height = mViewHeight;
        }
        int top = (mViewHeight - height) / 2;
        mRectView.set(0, top, mViewWith, top + height);

        //计算加载bitmap时的压缩比
        int wRatio, hRatio;
        if (0 == mPicDegree % 180) {
            wRatio = Math.round(mRectBitmap.width() * 1.0f / mRectView.width());
            hRatio = Math.round(mRectBitmap.height() * 1.0f / mRectView.height());
        } else {
            wRatio = Math.round(mRectBitmap.height() * 1.0f / mRectView.width());
            hRatio = Math.round(mRectBitmap.width() * 1.0f / mRectView.height());
        }
        mOptions.inSampleSize = Math.max(wRatio, hRatio);
        Log.e(TAG, "ratio__" + mOptions.inSampleSize);
    }

    /**
     * 设置最大放大倍数,以及显示比例
     */
    private void setMaxRatio() {
        if (mViewHeight > 0 && mViewWith > 0 && mBitmapWidth > 0 && mBitmapHeight > 0) {
            //计算显示宽高比
            if (0 == mPicDegree % 180) {
                mWhRatioBitmap = mBitmapWidth * 1.0f / mBitmapHeight;
            } else {
                mWhRatioBitmap = mBitmapHeight * 1.0f / mBitmapWidth;
            }

            //计算最大放大比例
            float wRatio, hRatio;
            if (0 == mPicDegree % 180) {
                wRatio = mBitmapWidth * 1.0f / mViewWith;
                hRatio = mBitmapHeight * 1.0f / mViewHeight;
            } else {
                wRatio = mBitmapHeight * 1.0f / mViewWith;
                hRatio = mBitmapWidth * 1.0f / mViewHeight;
            }
            mMaxRatio = Math.max(wRatio, hRatio);
            if (mMaxRatio < 1) {
                mMaxRatio = 1.0f;
            }

            onScale(1.0f);
        }
    }

    /**
     * 获取图片的旋转角度
     *
     * @param path
     * @return
     */
    private int getPicDegree(String path) {
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

    public void setPath(String path) {
        Log.e(TAG, "setPath");
        if (null == path || path.length() <= 0) {
            return;
        }
        this.mPath = path;
        try {
            mDecoder = BitmapRegionDecoder.newInstance(path, false);
            mPicDegree = getPicDegree(path);
            Log.e(TAG, "picdegree__" + mPicDegree);
            mBitmapWidth = mDecoder.getWidth();
            mBitmapHeight = mDecoder.getHeight();

            Log.e(TAG, "bitmapw_" + mBitmapWidth + "__H__" + mBitmapHeight);

            setMaxRatio();

        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

    public String getPath() {
        return mPath;
    }
}
