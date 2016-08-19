package com.bear.web.dto

/**
  * Created by tanghong on 16/6/15.
  */
object PromotionDto {

// 促销基础字段
case class BaseDataDto(storeId: Int, teletextId: Int)

// 促销活动
case class PromotionBargainDto(
  id: Option[Int],
  storeId: Int,
  promotionTypeId: Int,
  teletextId: Int,
  beginTime: String,
  endTime: String,
  participatorCouponId: Int,
  sharerCouponId: Int
)

case class ProductBargainSettingDto(
  id: Option[Int],
  storeId: Int,
  teletextId: Int,
  bargainPromotionId: Int,
  productTypeId: Int,
  productId: Int,
  productName: String,
  productAmount: Double,
  imgUrl: String,
  description: String,
  couponId: Int,
  bargainTimes: Int
)

case class BaseIdDto(id: Int, status: Option[Int])

case class ProductCategoryDto(promotionId: Int, promotionTypeId: Int)
case class ProductVoucherDto(storeId: Int, productTypeId: Int)
case class ShowProductVoucherDto(productTypeId: Int, productId: Int, storeId: Int)

case class BaseInteractivePromotionDto(
  productTypeId: Int,
  bargainPromotionId: Int)


case class MyBargainProductDto(
  bargainPromotionId: Int,
  status: Int,
  productTypeId: Int,
  openid: Option[String] = None
)

case class CreateBargainAmount(bargainSettingId: Int, bargainEventId: Option[Int])

case class PromotionDes(description: List[String])

case class GainVoucher(openid: String)

case class AlterClaimed(ids: List[Int])

case class VerifyRunningPromotion(id: Int, category: Int)

case class WxTeletextPromotion(teletextId: Int, promotionTypeId: Int)

}
