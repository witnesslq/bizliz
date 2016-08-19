package com.bear.web
package mapper

import com.bear.web.entity.WechatSubscribe
import org.springframework.stereotype.Repository

/**
  * Created by Apple on 16/6/8.
  */
@Repository
trait WechatSubscribeMapper {

  /**
    * 新增微信关注者
    *
    * @param record 关注信息
    * @return 0:失败，1:成功
    */
  def insert(record: WechatSubscribe): Int

  /**
    * 根据微信标识查询关注信息
    *
    * @param openId 微信标识
    * @return 关注信息
    */
  def selectByPrimaryKey(openId: String): WechatSubscribe


  /**
    * 取消关注/再次关注
    *
    * @param record 关注信息
    * @return 0:失败，1:成功
    */
  def updateByPrimaryKey(record: WechatSubscribe): Int
}
