package com.pownpon.hua.model.bean.entity

import com.pownpon.hua.model.bean.base.BaseEntity
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
    val AutoId : Int,
    val AucId : String?,
    val SellerId : String?,
    val Seller : String?,
    val AucType : Int,
    val AucClassId : Int,
    val ShopClassId : Int,
    val AucTitle : String?,
    val StartTime : String?,
    val EndTime: String?,
    val AucNum : Int,
    val AucSpec : String?,
    val AucPro : String?,
    val AucCity : String?,
    val StartPrice : Double,
    val ExtPrice : Double,
    val ImmPrice : Double,
    val AucHot : Int,
    val TraPayMethod : Int,
    val TraObject : Int,
    val ChTraPrice : Double,
    val DeTraPrice : Double,
    val EmsTraPrice : Double,
    val AucIP : String?,
    val AucIsdel : Int,
    val AucIsSell : Int,
    val AucGoodsType : Int,
    val AucIsEvacuate : Int,
    val AucIsPay : Int,
    val AucIsOrchid : Int,
    val BigSrc : String?,
    val MidSrc : String?,
    val SmallSrc : String?,
    val TopPrice : Double,
    val PriceNum : Int,
    val UserRate : Int,
    val ExId : Int,
    val AucHits : Int,
    val GreatTime : String?,
    val TopUserId : String?,
    val IsReturnSer : Int,
    val AucIsRepeat : Int,
    val OrchidBigSort : Int,
    val OrchidFloSort : Int,
    val CancelTransNums : Int,
    val IsPhonePub : Int,
    val IsLive : Int
) :BaseEntity(AutoId), Serializable
