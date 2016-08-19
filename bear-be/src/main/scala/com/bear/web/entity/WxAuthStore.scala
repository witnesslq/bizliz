package com.bear.web.entity

import scala.beans.BeanProperty

/**
  * Created by tanghong on 01/08/2016.
  */
class WxAuthStore {
  @BeanProperty var authId: Int = _
  @BeanProperty var storeId: Int = _
  @BeanProperty var appId: String = _
  @BeanProperty var authCode: String = _
  @BeanProperty var refreshCode: String = _
  @BeanProperty var createon: String = _
  @BeanProperty var state: Int = _
}
