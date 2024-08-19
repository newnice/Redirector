package ru.loony.dao.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.loony.dao.UrlRepository;
import ru.loony.model.UrlEntity;

public interface JpaUrlRepository extends JpaRepository<UrlEntity, Long>, UrlRepository {

    @Override
    @Query(value = "SELECT NEXTVAL('SEQUENCE_URLS')", nativeQuery = true)
    long nextId();
}
