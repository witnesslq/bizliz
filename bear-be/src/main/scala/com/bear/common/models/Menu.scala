package com.bear.common
package models

import consts.WxConsts.MenuButtonCons._
import com.fasterxml.jackson.databind.ObjectMapper
import collection.JavaConverters._

sealed trait Button{
  val name: String
}

case class ClickButton(name: String, key: String) extends Button{
  val typ = BUTTON_CLICK
}

case class ViewButton(name: String, url: String) extends Button{
  val typ = BUTTON_VIEW
}

case class SubButton(name: String, subButton: List[Button]) extends Button

case class Menu(buttons: List[Button]) {
  val mapper = new ObjectMapper()
  def toJson = mapper.writeValueAsString(Map("button" -> buttons.map(buttonToJsValue).asJava).asJava)

  def buttonToJsValue(button: Button): java.util.Map[String, Object]= {
    button match {
      case clickButton @ ClickButton(name, key) =>
        Map[String, Object]("type" -> clickButton.typ, "name" -> clickButton.name, "key" -> clickButton.key).asJava
      case viewButton @ ViewButton(name, url) =>
        Map[String, Object]("type" -> viewButton.typ, "name" -> viewButton.name, "url" -> viewButton.url).asJava
      case SubButton(name, subButton) =>
        Map[String, Object]("name" -> name, "sub_button" -> subButton.map(buttonToJsValue).asJava).asJava

    }
  }

  def urlWithWrap(withWrapFunc: String => String) = {

    def buttonWithWrap(button: Button): Button = {
      button match {
        case ViewButton(name, url) => ViewButton(name, withWrapFunc(url))
        case SubButton(name, subButton) => SubButton(name, subButton.map(buttonWithWrap))
        case other => other
      }
    }

    Menu(buttons.map(buttonWithWrap))
  }

  def getMenuUrl = {
    var contents: List[String] = List.empty

    def getContent(buttons: List[Button]): Unit = buttons.foreach {
      case ClickButton(name, key) => contents = key :: contents
      case ViewButton(name, url) => contents = url :: contents
      case SubButton(name, subButton) => getContent(subButton)
    }

    getContent(buttons)

    contents
  }
}
object Menu{

  def getDefaultAuthMenu(url: String = "", authId: Long): List[Button] = {
    //val promotion = s"http://$url/weixin/platform/auth/$authId/menu/1"
    //val profile = s"http://$url/weixin/platform/auth/$authId/menu/2"
    val voucher = s"http://$url/weixin/platform/auth/$authId/menu/3"
    //val integral = s"http://$url/weixin/platform/auth/$authId/menu/4"
    val bill = s"http://$url/weixin/platform/auth/$authId/menu/5"
    val memberCard = s"http://$url/weixin/platform/auth/$authId/menu/6"

    List(
      SubButton("会员卡", List(
        ViewButton("我的券", voucher),
        ViewButton("我的账单", bill),
        ViewButton("我的卡", memberCard)
      )
      )
    )
  }

  val defaultMenu = List(
    ViewButton("优惠", "prime"),
    SubButton("会员卡", List(
      ViewButton("完善资料", "http://www.soso.com/"),
      ViewButton("我的券", "http://www.soso.com/"),
      ViewButton("我的积分", "http://www.soso.com/"),
      ViewButton("我的账单", "http://www.soso.com/"),
      ViewButton("我的卡", "http://www.soso.com/")
      )
    )
  )

  def aieMenu(domain: String) = {
    val url = s"http://$domain/weixin/aide/brand/0/shop/0/site/1"
    List(
      ViewButton("我的主页", url),
      ViewButton("商城", "http://www.sanyipos.com/")
    )
  }

}


