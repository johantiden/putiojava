package github.tidenjohan.putiojava.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ListSubtitlesDto {


    private final String status;
    private final String defaultSubtitleKey;
    private final List<SubtitleDto> subtitles;

    @JsonCreator
    public ListSubtitlesDto(
            @JsonProperty("status") String status,
            @JsonProperty("default") String defaultSubtitleKey,
            @JsonProperty("subtitles") List<SubtitleDto> subtitles) {
        this.status = status;
        this.defaultSubtitleKey = defaultSubtitleKey;
        this.subtitles = subtitles;
    }

    public String getStatus() {
        return status;
    }

    public String getDefaultSubtitleKey() {
        return defaultSubtitleKey;
    }

    public List<SubtitleDto> getSubtitles() {
        return subtitles;
    }

}
