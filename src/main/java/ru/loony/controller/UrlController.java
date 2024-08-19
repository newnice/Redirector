package ru.loony.controller;


import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import ru.loony.dto.AddUrlDto;
import ru.loony.dto.CreatedUrlDto;
import ru.loony.dto.mapper.UrlMapper;
import ru.loony.model.UrlEntity;
import ru.loony.service.UrlService;


@RestController
@RequestMapping("/urls")
@Tag(
        name = "Менеджмент URLs",
        description = "Создание короткого URL на основании кастомного или с применением алгоритма сокращения"
)
public class UrlController {

    private final UrlService urlService;
    private final UrlMapper urlMapper;

    public UrlController(UrlService urlService, UrlMapper mapper) {
        this.urlService = urlService;
        this.urlMapper = mapper;
    }

    @PostMapping()
    CreatedUrlDto addUrl(
            @Parameter(description = "Структура с данными для сокращения URL")
            @RequestBody @Valid AddUrlDto urlDto) {

        UrlEntity entity;
        if (urlDto.getApplyCustom()) {
            entity = urlService.addUrlWithCustomPart(urlDto.getFullUrlAsURL(), urlDto.getCustomUrlPart());
        } else {
            entity = urlService.addShortenUrl(urlDto.getFullUrlAsURL());
        }
        return urlMapper.convertToUrlDto(entity);
    }

}
