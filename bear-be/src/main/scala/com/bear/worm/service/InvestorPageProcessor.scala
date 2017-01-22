package com.bear.worm.service

import com.bear.worm.model.Investor
import com.jayway.jsonpath.JsonPath
import org.json4s.JsonAST.{JArray, JString}
import org.json4s.StringInput
import org.json4s.native.JsonMethods._
import us.codecraft.webmagic.{Page, Site}
import us.codecraft.webmagic.processor.PageProcessor
import us.codecraft.webmagic.selector.{Html, HtmlNode, Selectors}

import scala.collection.JavaConverters._
/**
  * Created by tanghong on 19/01/2017.
  */
class InvestorPageProcessor extends PageProcessor{

  override def getSite: Site = Site.me().setRetryTimes(2).setSleepTime(1000)


  override def process(page: Page): Unit = {
    val result = parse(page.getJson.get())
    val list = {
      result.\\("data").\\("pageData").\\("data") match {
        case json: JArray =>
          json.arr.map{ m =>
            val name = Option(compact(render(m \ "name"))).getOrElse("")
            val company = Option(compact(render(m \ "orgName"))).getOrElse("")
            val position = Option(compact(render(m \\ "position"))).getOrElse("")
            val brief = Option(compact(render(m \\ "brief"))).getOrElse("")
            val investCount = compact(render(m \\ "investCnt"))
            val industry = {
              m \\ "industry" match{
                case js: JArray => js.arr.map{ case JString(v) => v}.mkString(",")
                case _ => ""
              }
            }
            val city = {
              m \\ "city" match{
                case js: JArray => js.arr.map{ case JString(v) => v}.mkString(",")
                case _ => ""
              }
            }
            val phase = {
              m \\ "phase" match{
                case js: JArray => js.arr.map{ case JString(v) => v}.mkString(",")
                case _ => ""
              }
            }
            new Investor(name, company, industry, phase, city, position, investCount, brief)
          }
        case _ => Nil
      }
    }
   /*val pageList = page.getHtml.xpath("//div[@class='result-content']/table/tbody/tr").all()
   val list = collectionAsScalaIterable(pageList).map{ str =>
     val sel = Html.create(str)
     val name = sel.xpath("//div[@class='investor-name']/a/text()").get()
     val company = sel.xpath("//div/div[@class='investor-intro ng-binding']/text()").get()
     val territory = sel.xpath("//section[contains(@ng-repeat, 'item in obj.industry')]/text()").all()
     val financingStage = sel.xpath("//section[contains(@ng-repeat, 'item in obj.phase')]/text()").all()
     val address = sel.xpath("//section[contains(@ng-repeat, 'item in obj.city')]/text()").all()
     new Investor(
       name,
       Option(company).getOrElse(""),
       collectionAsScalaIterable(territory).mkString(","),
       collectionAsScalaIterable(financingStage).mkString(","),
       collectionAsScalaIterable(address).mkString(",")
     )
   }*/
   page.putField("investorList", asJavaCollection(list))
  }

}
