package com.pownpon.picture.picker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pownpon.picture.R;
import com.pownpon.picture.util.PictureLoader;
import com.pownpon.picture.util.PictureUtil;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.security.Permission;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PickPictureActivity extends AppCompatActivity implements View.OnClickListener {

    private final int RequestPermissionCode = 99;
    private final String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};

    private RecyclerView rvPicture, rvDir;
    private ImageView ivBack, ivChoose;
    private TextView tvTitle, tvSure, tvDir, tvChoose, tvView;
    private AdapterPicture mAdapterPicture;
    private Map<String, ArrayList<BeanPicture>> mPics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_picture);
        findView();
        initView();
        checkPermission(true);
    }

    /**
     * 检查权限
     */
    private void checkPermission(boolean request) {
        if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this, permissions[0])
                || PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this, permissions[1])) {
            if (request) {
                ActivityCompat.requestPermissions(this, permissions, RequestPermissionCode);
            } else {
                Toast.makeText(this, "您没有授权", Toast.LENGTH_SHORT).show();
            }
        } else {
            getPictureData();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == RequestPermissionCode){
            checkPermission(false);
        }
    }

    /**
     * 获取图片数据
     */
    private void getPictureData() {
        mPics = PictureUtil.queryPictureBean(this);
        if (null == mPics || mPics.size() <= 0) {
            Toast.makeText(this, "没有图片哦", Toast.LENGTH_SHORT).show();
            tvTitle.postDelayed(
                    new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    }, 500);
            return;
        }
        changePicList(PictureUtil.KeyAll);
    }

    private void changePicList(String key) {
        mAdapterPicture.refresh(mPics.get(key));
    }

    @Override
    public void onClick(View v) {
        if (v == ivBack) {
            finish();
        } else if (v == tvView) {
            Intent intentView = new Intent(this, ViewPictureActivity.class);
            intentView.putParcelableArrayListExtra(ViewPictureActivity.INTENT_KEY_PICS, mPics.get(PictureUtil.KeyAll));
            startActivity(intentView);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PictureLoader.cancel(null);
    }

    /**
     * 控件初始化
     */
    private void initView() {
        rvDir.setVisibility(View.GONE);

        ivBack.setOnClickListener(this);
        tvSure.setOnClickListener(this);
        tvDir.setOnClickListener(this);
        ivChoose.setOnClickListener(this);
        tvChoose.setOnClickListener(this);
        tvView.setOnClickListener(this);

        if (null == rvPicture.getAdapter()) {
            mAdapterPicture = new AdapterPicture(this);
            rvPicture.setAdapter(mAdapterPicture);
        }
        if (null == mAdapterPicture) {
            mAdapterPicture = (AdapterPicture) rvPicture.getAdapter();
        }

        if (null == rvPicture.getLayoutManager()) {
            GridLayoutManager manager = new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false);
            rvPicture.setLayoutManager(manager);
        }
        if (rvPicture.getItemDecorationCount() <= 0) {
            rvPicture.addItemDecoration(new ItemPicutreDecoration(3));
        }
        if (null == rvDir.getAdapter()) {
            rvDir.setAdapter(null);
        }
        if (null == rvDir.getLayoutManager()) {
            LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            rvDir.setLayoutManager(manager);
        }


    }

    /**
     * 查找控件
     */
    private void findView() {
        rvPicture = findViewById(R.id.rv_picture_act_pick_picture);
        rvDir = findViewById(R.id.rv_dir_act_pick_picture);
        ivBack = findViewById(R.id.iv_back_act_pick_picture);
        ivChoose = findViewById(R.id.iv_choose_act_pick_picture);
        tvTitle = findViewById(R.id.tv_title_act_pick_picture);
        tvSure = findViewById(R.id.tv_sure_act_pick_picture);
        tvDir = findViewById(R.id.tv_dir_act_pick_picture);
        tvChoose = findViewById(R.id.tv_choose_act_pick_picture);
        tvView = findViewById(R.id.tv_view_act_pick_picture);
    }

}