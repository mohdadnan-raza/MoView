package com.moview.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ResourceNotFoundException extends RuntimeException {
	public ResourceNotFoundException(String message){
        super(message);
    }
}
