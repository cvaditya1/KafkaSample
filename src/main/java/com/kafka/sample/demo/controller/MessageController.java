package com.kafka.sample.demo.controller;

import com.kafka.sample.demo.models.MessageRequest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import static java.util.Objects.nonNull;

@RestController
@RequestMapping("api/v1/messages")
public class MessageController {

    private KafkaTemplate<String, String> kafkaTemplate;

    public MessageController(KafkaTemplate<String, String> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping(path = "/{topic}")
    public void publish(@RequestBody MessageRequest request, @PathVariable(required = false) String topic){
        if(nonNull(topic)){
            kafkaTemplate.send(topic, request.getMessage());
        } else {
            kafkaTemplate.send("aditya", request.getMessage());
        }
    }
}
