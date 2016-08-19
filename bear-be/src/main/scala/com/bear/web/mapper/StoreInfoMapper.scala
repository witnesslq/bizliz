package com.bear.web.mapper

import com.bear.web.entity.StoreInfo
import org.apache.ibatis.annotations.Param
import org.springframework.stereotype.Repository

/**
  * Created by Apple on 16/6/10.
  */
@Repository
trait StoreInfoMapper {
  /**
    * 删除
    *
    * @param storeId 门店id
    * @return int
    */
  def deleteByPrimaryKey(storeId: Int): Int


  /**
    * 插入
    *
    * @param storeInfo 门店信息
    * @return int
    */
  def insert(storeInfo: StoreInfo): Int

  /**
    * 查询
    *
    * @param storeId 门店id
    * @return StoreInfo
    */
  def selectByPrimaryKey(@Param(value = "storeId") storeId: Int): StoreInfo

  /**
    * 更新
    *
    * @param storeInfo 门店信息
    * @return int
    */
  def updateByPrimaryKey(storeInfo: StoreInfo): Int

  /**
    * 根据门店标识查询门店基础信息
    *
    * @param storeId 门店标识
    * @return 门店基础信息
    */
  def selectBaseInfoByStoreId(storeId: Int): StoreInfo

  /**
    * 根据门店查询门店介绍
    *
    * @param storeId 门店标识
    * @return 门店介绍
    */
  def selectDescByStoreId(storeId: Int): StoreInfo

  /**
    * 根据门店查询技术展示
    *
    * @param storeId 技术展示
    * @return 技术展示
    */
  def selectTechnicalByStoreId(storeId: Int): String

  /**
    * 根据门店查询特色服务
    *
    * @param storeId 特色服务
    * @return 特色服务
    */
  def selectCharacteristicByStoreId(storeId: Int): String

  /**
    * 根据总店标识查询所有分店信息，不包括自己
    *
    * @param mainStoreId 总店标识
    * @return 分店列表
    */
  def selectBaseInfoByMainId(mainStoreId: Int): java.util.List[StoreInfo]

  /**
    * 根据总店id查询旗下的分店数量
    *
    * @param hqStoreId 总店id
    * @return 分店数量
    */
  def countByHQStoreId(hqStoreId: Int): Int


  /**
    * 根据总店id查询旗下所有分店
    *
    * @param hqStoreId 总店id
    * @return 总店id查询旗下所有分店
    */
  def selectChainsByHQStoreId(hqStoreId: Int): java.util.List[StoreInfo]

  /**
    * 根据多个门店id查询门店账号
    *
    * @param storeIds 多个门店id
    * @return 多个门店账号
    */
  def selectByStoreIds(storeIds: java.util.List[Int]): java.util.List[StoreInfo]

  /**
    * 根据门店标识查询总店，当店/总店返回自身
    *
    * @param storeId 门店标识
    * @return 总店标识
    */
  def selectMainIdByStoreId(storeId: Int): Int

  /**
    * 查询所有门店标识
    *
    * @return List<Integer>
    */
  def selectStoreIdAll(): java.util.List[Int]


  /**
    * 根据门店id和门店名称查询门店
    *
    * @param prams 参数
    * @return 门店集合, 根据传入的门店id排序
    */
  def selectOrderedStore(prams: java.util.Map[String, Object]): java.util.List[StoreInfo]

  /**
    * 根据门店id查询门店的微信会员数
    *
    * @param storeIds 门店id
    * @return 会员数
    */
  def countWechatByIds(storeIds: List[Int]): Int

  /**
    * 查询一个省份下已开的门店
    *
    * @param province 省份名称
    * @return 一个省份下已开的门店
    */
  def selectOpenedByProvince(province: String): java.util.List[StoreInfo]

}
