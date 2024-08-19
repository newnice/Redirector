package ru.loony.dao;

import ru.loony.model.UrlEntity;


public interface  UrlRepository  {
    UrlEntity findFirstByFullUrlEquals(String url);
    UrlEntity findFirstByShortenUrlEquals(String url);

    UrlEntity save(UrlEntity tableUrl);
    long nextId();
}
