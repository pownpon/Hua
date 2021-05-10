package com.pownpon.hua.model.bean.response

import com.google.gson.annotations.SerializedName
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

    @SerializedName("ListAuctionP")
    val listAuctionP: List<Goods>?,
    @SerializedName("ListClass")
    val listClass: List<GoodsClass>?,
    @SerializedName("ClassTitle")
    val classTitle: String?
) :BaseResponse(), Serializable
