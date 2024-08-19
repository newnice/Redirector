package ru.loony.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.loony.model.UrlEntity;
import ru.loony.dao.UrlRepository;
import ru.loony.service.UrlService;

import java.net.*;
import java.util.Base64;

@Service
public class UrlServiceImpl implements UrlService {

    private final UrlRepository urlRepository;

    public UrlServiceImpl(UrlRepository urlRepository) {

        this.urlRepository = urlRepository;
    }

    private String makeShortUrlPartById(long index) {
        return Base64.getEncoder().encodeToString(Long.valueOf(index).toString().getBytes());
    }

    @Override
    @Transactional
    public UrlEntity addUrlWithCustomPart(URL fullUrl, String customUrlPart) {
        return saveUrlData(urlRepository.nextId(), fullUrl.toString(), customUrlPart);
    }

    @Override
    @Transactional
    public UrlEntity addShortenUrl(URL fullUrl) {

        var strUrl = fullUrl.toString();
        var tableUrl = urlRepository.findFirstByFullUrlEquals(strUrl);
        if (tableUrl != null) {
            return tableUrl;
        }

        var urlId = urlRepository.nextId();
        return saveUrlData(urlId, strUrl, makeShortUrlPartById(urlId));
    }

    @Override
    public String getFullUrlByShortenUrl(String shortenUrl) {
        var entity = urlRepository.findFirstByShortenUrlEquals(shortenUrl);
        if (entity != null)
            return entity.getFullUrl();
        return null;
    }

    private UrlEntity saveUrlData(long urlId, String fullUrl, String shortenUrl) {
        var tableUrl = new UrlEntity();
        tableUrl.setId(urlId);
        tableUrl.setFullUrl(fullUrl);
        tableUrl.setShortenUrl(shortenUrl);
        return urlRepository.save(tableUrl);
    }
}
