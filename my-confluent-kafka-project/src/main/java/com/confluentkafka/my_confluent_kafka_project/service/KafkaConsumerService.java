package com.confluentkafka.my_confluent_kafka_project.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    @KafkaListener(id = "myConsumer", topics = "purchases", groupId = "spring-boot", autoStartup = "false")
    public void listen(String value,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(KafkaHeaders.RECEIVED_KEY) String key) {
        System.out.println(String.format("Consumed event from topic %s: key = %-10s value = %s", topic, key, value));
    }
}
