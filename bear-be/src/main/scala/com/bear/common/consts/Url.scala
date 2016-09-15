package com.bear.common.consts

/**
  * Created by Apple on 16/6/6.
  */
object Url {

  /** 系统设置模块 */
  object SystemSetting {
    /** 访问个人设置页面 */
    final val VIEW_PERSON_SETTING = "system/view/person"
    /** 个人设置操作 */
    final val ACTION_PERSON_SETTING = "system/action/person"
    /** 修改账户密码 */
    final val ACTION_UPDATE_PASSWORD = "system/action/updatePwd"
    /** 访问门店基础设置页面 */
    final val VIEW_BASE_SETTING = "system/view/baseSetting"
    /** 门店基础设置操作 */
    final val ACTION_BASE_SETTING = "system/action/baseSetting"
    /** 分店列表页面 */
    final val VIEW_BRANCH_LIST = "system/action/branchList"
    /** 门店微信管理页面 */
    final val VIEW_STORE_WECHAT = "system/view/storeWechat"
    /** 门店微信管理操作 */
    final val ACTION_STORE_WECHAT = "system/action/storeWechat"
    /** 门店使用状况查询 */
    final val VIEW_STORE_USAGE = "system/view/storeUsage"
    /**日志记录*/
    final val VIEW_STORE_MESSAGE = "system/view/messageList"
    /** 门店测试数据清除 */
    final val ACTION_CLEAN_DATA = "system/action/cleanData"
    /** 门店分享机制页面 */
    final val VIEW_SHARE = "system/view/share"
    /** 保存门店分享机制 */
    final val ACTION_SAVE_SHARE = "action/save/share"
    /** 商品or疗程提成设置*/
    final val COMBO_OR_GOODS_COMMISSION = "comboOrGoodsCommission/view/comboOrGoodsCommission"
    /** 商品or疗程提成设置(添加or修改)*/
    final val COMBO_OR_GOODS_COMMISSION_ADD = "comboOrGoodsAdd/save/comboOrGoodsAdd"
    /** 商品or疗程提成设置(加载数据)*/
    final val COMBO_OR_GOODS_COMMISSION_ONE = "comboOrGoodsSetting/save/comboOrGoodsSetting"
  }


  /** 会员等级模块 */
  object MemberLevel {
    /** 添加等级 */
    final val ACTION_ADD = "memberLevel/action/add"
    /** 显示会员等级列表的页面 */
    final val VIEW_LIST = "memberLevel/view/list"
    /** 分页查询会员等级信息 */
    final val ACTION_LIST = "memberLevel/view/list"
    /** 查询会员等级信息 */
    final val ACTION_INFO = "memberLevel/action/info"
    /** 删除会员等级信息 */
    final val ACTION_DELETE = "memberLevel/action/delete"
    /** 设置默认等级 */
    final val ACTION_DEFAULT = "memberLevel/action/default"
    /** 会员卡导入excle */
    final val IMPORTEXCLE = "memberLevel/action/importexcle"

  }

  /** 会员模块 */
  object Member {
    /** 添加等级 */
    final val MEMBER_VIEW_LIST = "member/view/list"
    /** 新增筛选器 */
    final val MEMBER_SCREEN_ADD = "member/add/memberScreening"
    /** 会员分组 */
    final val VIEW_CENSUS_LIST = "member/view/census/list"
    /** 根据筛选器确定人员 */
    final val VIEW_LIST_BY_SCREEN = "member/view/census/member/list"
    /** 通过预设的条件进行查询会员数据 */
    final val SERCH_MEMBER_BY_TREM = "member/serch/by/screen"
    /** 校验会员是否存在 */
    final val SELECT_MEMBER_BYPHONE = "member/action/selectMemberByPhone"
    /** 根据会员标识查询会员信息 */
    final val SELECT_MEMBER_BY_MEMBER_ID = "member/action/selectMemberById"
    /** 根据会员卡查找员工提成和业绩 */
    final val SELECT_MEMBER_BYPCARD = "member/action/selectMemberByCard"
    /** 根据会员信息标识查询会员信息及账户信息 */
    final val SELECTBY_MEMBERDTO = "member/action/selectByMemberDto"
    /** 根据会员标识分页查询资金流水 */
    final val SELECT_MONEYFLOW = "member/action/selectMoneyFlow"
    /** 删除会员筛选分组 */
    final val DELETE_CENSUS = "member/delete/census"
    /** 根据姓名或者手机号码进行查询会员数据 */
    final val SERCH_MEMBER_BY_CONTENT = "member/serch/by/nameOrPhone"
    /** 总店会员页面 */
    final val VIEW_BASE_MEMBER = "member/base/view/list"
    /** 修改会员资料 */
    final val ACTION_UPDATE_MEMBER_INFO = "member/update/member/info"
    /** 修改会员疗程 */
    final val ACTION_UPDATE_MEMBER_COMBO = "member/action/updateCombo"
    /** 会员删除 */
    final val MEMBER_DELETED = "member/view/member/delete"
    /** 查询门店内所有会员电话和名称 */
    final val SELECT_STORE_MEMBERINFO = "member/action/selectStoreMemberInfo"

    /** 会员错误数据展示页面 */
    final val VIEW_ERROR_MEMBER_LIST = "member/view/error/memberError/list"
    /** 会员错误数据删除操作 */
    final val ACTION_ERROR_MEMBER_DELETE = "member/action/memberError/delete"
    /** 会员余额迁移操作 */
    final val ACTION_MEMBER_ERROR_MIGRATE = "member/action/memberError/migrate"
    /** 会员异常疗程记录查询 */
    final val VIEW_MEMBER_ERROR_COMBO_LIST = "member/view/memberError/combo/list"
    /** 会员异常疗程迁移批量操作 */
    final val ACTION_MEMBER_ERROR_COMBO_MIGRATE_ALL = "member/action/memberError/combo/migrateAll"
    /** 会员异常疗程迁移单个操作 */
    final val ACTION_MEMBER_ERROR_COMBO_MIGRATE_SINGLE = "member/action/memberError/combo/migrateSingle"
    /**会员疗程模态框信息*/
    final val SELECT_COMBO_BY_MEMBER = "member/action/selectComboByMember"
  }

  /** 七牛图片处理模块 */
  object Qiniu {
    /** 获取token */
    final val UPTOKEN = "qiniu/uptoken"
    /** 抓取网络资源上传到七牛 */
    final val FETCH = "qiniu/fetch"
    /** 文字转语音 */
    final val TEXT_TO_VOICE = "qiniu/textToVoice"
    /** 将base64上传至七牛 */
    final val BASE64 = "qiniu/base64"
  }






  /** 微信接口 */
  object Wechat {

    /** 微信七牛上传图片存储图片,用于图片库 */
    final val QINNIU_UPLOAD = "wechat/qiniu/upload"
    /** 新增图文消息 */
    final val UPLOADNEWS = "uploadnews"
    /** 菜单新增 */
    final val ADD_MENU = "add/menu"
    /** 获得菜单 */
    final val GETMENU = "getMenu"
    /** 跳转至菜单设置页面 */
    final val VIEW_LIST_MENU = "wechat/menu"
    /** 删除菜单 */
    final val DELETE_MENU = "delete/menu"
    /** 店铺菜单设置页面 */
    final val VIEW_LIST_STORE_MENU = "wechat/store/menu"
    /** 改变菜单链接地址 */
    final val SET_MENU_URL = "set/menu/url"
    /** 获得单一菜单元素 */
    final val GET_ONE_MENU = "get/one/menu"
    /** 根据openId发送图文消息 */
    final val SEND_MESSAGE_ITEM = "send/item/openId"
    /** 根据openId发送文本消息 */
    final val SEND_MESSAGE_TEXT = "send/text/openId"

    /** 微信授权回调接口 */
    final val CALL_BACK = "/wechat/callback/{openidKey}"
    /** 发起微信支付 */
    final val CREATE_PAY = "/wechat/pay/create"
    /** 微信支付回调接口 */
    final val CALL_BACK_PAY = "/wechat/pay/callback"
    /** 提取微信素材资源 */
    final val FETCH_MEDIA = "wechat/fetch/media"

    /** 为了textarea中的图片上传做的地址 */
    final val UPLOAD_AREA = "wechat/upload/img/textarea/store/{storeId}/user/{userId}"
    /** 进入新增图文消息页面 */
    final val WECHAT_ARTIC_MANAGER = "/wechat/articles/manager/store/{storeId}"
    final val WECHAT_ARTIC_RENEW = "/wechat/articles/renew/store/{storeId}/category/{category}"
    final val WECHAT_PROMOTION_INDEX = "/wechat/promotion/store/{storeId}/{ptyId}/{bargainPromotionId}"
    final val WECHAT_CONNECT_VERIFY = "/wechat/marketing/index"

    final val SHOW_WX_JSSDK_TICKET = "/wechat/store/{storeId}/show/jssdk/ticket"

    final val NEWS_PRAISE = "/wechat/store/{storeId}/news/renew/praise"

    /** 图文消息展示已新增的图文消息 */
    final val WECHAT_STORE_NEWS_INFO = "/wechat/store/{storeId}/show/news/info"
    final val WECHAT_ZF_NEWS_INFO = "/wechat/show/zefun/news/info"
    /** 跳转某个图文消息修改页面，并展示其信息 */
    final val WECHAT_NEWS_RENEW_INFO = "/wechat/store/{storeId}/show/news/renew/item"
    /** 更新微信图片 */
    final val WECHAT_RENEW_IMG = "/wechat/renew/img/store/{storeId}"
    /** 更新图文消息，接口，开始实际修改图文消息 */
    final val UPDATE_ITEM = "wechat/items/update"
    /** 删除图文消息 */
    final val WECHAT_REMOVE_NEWS = "/wechat/store/{storeId}/remove/news"
    /**下载图文消息 */
    final val WECHAT_DOWNLOAD_NEWS = "/wechat/store/{storeId}/download/news"


    final val SHOW_MULTI_NEWS_CONTENT = "/wechat/store/{storeId}/show/multi/news"

    final val SHOW_SCREEN_MEMBER_INFO = "/wechat/store/{storeId}/show/screen/member/info"

    /** 用于预览 */
    final val GET_ITEM_BY_MEDIA_ID = "wechat/get/item/by/mediaId"
    /** 上传图片,生成图片库 */
    final val UPDATE_IMG = "wechat/update/img"
    /** 删除图片 */
    final val DELETE_IMG = "wechat/delete/img"
    /** 进入图文消息发送页面 */
    final val SEND_ITEMS = "/wechat/store/{storeId}/send/items"
    /** 进入自动回复设置页面 */
    final val VIEW_AUTO_REPLY = "wechat/view/auto/reply"
    /** 设置关注回复内容 */
    final val SET_FOLLOW_REPLY = "wechat/set/follow/repy"
    /** 设置消息回复内容 */
    final val SET_TEXT_REPLY = "wechat/set/text/repy"
    /** 删除消息回复 */
    final val DELETE_MSG_AUTO = "wechat/delete/auto/repy"
    /** 更新开发商图文消息 */
    final val UPLOAD_ITEMS = "wechat/upload/items"
    /** 复制菜单信息 */
    final val COPY_MENUS = "wechat/copy/menus"
    /** 删除复制菜单 */
    final val DELETE_COPY_MENUS = "wechat/delete/copy/menus"
    /** 复制单个图文 */
    final val COPY_ITEMS_ZHIFANG = "wechat/items/copy/zhifang"
    /** 查询出本月次数为0的会员 */
    final val CHECK_WECHAT_COUNT = "wechat/check/member/count"
    /** 根据关键字查询图文消息 */
    final val ACTION_SERCH_ITEMS = "wechat/serch/items"
    /** 点赞图文操作 */
    final val PRAISE_ITEMS = "wechat/praise/store/wechat"
    /** 图文预览 */
    final val WECHAT_NEWS_PRERVIEW = "/wechat/news/preview/store/{storeId}"

  }

  object Habits{
    final val ADD_HABIT_TYPE_INFO = "/habit/type/add/info"

    final val SHOW_HABIT_INFO = "/habit/show/info"

    final val SHOW_INFO = "/show/info"
  }


}
