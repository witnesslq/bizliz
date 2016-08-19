package com.bear.web.dto

/**
  * Created by tanghong on 16/6/20.
  */
object NewsDto {


case class ThumbDto(
  imgUrl: String,
  /** 作者 */
  author: String,
  /** 标题 */
  title: String,
  /** 原文链接 */
  contentSourceUrl: String,
  /** 内容 */
  content: String,
  /** 摘要 */
  digest: String,
  /** 是否显示封面，0为false，即不显示，1为true，即显示 */
  showCoverPic: Int,
  promotionTypeId: Int
)



case class ArticlesDto(
  articles: List[ThumbDto],
  teletextId: Option[Int] = None
)


case class PraiseDto(
  id: Option[Int],
  promotionTypeId: Int,
  teletextId: Int)

  case class UpdateWxImgUrl(qiniuImg: String)
  case class WxNewsPreview(news: List[Int], groups: List[String], levels: List[String])
  case class WxNewsTest(news: List[Int], userName: String)
  case class WxBaseNewsDto(teletextId: Int)
  case class filtratePromotionType(promotionTypeId: Int)

  case class MemberGroupInfo(groups: List[String], levels: List[String])

}
