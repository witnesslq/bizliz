package com.bear.web.entity

import scala.beans.BeanProperty

/**
  * 公众号与门店关联表
  * Created by Apple on 16/6/8.
  */
class StoreWechat {
  /** 门店ID */
  @BeanProperty var storeId: Int = _

  /** 公众号原始id */
  @BeanProperty var wechatId: String = _

  /** 公众号应用ID */
  @BeanProperty var wechatAppid: String = _

  /** 公众号应用密钥 */
  @BeanProperty var wechatAppsecret: String = _

  /** 客户预约申请通知 */
  @BeanProperty var tmAppointApply: String = _

  /** 客户预约结果通知 */
  @BeanProperty var tmAppointResult: String = _

  /** 客户预约到时提醒 */
  @BeanProperty var tmAppointRemind: String = _

  /** 客户充值结果提醒 */
  @BeanProperty var tmChargeResult: String = _

  /** 客户结账信息通知 */
  @BeanProperty var tmPaymentInfo: String = _

  /** 员工服务移交通知 */
  @BeanProperty var tmServiceTurn: String = _

  /** 优惠券到期提醒 */
  @BeanProperty var tmCouponOverdue: String = _
}
