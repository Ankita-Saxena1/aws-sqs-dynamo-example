package com.confluentkafka.my_confluent_kafka_project;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

@Service
public class Producer {

    private static final String TOPIC = "purchases";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String key, String value) {
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(TOPIC, key, value);

        future.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.print(String.format("Produced event to topic %s: key = %-10s value = %s",
                        result.getRecordMetadata().topic(), key, value));
            } else {
                ex.printStackTrace(System.out);
            }
        });

    }

}