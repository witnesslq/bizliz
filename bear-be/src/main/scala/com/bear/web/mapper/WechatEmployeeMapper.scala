package com.bear.web
package mapper

import com.bear.web.entity.WechatEmployee
import org.springframework.stereotype.Repository

/**
  * Created by Apple on 16/6/8.
  */
@Repository
trait WechatEmployeeMapper {
  /**
    * 根据微信id删除关联记录
    *
    * @param openId 微信id
    * @return 0:失败，1:成功
    */
  def deleteByPrimaryKey(openId: String): Int

  /**
    * 插入微信id与员工关联记录
 *
    * @param record 关联记录
    * @return 0:失败，1:成功
    */
  def insert(record: WechatEmployee): Int

  /**
    * 根据微信id查询关联记录
    *
    * @param openId 微信id
    * @return 关联记录
    */
  def selectByPrimaryKey(openId: String): WechatEmployee

  /**
    * 根据员工编号查询关联记录
 *
    * @param employeeId
    * @return
    */
  def selectByEmployeeId(employeeId: Int): WechatEmployee

  /**
    * 根据微信id修改关联记录
    *
    * @param record 关联记录
    * @return 0:失败，1:成功
    */
  def updateByPrimaryKey(record: WechatEmployee): Int

  def selectOpenIdByUserName(name: String): WechatEmployee
}
