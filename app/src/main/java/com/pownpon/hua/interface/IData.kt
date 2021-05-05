package com.pownpon.hua.`interface`

/**
 * Copyright (C), 2021-2030, XXX有限公司
 * FileName: IData
 * Author: HUA
 * Date: 2021/5/5 9:44
 * Description:
 * History:
 */
interface IData<T> {
    /**
     * 刷新数据
     */
    fun refreshData(dataList:List<T>)

    /**
     * 增加数据
     */
    fun addData(data:T)

    /**
     * 增加多数据
     */
    fun addData(dataList:List<T>)

    /**
     * 清除数据
     */
    fun clearData()

    /**
     * 更新单个数据
     */
    fun updateItem(data:T)

    /**
     * 移除单个数据
     */
    fun removeItem(data:T)

    /**
     * 根据索引移除数据
     */
    fun removeItem(position:Int)

}