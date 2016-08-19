package com.bear.web.controller.sca

import javax.servlet.http.HttpServletRequest
import javax.xml.bind.annotation.XmlRootElement

import com.bear.common.api.dto.VerifyTicket
import com.bear.common.api.message.AesMessageApi._
import com.bear.common.api.message.ServiceMessageApi._
import com.bear.common.api.TokenApi._
import com.bear.common.api.PlatformApi._
import com.bear.common.consts.WxConsts.{EventCons, MsgTypeCons}
import com.bear.common.consts.Url
import com.bear.common.utils.Helpers._
import com.bear.common.consts.WxConsts._
import com.bear.common.manage.WxCacheManage
import com.bear.common.models.OutTextMsg
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation._
import scala.xml.XML
import scala.beans.BeanProperty

/**
  * Created by tanghong on 16/7/8.
  */
@Controller
@CrossOrigin(origins = Array("*"), maxAge = 3600, allowedHeaders = Array("Content-Type"),
  allowCredentials = "false")
class PlatformController {

  @Autowired
  @BeanProperty var wxCacheManage: WxCacheManage = _

  val logger = LoggerFactory.getLogger(classOf[PlatformController])


  @RequestMapping(value = Array(Url.WxPlatForm.SERVICE_CALL_BACK), method = Array(RequestMethod.GET))
  @ResponseBody
  def getMPAuthCode(@PathVariable storeId: Int, req: HttpServletRequest): String =  {
    val url = req.getHeader("Host")
    val ComAppId = zefunService.appId

    {
      for {
        authCode <- Option(req.getParameter("auth_code"))
        ticket <- wxCacheManage.getPlatformTicket(ComAppId)
      } yield (authCode, ticket)
    }.flatMap{
      case ((authCode, ticket)) =>
        logger.info(s"获取公众号授权码:$authCode")
        val q = for {
          token <- wxCacheManage.getCacheComponentAccessToken(ComAppId, ticket)
          auth <- getAuthorizerInfo(token, ComAppId, authCode).toOption
          authInfo <- getAuthorizerAccountInfo(token, ComAppId, auth.authorization_info.authorizer_appid).toOption
        } yield (auth, authInfo)
        q.map{ case (auth, authInfo) =>
          logger.info("授权账号信息:" + auth.authorization_info.authorizer_appid)
         // Redirect(s"http://$url/home/brand/$id/weixin/platform")
         "success"
        }
    }.getOrElse {
      logger.info("公众号授权传入参数错误或缓存票据已经失效...")
      "success"
    }
  }


  /**
    * 获取第三方平台ticket, 微信统一要求返回字符串
 *
    * @return
    */
  @RequestMapping(value = Array(Url.WxPlatForm.PLATFORM_MAIL_BOX), method = Array(RequestMethod.POST))
  @ResponseBody
  def platFormMailBox(@RequestBody xml: String, req: HttpServletRequest): String =  {
    {
      for {
        msgSignature <- Option(req.getParameter("msg_signature"))
        nonce <- Option(req.getParameter("nonce"))
        timestamp <- Option(req.getParameter("timestamp"))
        signature <- Option(req.getParameter("signature"))
      } yield (msgSignature, nonce, timestamp, signature)
    }.map {
      case ((msgSignature, nonce, timestamp, signature))
        if verifySignature(timestamp, nonce, signature, zefunService.token) =>
        logger.info("信息"+xml)
        val recXml = XML.loadString(getDecryptContent(zefunService, VerifyTicket(msgSignature, timestamp, nonce, xml)))
        logger.info(s"推送消息解密内容:$recXml")
        recXml.\\("InfoType").text match {
          case infoType if infoType == "component_verify_ticket" =>
            val ticket = recXml.\\("ComponentVerifyTicket").text
            wxCacheManage.setPlatformTicket(zefunService.appId, ticket)
            wxCacheManage.getCacheComponentAccessToken(zefunService.appId, ticket)
            logger.info(s"缓存微信推送票据:$ticket")
            "success"
          case unauth if unauth == "unauthorized" =>
            val authAppId = recXml.\\("AuthorizerAppid").text
            "success"
          case _ => "failure"
        }
    }.getOrElse {
      logger.info("获取微信推送票据参数失败")
      "success"
    }
  }


  /**
    * 界面点击进入授权页面
 *
    * @return
    */
  @RequestMapping(value = Array(Url.WxPlatForm.AUTH_ADDRESS), method = Array(RequestMethod.GET))
  @ResponseBody
  def getAuthAddress(@PathVariable storeId: Int, req: HttpServletRequest): String = {
    val url = req.getHeader("Host")
    val comAppId = zefunService.appId
    val preAuthCode = for {
      ticket <- wxCacheManage.getPlatformTicket(comAppId)
      token <- wxCacheManage.getCacheComponentAccessToken(comAppId, ticket)
      pre <- getPreauthcode(token, comAppId).toOption
    } yield pre.pre_auth_code
    preAuthCode.map { code =>
      s"https://mp.weixin.qq.com/cgi-bin/componentloginpage?component_appid=$comAppId&pre_auth_code=$code&redirect_uri=" +
        s"http://$url/platform/wechat/auth/servecallback/store/$storeId"
    }.getOrElse("")
  }



  import MsgTypeCons._
  import EventCons._

  /**
    * 微信第三方平台处理中心
 *
    * @param appId
    * @param xml
    * @param req
    * @return
    */
  @RequestMapping(value = Array(Url.WxPlatForm.PLATFORM_CENTER), method = Array(RequestMethod.POST),
    produces = Array(MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_XML_VALUE))
  @XmlRootElement
  @ResponseBody
  def platFormCenter(@PathVariable appId: String, @RequestBody xml: String, req: HttpServletRequest): String =  {
    val msgSignature = req.getParameter("msg_signature")
    val nonce = req.getParameter("nonce")
    val timestamp = req.getParameter("timestamp")

    val recXml = XML.loadString(getDecryptContent(zefunService, VerifyTicket(msgSignature, timestamp, nonce, xml)))
    logger.info(s"推送xml:${recXml}")
    val toUserName = recXml.\\("ToUserName").text
    val fromUserName = recXml.\\("FromUserName").text
    recXml.\\("MsgType").text match {
      case text if text == XML_MSG_TEXT =>
        val content = recXml.\\("Content").text

        // 以下为微信全网发布测试代码:
        if (content == "TESTCOMPONENT_MSG_TYPE_TEXT") {
          val reply = OutTextMsg(fromUserName, toUserName, content + "_callback")
          getEncryptContent(zefunService, reply.toXml, reply.createTime.toString, nonce)
        }
        else {
          val comAppId =  zefunService.appId
          val query_code = content.split(":").last
          val msg = content.split(":").head
          if (content.startsWith("QUERY_AUTH_CODE")) {
            val ticket = wxCacheManage.getPlatformTicket(comAppId).getOrElse("")
            // val ticket = "ticket@@@G0azzaT2F5lvF2H2jcmMU8W7UEEOGNamM_2IIlnIALBtHOFiuTgS1Bzq-M74hFuaUTCP0IyL1B0pVnrBVCuxGw"
            logger.info("客服票据:" + ticket)
            getPlatFormAccessToken(comAppId, zefunService.secret, ticket.trim).map{ acc =>
              logger.info("第三平台token" + acc.component_access_token)
              getAuthorizerInfo(acc.component_access_token, comAppId, query_code).flatMap { auth =>
                sendServiceMsg(fromUserName, query_code + "_from_api", auth.authorization_info.authorizer_access_token)
              }
            }

           ""
          } else {
            // 关键字消息回复:
            val reply = OutTextMsg(fromUserName, toUserName, "hello")
            getEncryptContent(zefunService, reply.toXml, reply.createTime.toString, nonce)
          }
        }

      case event if event == XML_MSG_EVENT =>
        println("开发者微信号:"+ toUserName +"----"+"用户信息"+ fromUserName)
        val msg = recXml.\\("Event").text
        val reply = OutTextMsg(fromUserName, toUserName, msg + "from_callback")
        getEncryptContent(zefunService, reply.toXml, reply.createTime.toString, nonce)
      case _ => ""
    }
  }


  /**
    * 发起授权页的体验验证方法
 *
    * @return
    */
  @RequestMapping(value = Array(Url.WxPlatForm.VERIFY_AUTH), method = Array(RequestMethod.GET))
  @ResponseBody
  def verifyAuth: String = "success"

}
