package com.pownpon.hua.activity


import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pownpon.hua.R
import com.pownpon.hua.activity.base.BaseListActivity
import com.pownpon.hua.adapter.base.BasePageDataAdapter
import com.pownpon.hua.bean.entity.TestEntity
import com.pownpon.hua.databinding.ActivityListGoodsBinding
import com.pownpon.hua.databinding.ItemTestBinding
import com.pownpon.hua.vm.TestAdapter
import com.pownpon.hua.vm.VmTest
import kotlinx.coroutines.flow.collectLatest

class ListGoodsActivity :
    BaseListActivity<TestEntity, ActivityListGoodsBinding, ItemTestBinding>() {

    private val mVM by viewModels<VmTest> {
        ViewModelProvider.NewInstanceFactory()
    }

    override fun getTopLayoutId(): Int {
        return R.layout.activity_list_goods
    }

    override fun initTopLayout() {
        lifecycleScope.launchWhenCreated {
            mVM.x.collectLatest {
                mAdapter.submitData(it)
            }
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