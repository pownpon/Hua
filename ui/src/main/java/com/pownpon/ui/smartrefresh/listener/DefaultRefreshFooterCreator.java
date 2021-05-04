package com.pownpon.ui.smartrefresh.listener;

import android.content.Context;

import androidx.annotation.NonNull;

import com.pownpon.ui.smartrefresh.api.RefreshFooter;
import com.pownpon.ui.smartrefresh.api.RefreshLayout;

/**
 * 默认Footer创建器
 * Created by scwang on 2018/1/26.
 */
public interface DefaultRefreshFooterCreator {
    @NonNull
    RefreshFooter createRefreshFooter(@NonNull Context context, @NonNull RefreshLayout layout);
}
