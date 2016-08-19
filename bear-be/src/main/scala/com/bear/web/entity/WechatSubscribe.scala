package com.bear.web.entity

import scala.beans.BeanProperty

/**
  * Created by Apple on 16/6/8.
  */
class WechatSubscribe {
  /** 微信标识 */
  @BeanProperty var openId: String = _

  /** 是否关注(0:未关注，1:已关注) */
  @BeanProperty var isSubscribe: Int = _

  /** 关注时间 */
  @BeanProperty var createTime: String = _

  /** 取消关注时间/再次关注时间 */
  @BeanProperty var updateTime: String = _
}
