package com.pownpon.ui.smartrefresh.listener;


import androidx.annotation.NonNull;

import com.pownpon.ui.smartrefresh.api.RefreshLayout;

/**
 * 刷新监听器
 * Created by scwang on 2017/5/26.
 */
public interface OnRefreshListener {
    void onRefresh(@NonNull RefreshLayout refreshLayout);
}
