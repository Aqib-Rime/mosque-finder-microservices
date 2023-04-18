package com.chotonakib.mosqueservice.services;

import com.chotonakib.mosqueservice.api.dto.mapper.NotificationEntityDtoMapper;
import com.chotonakib.mosqueservice.api.dto.mapper.UserInfoEntityMapper;
import com.chotonakib.mosqueservice.api.dto.response.NotificationEntityDto;
import com.chotonakib.mosqueservice.api.dto.response.UserInfoEntityDto;
import com.chotonakib.mosqueservice.exception.UserDoesNotExistException;
import com.chotonakib.mosqueservice.models.entity.UserInfoEntity;
import com.chotonakib.mosqueservice.models.enums.Badge;
import com.chotonakib.mosqueservice.repository.UserInfoEntityRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserInfoEntityRepository userInfoEntityRepository;
    private final NotificationEntityDtoMapper notificationEntityDtoMapper;
    private final UserInfoEntityMapper userInfoEntityMapper;

    public List<NotificationEntityDto> getUserNotifications(String email) throws UserDoesNotExistException {
        UserInfoEntity userInfoEntity = userInfoEntityRepository.findByEmailIgnoreCase(email).orElseThrow(UserDoesNotExistException::new);
        return userInfoEntity.getNotificationEntities().stream().map(notificationEntityDtoMapper::toDto).toList();
    }

    public List<NotificationEntityDto> getUnsentNotifications(String email) throws UserDoesNotExistException {
        List<NotificationEntityDto> userNotifications = getUserNotifications(email);
        return userNotifications.stream().filter(dto -> !dto.getIsDelivered()).toList();
    }

    public UserInfoEntity findUser(String email) throws UserDoesNotExistException {
        return userInfoEntityRepository.findByEmailIgnoreCase(email).orElseThrow(UserDoesNotExistException::new);
    }

    public void addUser(UserInfoEntityDto dto) {
        UserInfoEntity userInfoEntity = userInfoEntityMapper.toEntity(dto);
        userInfoEntity.setCreatedAt(LocalDateTime.now());
        userInfoEntity.setUpdatedAt(LocalDateTime.now());
        userInfoEntity.setBadge(Badge.BRONZE);
        userInfoEntity.setUserAwardPoint(0);
        if (StringUtils.isEmpty(userInfoEntity.getUserDisplayName())) {
            userInfoEntity.setUserDisplayName(userInfoEntity.getUserName());
        }
        userInfoEntityRepository.save(userInfoEntity);
    }
}
