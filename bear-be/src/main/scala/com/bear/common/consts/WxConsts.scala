package com.bear.common.consts

/**
  * Created by Apple on 16/6/7.
  */
object WxConsts extends Config with Cons

trait Config {
  val wxService = {
    WeiXinConfig("wx80cb6d22beb8d78b", "fafa4667403aa34478fdbbd32d8c1c54", "maywant", "THd9Ksh80zACN5h6XkHgqgKjL0GqytKSziFVyWpibVm", "batata.wicp.net")
    //WeiXinConfig("wxf5712d63a28ce7c8", "88cfbd7a84bddf3ba776e05911c44585", "maywant", "THd9Ksh80zACN5h6XkHgqgKjL0GqytKSziFVyWpibVm", "moffatt.maywant.com")
  }

  val zefunService = {
    //正式环境平台帐号配置
    //WeiXinConfig("wx2741a98577defffc", "1ee5695816cd4197c3e9c22d17aa48ed", "zefun", "MOxZeJ0g5FzGW0ocwMyjlq2yjJHgUNh5SSe7ZVXYSgD", "test.maywant.com")
    //测试环境平台帐号配置
    WeiXinConfig("wx55823f322f709275", "8898c8d5cb48c5aa24dfdb75218b7129", "zefun", "MOxZeJ0g5FzGW0ocwMyjlq2yjJHgUNh5SSe7ZVXYSgD", "www.maywant.com")
  }
}

trait Cons {

  object QiniuCons{
    val QINIU_DOMAIN = "http://7xkv8r.com1.z0.glb.clouddn.com/"
  }

  object WxUrlCons{
    val Plat_BASE_API_URL= "https://api.weixin.qq.com/cgi-bin/component/"
    val MEDIA_URI = "http://file.api.weixin.qq.com"
    val QRCODE_DOWNLOAD_URI = "https://mp.weixin.qq.com"
    val MCH_URI = "https://api.mch.weixin.qq.com"
    val OPEN_URI = "https://open.weixin.qq.com"
    val BASE_API_URI = "https://api.weixin.qq.com"
  }

  object ReqMethodCons{
    val post = "POST"
    val get = "GET"
  }

  object MsgTypeCons{
    ///////////////////////
    // 微信推送过来的消息的类型，和发送给微信xml格式消息的消息类型
    ///////////////////////
    val XML_MSG_TEXT = "text"
    val XML_MSG_IMAGE = "image"
    val XML_MSG_VOICE = "voice"
    val XML_MSG_VIDEO = "video"
    val XML_MSG_NEWS = "news"
    val XML_MSG_MUSIC = "music"
    val XML_MSG_LOCATION = "location"
    val XML_MSG_LINK = "link"
    val XML_MSG_EVENT = "event"
    val XML_TRANSFER_CUSTOMER_SERVICE = "transfer_customer_service"
    ///////////////////////
    // 主动发送消息的消息类型
    ///////////////////////
    val CUSTOM_MSG_TEXT = "text"
    val CUSTOM_MSG_IMAGE = "image"
    val CUSTOM_MSG_VOICE = "voice"
    val CUSTOM_MSG_VIDEO = "video"
    val CUSTOM_MSG_MUSIC = "music"
    val CUSTOM_MSG_NEWS = "news"
    val CUSTOM_MSG_FILE = "file"
    ///////////////////////
    // 群发消息的消息类型
    ///////////////////////
    val MASS_MSG_NEWS = "mpnews"
    val MASS_MSG_TEXT = "text"
    val MASS_MSG_VOICE = "voice"
    val MASS_MSG_IMAGE = "image"
    val MASS_MSG_VIDEO = "mpvideo"
  }

  object FeedBackCons{
    ///////////////////////
    // 群发消息后微信端推送给服务器的反馈消息
    ///////////////////////
    val MASS_ST_SUCCESS = "send success"
    val MASS_ST_FAIL = "send fail"
    val MASS_ST_涉嫌广告 = "err(10001)"
    val MASS_ST_涉嫌政治 = "err(20001)"
    val MASS_ST_涉嫌社会 = "err(20004)"
    val MASS_ST_涉嫌色情 = "err(20002)"
    val MASS_ST_涉嫌违法犯罪 = "err(20006)"
    val MASS_ST_涉嫌欺诈 = "err(20008)"
    val MASS_ST_涉嫌版权 = "err(20013)"
    val MASS_ST_涉嫌互推_互相宣传 = "err(22000)"
    val MASS_ST_涉嫌其他 = "err(21000)"
    /**
      * 群发反馈消息代码所对应的文字描述
      */
    val MASS_ST_2_DESC = Map(
      (MASS_ST_SUCCESS -> "发送成功"),
      (MASS_ST_FAIL -> "发送失败"),
      (MASS_ST_涉嫌广告 -> "涉嫌广告"),
      (MASS_ST_涉嫌政治 -> "涉嫌政治"),
      (MASS_ST_涉嫌社会 -> "涉嫌社会"),
      (MASS_ST_涉嫌色情 -> "涉嫌色情"),
      (MASS_ST_涉嫌违法犯罪 -> "涉嫌违法犯罪"),
      (MASS_ST_涉嫌欺诈 -> "涉嫌欺诈"),
      (MASS_ST_涉嫌版权 -> "涉嫌版权"),
      (MASS_ST_涉嫌互推_互相宣传 -> "涉嫌互推_互相宣传"),
      (MASS_ST_涉嫌其他 -> "涉嫌其他")
    )
  }

  object EventCons{
    ///////////////////////
    // 微信端推送过来的事件类型
    ///////////////////////
    val EVT_SUBSCRIBE = "subscribe"
    val EVT_UNSUBSCRIBE = "unsubscribe"
    val EVT_SCAN = "SCAN"
    val EVT_LOCATION = "LOCATION"
    val EVT_CLICK = "CLICK"
    val EVT_VIEW = "VIEW"
    val EVT_MASS_SEND_JOB_FINISH = "MASSSENDJOBFINISH"
    val EVT_SCANCODE_PUSH = "scancode_push"
    val EVT_SCANCODE_WAITMSG = "scancode_waitmsg"
    val EVT_PIC_SYSPHOTO = "pic_sysphoto"
    val EVT_PIC_PHOTO_OR_ALBUM = "pic_photo_or_album"
    val EVT_PIC_WEIXIN = "pic_weixin"
    val EVT_LOCATION_SELECT = "location_select"
    val EVT_TEMPLATESENDJOBFINISH = "TEMPLATESENDJOBFINISH"
    val EVT_ENTER_AGENT = "enter_agent"
  }

  object MediaCons{
    val MEDIA_IMAGE = "image"
    val MEDIA_VOICE = "voice"
    val MEDIA_VIDEO = "video"
    val MEDIA_THUMB = "thumb"
    val MEDIA_FILE = "file"
  }

  object documentCons{
    val FILE_JPG = "jpeg"
    val FILE_MP3 = "mp3"
    val FILE_ARM = "arm"
    val FILE_MP4 = "mp4"
  }

  object MaterialCons{
    val STUFF_THUMB = "thumb" // 缩略图,临时素材
    val IMAGE = "image"
  }

  object MenuButtonCons{
    ///////////////////////
    // 自定义菜单的按钮类型
    ///////////////////////
    /** 点击推事件 */
    val BUTTON_CLICK = "click"
    /** 跳转URL */
    val BUTTON_VIEW = "view"
    /** 扫码推事件 */
    val BUTTON_SCANCODE_PUSH = "scancode_push"
    /** 扫码推事件且弹出“消息接收中”提示框 */
    val BUTTON_SCANCODE_WAITMSG = "scancode_waitmsg"
    /** 弹出系统拍照发图 */
    val PIC_SYSPHOTO = "pic_sysphoto"
    /** 弹出拍照或者相册发图 */
    val PIC_PHOTO_OR_ALBUM = "pic_photo_or_album"
    /** 弹出微信相册发图器 */
    val PIC_WEIXIN = "pic_weixin"
    /** 弹出地理位置选择器 */
    val LOCATION_SELECT = "location_select"
    ///////////////////////
    // oauth2网页授权的scope
    ///////////////////////
    /** 不弹出授权页面，直接跳转，只能获取用户openid */
    val OAUTH2_SCOPE_BASE = "snsapi_base"
    /** 弹出授权页面，可通过openid拿到昵称、性别、所在地。并且，即使在未关注的情况下，只要用户授权，也能获取其信息 */
    val OAUTH2_SCOPE_USER_INFO = "snsapi_userinfo"
  }

  object TemplateCons{
    val MEMBER_CONSUME = "OPENTM203366326"
    val MEMBER_CHARGE = "OPENTM207498144"

    val INDUSTRY_ID1 = "1"
    val INDUSTRY_ID2 = "10"
    val PRIMARY_INDUSTRY1 = "IT科技"
    val PRIMARY_INDUSTRY2 = "互联网|电子商务"
    val SECONDARY_INDUSTRY1 = "餐饮"
    val SECONDARY_INDUSTRY2 = "餐饮"
  }

  object QrCodeCons{
    val TEMPORARY_ACTION_NAME = "QR_SCENE"
  }

  object ERRCodeCons{
    val ERR_CODE = "errcode"
  }

}