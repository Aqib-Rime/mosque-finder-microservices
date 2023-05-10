package com.chotonakib.mosqueservice.services;

import com.chotonakib.mosqueservice.models.entity.UserInfoEntity;
import com.chotonakib.mosqueservice.repository.UserInfoEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {
    private final UserInfoEntityRepository repository;

    @KafkaListener(topics = "${user.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeMessage(UserInfoEntity userInfo, Acknowledgment ack) {
        repository.save(userInfo);
        ack.acknowledge();
    }
}
