package com.kafka.sample.demo;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.adapter.ConsumerRecordMetadata;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {
    @KafkaListener(topics = "aditya", groupId = "groupId")
    void listener(String data, ConsumerRecordMetadata metadata){
        System.out.println("Listener Received: " + data);
        System.out.println("Listener Received Has offset: " + metadata.hasOffset());
        System.out.println("Listener Received topic: " + metadata.topic());
        System.out.println("Listener Received timstamp: " + metadata.timestamp());
    }
}