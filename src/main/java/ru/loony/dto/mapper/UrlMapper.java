package ru.loony.dto.mapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.loony.dto.CreatedUrlDto;
import ru.loony.model.UrlEntity;

@Component
public class UrlMapper {
    @Value("${redir.base.hosturl}")
    private String redirBaseUrl;

    public CreatedUrlDto convertToUrlDto(UrlEntity urlEntity) {
        if (urlEntity == null)
            return new CreatedUrlDto();

        return new CreatedUrlDto(getFullRedirAddress(urlEntity.getShortenUrl()), urlEntity.getFullUrl());
    }


    public String getFullRedirAddress(String shortUrlPart) {
        return redirBaseUrl + shortUrlPart;
    }

}
