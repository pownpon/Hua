package com.pownpon.picture.picker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pownpon.picture.R;
import com.pownpon.picture.util.PictureUtil;

import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PickPictureActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView rvPicture, rvDir;
    private ImageView ivBack, ivChoose;
    private TextView tvTitle, tvSure, tvDir, tvChoose, tvView;
    private AdapterPicture mAdapterPicture;
    private Map<String, List<String>> mPics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_picture);
        findView();
        initView();
        tvTitle.post(new Runnable() {
            @Override
            public void run() {
                getPictureData();
            }
        });
    }

    /**
     * 获取图片数据
     */
    private void getPictureData() {
        mPics = PictureUtil.queryPicture(this);
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
        List<BeanPicture> data = new ArrayList<>();
        for (String item : mPics.get(key)) {
            data.add(new BeanPicture(item));
        }
        mAdapterPicture.refresh(data);
    }

    @Override
    public void onClick(View v) {

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