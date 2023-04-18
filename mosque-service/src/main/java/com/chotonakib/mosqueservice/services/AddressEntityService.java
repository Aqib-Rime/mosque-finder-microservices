package com.chotonakib.mosqueservice.services;

import com.chotonakib.mosqueservice.models.entity.AddressEntity;
import com.chotonakib.mosqueservice.repository.AddressEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressEntityService {
    private final AddressEntityRepository addressEntityRepository;
    @Value("${no.mosque.radius.range}")
    private double NO_MOSQUE_RADIUS_RANGE;

    public Optional<Object> saveNewMosque(AddressEntity address) {
        List<AddressEntity> rangedAddress = getAddressWithInRange(address.getLatitude(), address.getLongitude(), NO_MOSQUE_RADIUS_RANGE);
        if (!CollectionUtils.isEmpty(rangedAddress)) {
            return Optional.empty();
        }
        addressEntityRepository.save(address);
        return Optional.of(new Object());
    }

    public List<AddressEntity> getAddressWithInRange(double latitude, double longitude, double radius) {
        List<AddressEntity> allAddresses = addressEntityRepository.findAll();
        return allAddresses.stream().filter(address -> {
            double dt = distance(address.getLatitude(), address.getLongitude(), latitude, longitude);
            return dt <= radius;
        }).toList();
    }

    // distance using the Haversine formula
    private double distance(double lat1, double lon1, double lat2, double lon2) {
        double R = 6371e3; // Earth radius in meters
        double lat1Radians = Math.toRadians(lat1);
        double lat2Radians = Math.toRadians(lat2);
        double deltaLatRadians = Math.toRadians(lat2 - lat1);
        double deltaLonRadians = Math.toRadians(lon2 - lon1);

        double a = Math.sin(deltaLatRadians / 2) * Math.sin(deltaLatRadians / 2)
                + Math.cos(lat1Radians) * Math.cos(lat2Radians)
                * Math.sin(deltaLonRadians / 2) * Math.sin(deltaLonRadians / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c;
    }
}
