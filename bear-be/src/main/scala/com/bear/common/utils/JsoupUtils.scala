package com.bear.common.utils

import org.jsoup.Jsoup
import scala.collection.JavaConversions._
/**
  * Created by tanghong on 16/7/8.
  */
object JsoupUtils {

  def disposeImgUrl(content: String)(func: String => Option[String]): String = {
    val doc = Jsoup.parse(content)
    // akka 延时处理
    doc.getElementsByTag("img").iterator.foreach{ img =>
      //println(img)
      val attrOpt = Option(img.attr("src"))
      val url = attrOpt.getOrElse(img.attr("data-src"))
      val replaceUrlOpt = if (url != null && url != "") func(url) else None
      attrOpt match {
        case Some(_) =>
          replaceUrlOpt.map{ m =>
            img.attr("data-src", m)
            img.attr("src", m)
          }
        case _ =>
          replaceUrlOpt.map{ m =>
            img.attr("src", m)
          }
      }
    }
    doc.html
  }
}
