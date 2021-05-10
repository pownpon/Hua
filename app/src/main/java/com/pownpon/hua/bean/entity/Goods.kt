package com.pownpon.hua.bean.entity

import java.io.Serializable

/**
 * Copyright (C), 2021-2030, XXX有限公司
 * FileName: Goods
 * Author: HUA
 * Date: 2021/5/10 10:21
 * Description: 物品详情
 * History:
 */
data class Goods(

    private var AutoId: Int = 0,
    private val AucId: String? = null,
    private val SellerId: String? = null,
    private val Seller: String? = null,
    private
    val AucType: Int = 0,
    private
    val AucClassId: Int = 0,
    private
    val ShopClassId: Int = 0,
    private
    val AucTitle: String? = null,
    private
    val StartTime: String? = null,
    private
    val EndTime: String? =
        null, private
    val AucNum: Int =
        0, private
    val AucSpec: String? =
        null, private
    val AucPro: String? =
        null, private
    val AucCity: String? =
        null, private
    val StartPrice: Double =
        0.0, private
    val ExtPrice: Double =
        0.0, private
    val ImmPrice: Double =
        0.0, private
    val AucHot: Int =
        0, private
    val TraPayMethod: Int =
        0, private
    val TraObject: Int =
        0, private
    val ChTraPrice: Double =
        0.0, private
    val DeTraPrice: Double =
        0.0, private
    val EmsTraPrice: Double =
        0.0, private
    val AucIP: String? =
        null, private
    val AucIsdel: Int =
        0, private
    val AucIsSell: Int =
        0, private
    val AucGoodsType: Int =
        0, private
    val AucIsEvacuate: Int =
        0, private
    val AucIsPay: Int =
        0, private
    val AucIsOrchid: Int =
        0, private
    val BigSrc: String? =
        null, private
    val MidSrc: String? =
        null, private
    val SmallSrc: String? =
        null, private
    val TopPrice: Double =
        0.0, private
    val PriceNum: Int =
        0, private
    val UserRate: Int =
        0, private
    val ExId: Int =
        0, private
    val AucHits: Int =
        0, private
    val GreatTime: String? =
        null, private
    val TopUserId: String? =
        null, private
    val IsReturnSer: Int =
        0, private
    val AucIsRepeat: Int =
        0, private
    val OrchidBigSort: Int =
        0, private
    val OrchidFloSort: Int =
        0, private
    val CancelTransNums: Int =
        0, private
    val IsPhonePub: Int =
        0,
    private val IsLive:Int =0
) : Serializable
