package ru.loony.model;

import jakarta.persistence.*;

@Entity
@Table(name = "TABLE_URLS", schema = "MAIN")
public class UrlEntity {
    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "SHORTEN_URL", nullable = false)
    private String shortenUrl;

    @Column(name = "FULL_URL", nullable = false)
    private String fullUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShortenUrl() {
        return shortenUrl;
    }

    public void setShortenUrl(String urlRedir) {
        this.shortenUrl = urlRedir;
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public void setFullUrl(String baseUrl) {
        this.fullUrl = baseUrl;
    }

}