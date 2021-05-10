package com.pownpon.hua.model.bean.entity

import com.google.gson.annotations.SerializedName
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
    @SerializedName("AutoId") val autoId: Int,
    @SerializedName("AucId") val aucId: String?,
    @SerializedName("SellerId") val sellerId: String?,
    @SerializedName("Seller") val seller: String?,
    @SerializedName("AucType") val aucType: Int,
    @SerializedName("AucClassId") val aucClassId: Int,
    @SerializedName("ShopClassId") val shopClassId: Int,
    @SerializedName("AucTitle") val aucTitle: String?,
    @SerializedName("StartTime") val startTime: String?,
    @SerializedName("EndTime") val endTime: String?,
    @SerializedName("AucNum") val aucNum: Int,
    @SerializedName("AucSpec") val aucSpec: String?,
    @SerializedName("AucPro") val aucPro: String?,
    @SerializedName("AucCity") val aucCity: String?,
    @SerializedName("StartPrice") val startPrice: Double,
    @SerializedName("ExtPrice") val extPrice: Double,
    @SerializedName("ImmPrice") val immPrice: Double,
    @SerializedName("AucHot") val aucHot: Int,
    @SerializedName("TraPayMethod") val traPayMethod: Int,
    @SerializedName("TraObject") val traObject: Int,
    @SerializedName("ChTraPrice") val chTraPrice: Double,
    @SerializedName("DeTraPrice") val deTraPrice: Double,
    @SerializedName("EmsTraPrice") val emsTraPrice: Double,
    @SerializedName("AucIP") val aucIP: String?,
    @SerializedName("AucIsdel") val aucIsdel: Int,
    @SerializedName("AucIsSell") val aucIsSell: Int,
    @SerializedName("AucGoodsType") val aucGoodsType: Int,
    @SerializedName("AucIsEvacuate") val aucIsEvacuate: Int,
    @SerializedName("AucIsPay") val aucIsPay: Int,
    @SerializedName("AucIsOrchid") val aucIsOrchid: Int,
    @SerializedName("BigSrc") val bigSrc: String?,
    @SerializedName("MidSrc") val midSrc: String?,
    @SerializedName("SmallSrc") val smallSrc: String?,
    @SerializedName("TopPrice") val topPrice: Double,
    @SerializedName("PriceNum") val priceNum: Int,
    @SerializedName("UserRate") val userRate: Int,
    @SerializedName("ExId") val exId: Int,
    @SerializedName("AucHits") val aucHits: Int,
    @SerializedName("GreatTime") val greatTime: String?,
    @SerializedName("TopUserId") val topUserId: String?,
    @SerializedName("IsReturnSer") val isReturnSer: Int,
    @SerializedName("AucIsRepeat") val aucIsRepeat: Int,
    @SerializedName("OrchidBigSort") val orchidBigSort: Int,
    @SerializedName("OrchidFloSort") val orchidFloSort: Int,
    @SerializedName("CancelTransNums") val cancelTransNums: Int,
    @SerializedName("IsPhonePub") val isPhonePub: Int,
    @SerializedName("IsLive") val isLive: Int
) :BaseEntity(autoId), Serializable
