package ru.loony.dto;

public record CreatedUrlDto(String shortUrl, String fullUrl) {
    public CreatedUrlDto() {
        this("", "");
    }
}
