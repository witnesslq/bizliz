package com.bear.web
package mapper

import com.bear.web.entity.WechatStore
import org.springframework.stereotype.Repository

/**
  * Created by Apple on 16/6/8.
  */
@Repository
trait WechatStoreMapper {

  /**
    * 新增微信门店映射信息
    *
    * @param record 微信门店映射信息
    * @return 0:失败，1:成功
    */
  def insert(record: WechatStore): Int

  /**
    * 根据微信标识查询微信门店映射信息
    *
    * @param openId 微信标识
    * @return 微信门店映射信息
    */
  def selectByPrimaryKey(openId: String): WechatStore

  /**
    * 根据门店标识查询微信门店映射信息
    *
    * @param storeId 门店标识
    * @return 微信门店映射信息
    */
  def selectByStoreId(storeId: Int): WechatStore

  /**
    * 统计微信关注数
    *
    * @param params 参数
    * @return 微信关注数
    */
  def countFollowByStoreIds(params: Map[String, Object]): Int

  /**
    * 根据主键删除关联记录
    *
    * @param openId 微信标识
    * @return 0:失败，1:成功
    */
  def deleteByPrimaryKey(openId: String): Int
}
