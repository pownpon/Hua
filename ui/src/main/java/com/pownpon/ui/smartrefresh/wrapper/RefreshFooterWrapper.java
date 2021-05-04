package com.pownpon.ui.smartrefresh.wrapper;

import android.annotation.SuppressLint;
import android.view.View;

import com.pownpon.ui.smartrefresh.api.RefreshFooter;
import com.pownpon.ui.smartrefresh.simple.SimpleComponent;


/**
 * 刷新底部包装
 * Created by scwang on 2017/5/26.
 */
@SuppressLint("ViewConstructor")
public class RefreshFooterWrapper extends SimpleComponent implements RefreshFooter {

    public RefreshFooterWrapper(View wrapper) {
        super(wrapper);
    }

}
