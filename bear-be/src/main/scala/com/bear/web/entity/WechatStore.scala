package com.bear.web.entity

import scala.beans.BeanProperty
import java.sql.Timestamp

/**
  * Created by Apple on 16/6/8.
  */
class WechatStore {
  /** 微信标识 */
  @BeanProperty var openId: String = _

  /** 门店标识 */
  @BeanProperty var storeId: Int = _

  /** 创建时间 */
  @BeanProperty var createTime: Timestamp = _
}
