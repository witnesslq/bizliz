package com.bear.web.model

import com.bear.common.consts.Consts
import com.bear.common.utils.JodaUtils
import com.bear.web.dto.ResultDto
import org.elasticsearch.index.query.SimpleQueryStringBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.{Document, Field, FieldType}
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate
import org.springframework.data.elasticsearch.core.query._
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Service

import scala.beans.BeanProperty

/**
  * Created by Apple on 26/08/2016.
  */
@Document(indexName = "habit_types")
class HabitTypes {
  @Id
  @BeanProperty var id: String = _
  @Field(`type` = FieldType.Nested)
  @BeanProperty var accountId: String = _
  @Field(`type` = FieldType.Nested)
  @BeanProperty var name: String = _
  @Field(`type` = FieldType.Nested)
  @BeanProperty var remark: String = _
  @Field(`type` = FieldType.Nested)
  @BeanProperty var createon: String = _
  @Field(`type` = FieldType.Nested)
  @BeanProperty var state: Int = _

  def this(name: String, remark: String){
    this
    this.accountId = "1111"
    this.name = name
    this.remark = remark
    this.createon = JodaUtils.getDateTime
    this.state = Consts.StatusCons.enable
  }
}

@Service
object HabitTypes{
  import scala.collection.JavaConversions._

  @Autowired
  @BeanProperty var bearTemplate: MongoTemplate = _

  /*@Autowired
  @BeanProperty var elasticsearchTemplate: ElasticsearchTemplate = _*/

  def add = {
    val n = new HabitTypes("阅读类型11111", "33333")
    bearTemplate.insert(n, "habit_types")
   /* val indexQuery = new IndexQuery();
    indexQuery.setId(n.getId());
    indexQuery.setObject(n);*/

    //elasticsearchTemplate.putMapping("habit_types", "habit", classOf[HabitTypes])
    //val doc = elasticsearchTemplate.index(indexQuery)


    "11111"
  }

  def find = {
    val list = bearTemplate.find(new Query(), classOf[HabitTypes], "habit_types")
    /*println("111111111111")
    val searchQuery = new NativeSearchQueryBuilder().build()
    val qf = elasticsearchTemplate.queryForList(searchQuery, classOf[HabitTypes])
    println(elasticsearchTemplate.indexExists("habit_types"))
    qf.foreach{ f =>
      println(f.name)
    }*/
    ResultDto(0, list.map(_.name))
  }

}