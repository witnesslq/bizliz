package com.bear.common.utils.http

import org.apache.http.conn.ssl.{NoopHostnameVerifier, SSLConnectionSocketFactory}
import org.apache.http.impl.client.{CloseableHttpClient, HttpClients}
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager
import org.apache.http.ssl.SSLContexts

/**
  * Created by Apple on 16/6/6.
  */
object HttpClientFactory {

  def createDefaultClient: CloseableHttpClient = {
    try {
      val ssl = SSLContexts.createSystemDefault()
      val ssf = new SSLConnectionSocketFactory(ssl, NoopHostnameVerifier.INSTANCE)
      HttpClients.custom().setSSLSocketFactory(ssf).build()
    } catch {
      case e: Exception => throw e
    }
  }

  def createClient(maxPerRoute: Int, maxTotal: Int): CloseableHttpClient = {
    try {
      val ssl = SSLContexts.createSystemDefault()
      val ssf = new SSLConnectionSocketFactory(ssl, NoopHostnameVerifier.INSTANCE)
      val pcm = new PoolingHttpClientConnectionManager()
      // max total coon 200
      pcm.setMaxTotal(maxTotal)
      // max per route 20
      pcm.setDefaultMaxPerRoute(maxPerRoute)
      HttpClients.custom()
        .setSSLSocketFactory(ssf)
        .setConnectionManager(pcm)
        .build()
    } catch {
      case e: Exception => throw e
    }
  }

}
