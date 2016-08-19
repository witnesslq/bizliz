package com.bear.common.utils

/**
  * Created by Apple on 16/6/6.
  */
object Helpers extends DataUtils with SafeUtils{
  def verifySignature(timestamp: String, nonce: String, signature: String, token: String): Boolean = sha1Hex(List(token, timestamp, nonce).sorted.mkString) == signature
}
