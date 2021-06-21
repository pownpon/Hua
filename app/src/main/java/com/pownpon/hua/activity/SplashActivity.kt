package com.pownpon.hua.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.pownpon.hua.R
import com.pownpon.hua.activity.base.TopBaseActivity
import com.pownpon.hua.databinding.ActivitySplashBinding
import com.pownpon.hua.global.lc
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : TopBaseActivity() {

    private lateinit var mVDB: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mVDB = DataBindingUtil.setContentView(this@SplashActivity, R.layout.activity_splash)
        goMainAct(800L)
    }

    /**
     * 进入主界面
     */
    private fun goMainAct(millis: Long) {
        lifecycleScope.launch {
            delay(millis)
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            overridePendingTransition(R.anim.translate_in_from_right, R.anim.translate_out_to_left)
            finish()
        }
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        //拦截返回键
        if(keyCode == KeyEvent.KEYCODE_BACK){
            return true
        }
        return super.onKeyDown(keyCode, event)
    }


}