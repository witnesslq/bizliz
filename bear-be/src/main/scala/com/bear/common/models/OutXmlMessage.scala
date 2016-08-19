package com.bear.common
package models

import consts.WxConsts.MsgTypeCons._

sealed trait BaseMsgOut {
  val toUser: String
  val fromUser: String
  val msgType: String
  val createTime = System.currentTimeMillis / 1000

  def other: String

  def toXml = s"""
    <xml>
      <ToUserName><![CDATA[${ toUser }]]></ToUserName>
      <FromUserName><![CDATA[${ fromUser }]]></FromUserName>
      <CreateTime>${ createTime }</CreateTime>
      <MsgType><![CDATA[${ msgType }]]></MsgType>
      ${ other }
    </xml>
  """
}

/**
 * 回复文本消息
 */
case class OutTextMsg(toUser: String, fromUser: String, content: String) extends BaseMsgOut {
  val msgType = XML_MSG_TEXT
  def other = s"""
     <Content><![CDATA[${ content }]]></Content>
  """
}

/**
 * 回复图片
 */
case class OutImageMsg(toUser: String, fromUser: String, mediaId: String) extends BaseMsgOut {
  val msgType = XML_MSG_IMAGE
  def other: String = ""
}

/**
 * 图文图文消息
 */
case class OutNewsMsg(toUser: String, fromUser: String, articles: Article *) extends BaseMsgOut {
  val msgType = XML_MSG_NEWS

  def other = s"""
    <ArticleCount>${ articles.size }</ArticleCount>
    <Articles>${ articles.map { article =>
    s"""<item>
        <Title><![CDATA[${ article.title }]]></Title>
        <Description><![CDATA[${ article.description }]]></Description>
        <PicUrl><![CDATA[${ article.picUrl }]]></PicUrl>
        <Url><![CDATA[${ article.uri }]]></Url>
      </item>"""
  }}</Articles>
  </xml>"""
}

case class Article(title: String, description: String, picUrl: String, uri: String)

object UnMatchMsgOut extends BaseMsgOut {
  val (toUser, fromUser, msgType) = ("", "", "")

  def other = ""
}