package com.TrainingCenter.exceptions;

public class DataNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -2105239003165681926L;

    private final long id;

    public DataNotFoundException(String message, long id) {
        super(message);
        this.id = id;
    }

    public long getId() {
        return id;
    }

}
