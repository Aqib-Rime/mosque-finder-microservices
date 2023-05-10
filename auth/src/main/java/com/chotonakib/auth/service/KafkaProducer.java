package com.chotonakib.auth.service;

import com.chotonakib.auth.model.entity.UserInfoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String, UserInfoEntity> kafkaTemplate;

    public void sendMessage(String topic, UserInfoEntity userInfo) {
        kafkaTemplate.send(topic, userInfo);
    }
}
