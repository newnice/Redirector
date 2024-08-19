package ru.loony.exception;

public class NotFoundRedirectionUrlException extends RuntimeException {
    private final String wrongUrl;

    public NotFoundRedirectionUrlException(String url) {
        wrongUrl = url;
    }

    public String getWrongUrl() {
        return wrongUrl;
    }

    @Override
    public String getMessage() {
        return  "Not found redirection url: " + wrongUrl;
    }
}
