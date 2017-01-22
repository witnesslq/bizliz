package com.bear.web.service

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.listener.MessageListener
import org.springframework.stereotype.Service

/**
  * Created by Apple on 22/01/2017.
  */
@Service
class KafkaService extends MessageListener[Int, String]{
  override def onMessage(data: ConsumerRecord[Int, String]): Unit = {
    println("---------" + data)
  }
}
