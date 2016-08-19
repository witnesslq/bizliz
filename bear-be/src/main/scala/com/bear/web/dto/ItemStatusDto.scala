package com.bear.web.dto

import scala.beans.BeanProperty
/**
  * Created by Apple on 16/6/8.
  */
class ItemStatusDto {
  /**图文消息ID*/
  @BeanProperty var mediaId: String = _
  /**首个标题*/
  @BeanProperty var title: String = _
  /**创建时间*/
  @BeanProperty var createTime: String = _
  /**图文消息发送状态*/
  @BeanProperty var msgStatus: String = _
  /**成功接收人数*/
  @BeanProperty var successCount: String = _
  /**失败接收人数*/
  @BeanProperty var errorCount: String = _
  /**已经发送对象*/
  @BeanProperty var hasAccess: String = _
  /**已经阅读人数*/
  @BeanProperty var hasRead: String = _
  /**图文热度*/
  @BeanProperty var  hotCount: Int = _
  /**门店信息*/
  @BeanProperty var storeId: String = _
}
