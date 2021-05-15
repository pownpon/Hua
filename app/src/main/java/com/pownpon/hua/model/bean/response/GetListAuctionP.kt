package com.pownpon.hua.model.bean.response

import com.pownpon.hua.model.bean.base.BaseResponse
import com.pownpon.hua.model.bean.entity.Goods
import com.pownpon.hua.model.bean.entity.GoodsClass
import java.io.Serializable

/**
 * Copyright (C), 2021-2030, XXX有限公司
 * FileName: GetListAuctionP
 * Author: HUA
 * Date: 2021/5/10 10:49
 * Description:
 * History:
 */
data class GetListAuctionP(
    val ListAuctionP: List<Goods>?,
    val ListClass: List<GoodsClass>?,
    val ClassTitle: String?
) : BaseResponse(), Serializable
