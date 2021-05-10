package com.pownpon.hua.model.bean.entity

import com.google.gson.annotations.SerializedName
import com.pownpon.hua.model.bean.base.BaseEntity
import java.io.Serializable

/**
 * Copyright (C), 2021-2030, XXX有限公司
 * FileName: GoodsClass
 * Author: HUA
 * Date: 2021/5/10 10:51
 * Description: 物品分类
 * History:
 */
data class GoodsClass(
    @SerializedName("AutoId")
    val autoId: Int,
    @SerializedName("ClassId")
    val classId: Int,
    @SerializedName("Title")
    val title: String?,
    @SerializedName("ParentId")
    val parentId: Int,
    @SerializedName("MenuNum")
    val menuNum: Int,
    @SerializedName("ChidNum")
    val chidNum: Int,
    @SerializedName("IdPath")
    val idPath: String?,
    @SerializedName("TitlePath")
    val titlePath: String?,
    @SerializedName("imgPath")
    val imgPath: String?
) :BaseEntity(autoId), Serializable
