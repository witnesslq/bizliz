package com.bear.worm.model

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.{Document, Field, FieldType}
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Service

import scala.beans.BeanProperty
/**
  * Created by tanghong on 19/01/2017.
  */
@Document(indexName = "investors")
class Investor {
  @Id
  @BeanProperty var id: String = _
  @Field(`type` = FieldType.Nested)
  @BeanProperty var name: String = _
  @Field(`type` = FieldType.Nested)
  @BeanProperty var position: String = _
  @Field(`type` = FieldType.Nested)
  @BeanProperty var company: String = _
  @Field(`type` = FieldType.Nested)
  @BeanProperty var territory: String = _
  @Field(`type` = FieldType.Nested)
  @BeanProperty var phase: String = _
  @Field(`type` = FieldType.Nested)
  @BeanProperty var investCount: String = _
  @Field(`type` = FieldType.Nested)
  @BeanProperty var city: String = _
  @Field(`type` = FieldType.Nested)
  @BeanProperty var brief: String = _

  def this (name: String, company: String, territory: String,
    financingStage: String, address: String,
    position: String, investCount: String, brief: String){
    this
    this.name = name
    this.company = company
    this.territory = territory
    this.phase = financingStage
    this.city = address
    this.position = position
    this.investCount = investCount
    this.brief = brief
  } 

}

@Service
object Investor {
  import scala.collection.JavaConverters._

  val COLL_NAME = "investors"

  @Autowired
  @BeanProperty var bearTemplate: MongoTemplate = _

  def add(inv: Seq[Investor]): Unit = {
    inv.foreach{ f =>
      println(f.company)
      println(f.brief)
      println(f.id)
      println(f.city)
      println(f.phase)
      println("________")
    }
    val list = asJavaCollection(inv)
    bearTemplate.insert(list, COLL_NAME)
  }

  def add(inv: Investor): String = {
    bearTemplate.insert(Investor, COLL_NAME)
    inv.id
  }

}