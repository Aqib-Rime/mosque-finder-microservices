package com.chotonakib.mosqueservice.services;

import com.chotonakib.mosqueservice.models.enums.MosqueStatus;
import org.springframework.stereotype.Service;

@Service
public class EnumService {
    public MosqueStatus statusStringToEnum(String mosqueStatus) {
        String status = mosqueStatus.toUpperCase();
        if (status.equals("ACCEPTED")) return MosqueStatus.ACCEPTED;
        if (status.equals("REJECTED")) return MosqueStatus.REJECTED;
        return MosqueStatus.PENDING;
    }

    public String statusEnumToString(MosqueStatus status) {
        return status.name().toUpperCase();
    }
}
