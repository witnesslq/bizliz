package com.bear.web.dto

import scala.beans.BeanProperty

/**
  * Created by tanghong on 16/7/1.
  */
class ScreeningDto {
  /** 主键 */
  @BeanProperty var screeningId: Int = _
  /** 筛选器名称 */
  @BeanProperty var screeningName: String = _
  /** 筛选器创建时间 */
  @BeanProperty var createTime: String = _
  /** 性别 */
  @BeanProperty var sex: String = _
  /** 等级 */
  @BeanProperty var levelId: Int = _
  /** 储值余额 */
  @BeanProperty var unusedBalanceStart: Int = _
  /** 储值余额 */
  @BeanProperty var unusedBalanceEnd: Int = _
  /** 积分余额 */
  @BeanProperty var integralBalanceStart: Int = _
  /** 积分余额 */
  @BeanProperty var integralBalanceEnd: Int = _
  /** 生日 */
  @BeanProperty var birthdayStart: String = _
  /** 生日 */
  @BeanProperty var birthdayEnd: String = _
  /** 注册日期 */
  @BeanProperty var registrationDateStart: String = _
  /** 注册日期 */
  @BeanProperty var registrationDateEnd: String = _
  /** 消费均额 */
  @BeanProperty var consumptionAverageStart: Int = _
  /** 消费均额 */
  @BeanProperty var consumptionAverageEnd: Int = _
  /** 累计消费 */
  @BeanProperty var cumulativeConsumptionStart: Int = _
  /** 累计消费 */
  @BeanProperty var cumulativeConsumptionEnd: Int = _
  /** 挂账 */
  @BeanProperty var debtAmountStart: Double = _
  /** 挂账 */
  @BeanProperty var debtAmountEnd: Double = _
  /** 礼金余额 */
  @BeanProperty var giftMoneyAmountStart: Double = _
  /** 礼金余额 */
  @BeanProperty var giftMoneyAmountEnd: Double = _
  /** 距离上次消费 */
  @BeanProperty var lastConsumeTimeStart: Int = _
  /** 距离上次消费 */
  @BeanProperty var lastConsumeTimeEnd: Int = _
  /** 消费频率 */
  @BeanProperty var avgVisitDaysStart: Int = _
  /** 消费频率 */
  @BeanProperty var avgVisitDaysEnd: Int = _
  /** 门店信息 */
  @BeanProperty var storeId: Int = _
  /** 分店门店id */
  @BeanProperty var branchStoreIds: String = _
}
