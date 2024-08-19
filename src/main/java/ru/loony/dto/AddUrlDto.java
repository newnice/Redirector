package ru.loony.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import ru.loony.exception.WrongUrlFormatException;
import ru.loony.util.Patterns;

import java.net.URL;
import java.net.URI;
import java.util.Objects;

public final class AddUrlDto {
    @NotBlank
    @Pattern(regexp = Patterns.URL_PATTERN)
    private final String fullUrl;
    private final boolean bApplyCustom;
    @Pattern(regexp = Patterns.CUSTOM_URL_PART_PATTERN)
    private final String customUrlPart;

    public AddUrlDto(String fullUrl, boolean bApplyCustom, String customUrl) {
        this.fullUrl = fullUrl;
        this.bApplyCustom = bApplyCustom;
        this.customUrlPart = customUrl;
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public URL getFullUrlAsURL() {
        return convertToURL(fullUrl);
    }

    public boolean getApplyCustom() {
        return bApplyCustom;
    }

    public String getCustomUrlPart() {
        return customUrlPart;
    }

    private URL convertToURL(String urlAsString) {
        try {
            return new URI(urlAsString).toURL();
        } catch (Exception e) {
            throw new WrongUrlFormatException(urlAsString);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (AddUrlDto) obj;
        return Objects.equals(this.fullUrl, that.fullUrl) &&
                this.bApplyCustom == that.bApplyCustom &&
                Objects.equals(this.customUrlPart, that.customUrlPart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullUrl, bApplyCustom, customUrlPart);
    }

    @Override
    public String toString() {
        return "AddUrlDto[" +
                "fullUrl=" + fullUrl + ", " +
                "bApplyCustom=" + bApplyCustom + ", " +
                "customUrl=" + customUrlPart + ']';
    }
}
