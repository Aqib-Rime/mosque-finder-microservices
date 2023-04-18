package com.chotonakib.mosqueservice.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@NoArgsConstructor
@Getter
public class MosqueNotFoundException extends Exception {
    private String message;

    public MosqueNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
