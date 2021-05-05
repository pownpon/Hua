package com.pownpon.hua.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.pownpon.hua.R
import com.pownpon.hua.activity.base.BaseActivity
import com.pownpon.hua.databinding.ActivityListGoodsBinding

class ListGoodsActivity : BaseActivity<ActivityListGoodsBinding>() {

    override fun getLayoutId(): Int {
        return R.layout.activity_list_goods
    }

    override fun initBeforeLogin() {

    }

    override fun initAfterLogin() {
    }

}