package ru.loony.controller;


import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.loony.exception.NotFoundRedirectionUrlException;
import ru.loony.dto.mapper.UrlMapper;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import ru.loony.service.UrlService;

@Validated
@Controller
@RequestMapping(value = "/${redir.base.url}")
@Tag(
        name = "Редиректор",
        description = "Осуществляет редиректы с сокращенного URL в полный"
)
public class RedirectorController {

    private final UrlService urlService;
    private final UrlMapper urlMapper;

    public RedirectorController(UrlService urlService, UrlMapper urlMapper) {
        this.urlService = urlService;
        this.urlMapper = urlMapper;
    }

    @Operation(summary = "Проверка жизнеспособности контроллера", hidden = true)
    @GetMapping("/hello")
    @ResponseBody
    String sayHello() {
        return "Another one 'Hello, World!'";
    }


    @Operation(summary = "Редирект")
    @GetMapping("/{shortenUrl}")
    String makeRedirect(
            @Parameter(description = "Сокращенный URL")
            @PathVariable("shortenUrl") @NotBlank String shortenUrl) {
        var fullUrl = urlService.getFullUrlByShortenUrl(shortenUrl);
        if (fullUrl != null) {
            return "redirect:" + fullUrl;
        }
        throw new NotFoundRedirectionUrlException(urlMapper.getFullRedirAddress(shortenUrl));
    }
}
