package ru.loony.exception;

public class WrongUrlFormatException extends RuntimeException {
    private final String wrongUrl;

    public WrongUrlFormatException(String url) {
        wrongUrl = url;
    }

    public String getWrongUrl() {
        return wrongUrl;
    }

    @Override
    public String getMessage() {
        return  "Wrong URL format: " + wrongUrl;
    }
}
