package com.bear
package common
package api
package message

import api.aes._
import com.bear.common.api.dto._
import com.bear.common.consts.WeiXinConfig

import scala.xml.XML

/**
  * Created by Apple on 16/6/6.
  */
object AesMessageApi {
  /**
    * 解密微信消息内容
    *
    * @param config
    * @param verifyTicket
    * @return
    */
  def getDecryptContent(config: WeiXinConfig, verifyTicket: VerifyTicket): String = {
    val msgCrypt = new WXBizMsgCrypt(config.token, config.aseKey, config.appId)
    msgCrypt.decryptMsg(verifyTicket.msg_signature, verifyTicket.timeStamp, verifyTicket.none, verifyTicket.postData)
  }

  /**
    * 加密微信消息内容
    *
    * @param config
    * @param replyMsg
    * @param timeStamp
    * @param nonce
    * @return
    */
  def getEncryptContent(config: WeiXinConfig, replyMsg: String, timeStamp: String, nonce: String): String = {
    val msgCrypt = new WXBizMsgCrypt(config.token, config.aseKey, config.appId)
    msgCrypt.encryptMsg(replyMsg, timeStamp, nonce)
  }
}
