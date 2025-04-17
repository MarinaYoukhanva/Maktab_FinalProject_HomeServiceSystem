package com.freshome.exception;


import com.freshome.entity.base.BaseEntity;

public class NotFoundException extends RuntimeException {

    public NotFoundException(Class<? extends BaseEntity<Long>> theClass, Long id) {
        super(theClass.getSimpleName() + " with id [ " + id + " ] does not exist! ");
    }

    public NotFoundException(Class<? extends BaseEntity<Long>> theClass, String username) {
        super(theClass.getSimpleName() + " with username [ " + username + " ] does not exist! ");
    }

    public NotFoundException(String message) {
        super(message);
    }
}
