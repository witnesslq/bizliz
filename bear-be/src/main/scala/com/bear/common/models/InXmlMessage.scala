package com.bear.common
package models

import consts.WxConsts.{MsgTypeCons, EventCons}

sealed trait BaseMsgIn {
  val toUserName: String
  val fromUserName: String
  val msgType: String
  val createTime: String
}

/**
 * 普通消息
 */
import MsgTypeCons._
case class TextMsg(toUserName: String, fromUserName: String, createTime: String, content: String, msgId: String) extends BaseMsgIn {
  val msgType = XML_MSG_TEXT
}

case class ImageMsg(toUserName: String, fromUserName: String, createTime: String, mediaId: String, msgId: String) extends BaseMsgIn {
  val msgType = XML_MSG_IMAGE
}

case class VoiceMsg(toUserName: String, fromUserName: String, createTime: String, mediaId: String, msgId: String, format: String) extends BaseMsgIn {
  val msgType = XML_MSG_VIDEO
}
/**
 * 事件推送
 */
import EventCons._
sealed trait BaseEvent extends BaseMsgIn {
  val msgType = XML_MSG_EVENT
  val event: String
}

case class SubscribeEvent(toUserName: String, fromUserName: String, createTime: String, eventKey: String) extends BaseEvent {
  val event = EVT_SUBSCRIBE
}

case class ClickEvent(toUserName: String, fromUserName: String, createTime: String, eventKey: String) extends BaseEvent {
  val event = EVT_CLICK
}

case class ViewEvent(toUserName: String, fromUserName: String, createTime: String, eventKey: String) extends BaseEvent {
  val event = EVT_VIEW
}

case class ScanEvent(toUserName: String, fromUserName: String, createTime: String, eventKey: String) extends BaseEvent {
  val event = EVT_SCAN
}

case class TemplateSendResult(toUserName: String, fromUserName: String, createTime: String, msgID: String, status: String) extends BaseEvent {
  val event = EVT_TEMPLATESENDJOBFINISH
}

object UnMatchMsgIn extends BaseMsgIn {
  val (toUserName, fromUserName, msgType, createTime) = ("", "", "", "")

  override def toString = "unmatch message"
}