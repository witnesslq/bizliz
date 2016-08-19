package com.bear.web.dto

import scala.beans.BeanProperty

/**
  * Created by Apple on 16/6/8.
  */
class WxTeletextDto {
  /** 消息ID */
  @BeanProperty var replyId: Int = _

  /** 图文消息MediaId */
  @BeanProperty var mediaId: String = _

  /** 图文消息的标题 */
  @BeanProperty var title: String = _

  /** 作者 */
  @BeanProperty var author: String = _

  /** 图文页的URL */
  @BeanProperty var url: String = _

  /** 图文消息的描述 */
  @BeanProperty var description: String = _

  /** 图片链接 */
  @BeanProperty var picUrl: String = _

  /** 七牛图片路径 */
  @BeanProperty var qiniuImg: String = _

  /**永久图片媒体ID*/
  @BeanProperty var thumbMediaId: String = _

  /**图文消息textarea*/
  @BeanProperty var content: String = _

  /**原文地址*/
  @BeanProperty var contentSourceUrl: String = _

  /** 创建时间 */
  @BeanProperty var createTime: String = _

  /** 门店ID */
  @BeanProperty var storeId: Int = _

  /** 复制的图文媒体ID*/
  @BeanProperty var fatherMediaId: String = _

  /**图文热度,门店使用*/
  @BeanProperty var zcount: Long = _

  /**点赞人数,门店使用*/
  @BeanProperty var praise: Long = _

  /**是否点赞,门店使用*/
  @BeanProperty var isPraise: Int = _
}
