package com.pownpon.hua.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.pownpon.hua.R
import com.pownpon.hua.activity.base.TopBaseActivity
import com.pownpon.hua.databinding.ActivityGgBinding
import com.pownpon.hua.global.Constans
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GgActivity : TopBaseActivity() {

    private lateinit var mVdb:ActivityGgBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mVdb = DataBindingUtil.setContentView(GgActivity@this,R.layout.activity_gg)

        startInterval(Constans.showGgTime)
    }

    /**
     * 开始倒计时
     */
    private fun startInterval(total:Long){
        if(total>0){
            mVdb.tvActGgText.text="${total/1000}后关闭"
            lifecycleScope.launch{
                delay(1200)
                startInterval(total-1000)
            }
        }else{
            finish()
        }
    }
}