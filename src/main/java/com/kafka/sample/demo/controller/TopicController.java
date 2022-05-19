package com.kafka.sample.demo.controller;

import com.kafka.sample.demo.models.MessageRequest;
import org.apache.kafka.clients.admin.AdminClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("api/v1/topics")
public class TopicController {
    private KafkaTemplate<String, String> kafkaTemplate;

    private KafkaAdmin kafkaAdmin;
    private AdminClient adminClient;

    public TopicController(KafkaAdmin kafkaAdmin, KafkaTemplate<String, String> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaAdmin = kafkaAdmin;
        this.adminClient = AdminClient.create(kafkaAdmin.getConfigurationProperties());
    }

    @PostMapping(path = "/{topic}")
    public void create(@PathVariable String topic){
        TopicBuilder.name(topic).build();
    }

    @PostMapping(path = "/{topic}/delete")
    public void delete(@PathVariable String topic){
        this.adminClient.deleteTopics(Collections.singletonList(topic));
    }
}
