package com.chotonakib.mosqueservice.services;

import com.chotonakib.mosqueservice.api.dto.request.UpdateMosqueRequestDto;
import com.chotonakib.mosqueservice.exception.AlreadyMosqueExistsException;
import com.chotonakib.mosqueservice.exception.MosqueNotFoundException;
import com.chotonakib.mosqueservice.models.entity.AddressEntity;
import com.chotonakib.mosqueservice.models.entity.MosqueDetailsEntity;
import com.chotonakib.mosqueservice.models.enums.MosqueStatus;
import com.chotonakib.mosqueservice.repository.MosqueDetailsEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MosqueService {
    private final MosqueDetailsEntityRepository mosqueDetailsEntityRepository;
    private final EnumService enumService;
    private final AddressEntityService addressEntityService;

    public List<MosqueDetailsEntity> getAllMosques(double latitude, double longitude, double radius) {
        List<AddressEntity> addresses = addressEntityService.getAddressWithInRange(latitude, longitude, radius);
        List<MosqueDetailsEntity> mosques = mosqueDetailsEntityRepository.findByAddressEntityIdIn(addresses.stream().map(AddressEntity::getId).toList());
        return mosques.stream().filter(mosque -> !mosque.getIsDeleted()).toList();
    }

    public MosqueDetailsEntity getSingleMosque(String name) throws MosqueNotFoundException {
        MosqueDetailsEntity entity = mosqueDetailsEntityRepository.findByNameIgnoreCase(name).orElseThrow(() -> {
            log.error("Mosque not found for the name : {}", name);
            return new MosqueNotFoundException();
        });
        if (entity.getIsDeleted()) {
            log.error("Mosque is soft deleted for the name : {}", name);
            throw new MosqueNotFoundException();
        }
        return entity;
    }

    public void save(MosqueDetailsEntity mosqueDetails) throws AlreadyMosqueExistsException {
        addressEntityService.saveNewMosque(mosqueDetails.getAddressEntity()).orElseThrow(AlreadyMosqueExistsException::new);
        mosqueDetails.setCreatedAt(LocalDateTime.now());
        mosqueDetails.setUpdatedAt(LocalDateTime.now());
        mosqueDetails.setIsDeleted(false);
        mosqueDetails.setMosqueStatus(MosqueStatus.PENDING);
        mosqueDetailsEntityRepository.save(mosqueDetails);
    }

    public void updateMosque(UpdateMosqueRequestDto dto) throws MosqueNotFoundException {
        MosqueDetailsEntity mosque = getSingleMosque(dto.getName());
        if (!dto.getUpdatedName().isEmpty()) {
            mosque.setName(dto.getUpdatedName());
        }
        if (!dto.getMosqueStatus().isEmpty()) {
            MosqueStatus status = enumService.statusStringToEnum(dto.getMosqueStatus());
            mosque.setMosqueStatus(status);
        }
        if (!dto.getImageUrl().isEmpty()) {
            mosque.setImageUrl(dto.getImageUrl());
        }
        if (!dto.getComment().isEmpty()) {
            mosque.setComment(dto.getComment());
        }
        if (!dto.getAddressString().isEmpty() || !dto.getLatitude().isNaN() || !dto.getLongitude().isNaN()) {
            AddressEntity address = AddressEntity.builder().address(dto.getAddressString()).latitude(dto.getLatitude()).longitude(dto.getLongitude()).build();
            mosque.setAddressEntity(address);
        }
        mosque.setUpdatedAt(LocalDateTime.now());
        mosqueDetailsEntityRepository.save(mosque);
    }

    public void deleteMosque(String name) throws MosqueNotFoundException {
        MosqueDetailsEntity entity = mosqueDetailsEntityRepository.findByNameIgnoreCase(name).orElseThrow(MosqueNotFoundException::new);
        entity.setIsDeleted(true);
        mosqueDetailsEntityRepository.save(entity);
    }
}
