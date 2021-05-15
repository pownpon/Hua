package com.pownpon.hua.model.bean.entity

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
    val AutoId: Int,
    val ClassId: Int,
    val Title: String?,
    val ParentId: Int,
    val MenuNum: Int,
    val ChidNum: Int,
    val IdPath: String?,
    val TitlePath: String?,
    val imgPath: String?
) : BaseEntity(AutoId), Serializable
