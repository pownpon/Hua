package com.pownpon.ui.smartrefresh.listener;

import android.content.Context;

import androidx.annotation.NonNull;

import com.pownpon.ui.smartrefresh.api.RefreshHeader;
import com.pownpon.ui.smartrefresh.api.RefreshLayout;

/**
 * 默认Header创建器
 * Created by scwang on 2018/1/26.
 */
public interface DefaultRefreshHeaderCreator {
    @NonNull
    RefreshHeader createRefreshHeader(@NonNull Context context, @NonNull RefreshLayout layout);
}
