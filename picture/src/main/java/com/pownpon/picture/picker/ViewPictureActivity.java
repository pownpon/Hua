package com.pownpon.picture.picker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pownpon.picture.R;
import com.pownpon.picture.util.PictureLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ViewPictureActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String INTENT_KEY_PICS = "viewpictureactivity_intent_key_pics";

    private ViewPager2 vpPicture;
    private ImageView ivBack, ivChoose;
    private TextView tvTitle, tvSure, tvDir, tvChoose, tvView;
    private AdapterViewPicture mAdapterPicture;
    private List<BeanPicture> mPics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_picture);
        findView();
        initView();
        initData();

        tvTitle.post(new Runnable() {
            @Override
            public void run() {
                mAdapterPicture.refresh(mPics);
            }
        });
    }

    private void initData() {
        Intent intent = getIntent();
        if (null == intent) {
            return;
        }

        mPics = intent.getParcelableArrayListExtra(INTENT_KEY_PICS);
        if (null == mPics || mPics.size() <= 0) {
            finish();
            return;
        }

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 控件初始化
     */
    private void initView() {

        ivBack.setOnClickListener(this);
        tvSure.setOnClickListener(this);
        tvDir.setOnClickListener(this);
        ivChoose.setOnClickListener(this);
        tvChoose.setOnClickListener(this);
        tvView.setOnClickListener(this);

        if (null == vpPicture.getAdapter()) {
            mAdapterPicture = new AdapterViewPicture(this);
            vpPicture.setAdapter(mAdapterPicture);
        }
        if (null == mAdapterPicture) {
            mAdapterPicture = (AdapterViewPicture) vpPicture.getAdapter();
        }

    }

    /**
     * 查找控件
     */
    private void findView() {
        vpPicture = findViewById(R.id.vp_picture_act_view_picture);
        ivBack = findViewById(R.id.iv_back_act_view_picture);
        ivChoose = findViewById(R.id.iv_choose_act_view_picture);
        tvTitle = findViewById(R.id.tv_title_act_view_picture);
        tvSure = findViewById(R.id.tv_sure_act_view_picture);
        tvDir = findViewById(R.id.tv_dir_act_view_picture);
        tvChoose = findViewById(R.id.tv_choose_act_view_picture);
        tvView = findViewById(R.id.tv_view_act_view_picture);
    }


}