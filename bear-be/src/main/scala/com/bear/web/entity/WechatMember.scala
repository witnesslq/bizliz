package com.bear.web.entity

import scala.beans.BeanProperty

/**
  * Created by Apple on 16/6/8.
  */
class WechatMember {
  /** 微信标识 */
  @BeanProperty var openId: String = _

  /** 会员标识 */
  @BeanProperty var memberId: Int = _

  /** 是否关注(0:未关注,1:已关注) */
  @BeanProperty var isSubscribe: Int = _

  /** 创建时间 */
  @BeanProperty var createTime: String = _

  /** 修改时间 */
  @BeanProperty var updateTime: String = _
}
