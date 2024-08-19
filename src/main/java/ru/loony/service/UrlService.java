package ru.loony.service;

import ru.loony.model.UrlEntity;

import java.net.URL;

public interface UrlService {

    UrlEntity addUrlWithCustomPart(URL fullUrl, String customUrlPart);
    UrlEntity addShortenUrl(URL fullUrl);
    String getFullUrlByShortenUrl(String shortenUrl);
}
