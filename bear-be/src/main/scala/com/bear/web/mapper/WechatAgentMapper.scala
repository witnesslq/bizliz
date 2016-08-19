package com.bear.web
package mapper

import entity.WechatAgent
import org.springframework.stereotype.Repository
/**
  * Created by Apple on 16/6/8.
  */
@Repository
trait WechatAgentMapper {

  /**
    * 新增微信与代理的映射
    * @param record 映射信息
    * @return   0:失败，1:成功
    */
  def insert(record: WechatAgent): Int


  /**
    * 根据微信标识查询映射信息
    * @param openId 微信标识
    * @return   映射信息
    */
  def selectByPrimaryKey(openId: String): WechatAgent
}
