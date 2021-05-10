package com.pownpon.hua.activity


import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pownpon.hua.R
import com.pownpon.hua.activity.base.BaseListActivity
import com.pownpon.hua.adapter.GoodsListAdapter
import com.pownpon.hua.adapter.base.BasePageDataAdapter
import com.pownpon.hua.model.bean.entity.Goods
import com.pownpon.hua.databinding.ActivityListGoodsBinding
import com.pownpon.hua.databinding.ItemGoodsBinding
import com.pownpon.hua.vm.VmGoods
import kotlinx.coroutines.flow.collectLatest

class ListGoodsActivity :
    BaseListActivity<Goods, ActivityListGoodsBinding, ItemGoodsBinding>() {

    private val vmGoods:VmGoods by viewModels<VmGoods> {
        ViewModelProvider.NewInstanceFactory()
    }

    override fun getTopLayoutId(): Int {
        return R.layout.activity_list_goods
    }

    override fun initTopLayout() {
        lifecycleScope.launchWhenCreated {
            vmGoods.goodsFlow .collectLatest {
                mAdapter.submitData(it)
            }
        }
        mVDBList.tvTest1.setOnClickListener {
            vmGoods.changParams( "字sdfsdfdsfds")
        }
        mVDBList.tvTest2.setOnClickListener {
            vmGoods.changParams("火")
        }
    }


    override fun initManager(): RecyclerView.LayoutManager {
        return LinearLayoutManager(ListGoodsActivity@ this)
    }

    override fun initAfterLogin() {

    }

    override fun initAdapter(): BasePageDataAdapter<Goods, ItemGoodsBinding> {
        return GoodsListAdapter(this)
    }

    override fun getTitleHeight(): Int = 100


}