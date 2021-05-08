package com.pownpon.hua.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pownpon.hua.R
import com.pownpon.hua.activity.base.BaseActivity
import com.pownpon.hua.activity.base.BaseListActivity
import com.pownpon.hua.adapter.base.BasePageDataAdapter
import com.pownpon.hua.adapter.base.BaseRecyclerAdapter
import com.pownpon.hua.bean.entity.TestEntity
import com.pownpon.hua.databinding.ActivityListGoodsBinding
import com.pownpon.hua.databinding.ItemTestBinding
import com.pownpon.hua.global.lc
import com.pownpon.hua.vm.TestAdapter
import com.pownpon.hua.vm.VmTest
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ListGoodsActivity :
    BaseListActivity<TestEntity, ActivityListGoodsBinding, ItemTestBinding>() {

    private lateinit var mVM: VmTest

    override fun getTopLayoutId(): Int {
        return R.layout.activity_list_goods
    }

    override fun initTopLayout() {
        mVM = VmTest()
        lifecycleScope.launchWhenCreated {
            mVM.x.collectLatest {
                mAdapter.submitData(it)
            }
        }
      mVDBList.tvTest1.setOnClickListener {
          lc("1111111111111111")
      }
    }


    override fun initManager(): RecyclerView.LayoutManager {
        return LinearLayoutManager(ListGoodsActivity@ this)
    }

    override fun initAfterLogin() {

    }

    override fun initAdapter(): BasePageDataAdapter<TestEntity, ItemTestBinding> {
        return TestAdapter(this)
    }

    override fun getTitleHeight(): Int = 100


}