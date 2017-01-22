package com.bear.web.service

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

/**
  * Created by Apple on 22/01/2017.
  */
@Service
class KafkaListenerService {

  @KafkaListener(id = "0", topics = Array("testTopic"), containerFactory = "kafkaListenerContainerFactory")
  def listen(data: ConsumerRecord[Int, String]): Unit = {
    println("---1111-----" + data)
  }

}
