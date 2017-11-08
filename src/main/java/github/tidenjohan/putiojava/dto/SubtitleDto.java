package github.tidenjohan.putiojava.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SubtitleDto {

    private final String key;
    private final String language;
    private final String name;
    private final String source;

    @JsonCreator
    public SubtitleDto(
            @JsonProperty("key") String key,
            @JsonProperty("language") String language,
            @JsonProperty("name") String name,
            @JsonProperty("source") String source) {

        this.key = key;
        this.language = language;
        this.name = name;
        this.source = source;
    }

    public String getKey() {
        return key;
    }

    public String getLanguage() {
        return language;
    }

    public String getName() {
        return name;
    }

    public String getSource() {
        return source;
    }
}
