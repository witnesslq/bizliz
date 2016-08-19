package com.bear.web
package mapper

import com.bear.web.entity.StoreWechat
import org.springframework.stereotype.Repository
/**
  * Created by Apple on 16/6/8.
  */
@Repository
trait StoreWechatMapper {
  /**
    * 新增
 *
    * @param record 实体
    * @return 影响行数
    */
  def insert(record: StoreWechat): Int

  /**
    * 查询
 *
    * @param storeId 主键
    * @return 返回实体
    */
  def selectByPrimaryKey(storeId: Int): StoreWechat

  /**
    * 更新
 *
    * @param record 实体
    * @return 影响行数
    */
  def updateByPrimaryKey(record: StoreWechat): Int

  /**
    * 根据微信的唯一id进行查询
 *
    * @param toUserName wechatID
    * @return 返回实体
    */
  def selectByWechatId(toUserName: String): StoreWechat

  /**
    * 查询所有门店的微信设置信息
 *
    * @return   所有门店的微信设置信息
    */
  def selectAll(): java.util.List[StoreWechat]
}
