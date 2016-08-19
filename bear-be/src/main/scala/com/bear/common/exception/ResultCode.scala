package com.bear.common.exception

/**
  * Created by tanghong on 16/7/19.
  */
object ResultCode {
  case class BaseCode(id: Int, msg: String)

  val NO_DATA = BaseCode(1001, "没有数据")
  val NO_FOUND = BaseCode(1002, "没有发现")

  val RENEW_FAILURE = BaseCode(1501, "更新失败")
  val REMOVE_FAILURE = BaseCode(1502, "删除失败")

  val UPLOAD_FAILURE = BaseCode(1531, "上传失败")
  val YET_DOWNLOADS = BaseCode(1532, "已经下载")

  val WEIXIN_SEND_FAILURE = BaseCode(2001, "微信发送消息失败")

  // bargain
  val YET_BARGAIN = BaseCode(3001, "已经砍过价")
  val EXISTS_UNDERWAY_BARGAIN = BaseCode(3002, "存在进行中的砍价")
}
