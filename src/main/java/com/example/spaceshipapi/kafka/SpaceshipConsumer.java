package com.example.spaceshipapi.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SpaceshipConsumer {
    @KafkaListener(topics = "spaceship_topic", groupId = "spaceship_group")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
    }
}
