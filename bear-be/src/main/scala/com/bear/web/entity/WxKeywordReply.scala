package com.bear.web.entity

import scala.beans.BeanProperty
/**
  * Created by tanghong on 01/08/2016.
  */
class WxKeywordReply {
  @BeanProperty var id: Int = _
  @BeanProperty var storeId: Int = _
  @BeanProperty var authId: Int = _
  @BeanProperty var appId: String = _
  @BeanProperty var rule: String = _
  @BeanProperty var keyword: String = _
  @BeanProperty var content: String = _
  @BeanProperty var createon: String = _
  @BeanProperty var updateon: String = _
  @BeanProperty var state: Int = _
}
