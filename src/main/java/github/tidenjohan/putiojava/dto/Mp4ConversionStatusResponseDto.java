package github.tidenjohan.putiojava.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Mp4ConversionStatusResponseDto {

    private final String status;
    private final Mp4ConversionStatusDto mp4;

    @JsonCreator
    public Mp4ConversionStatusResponseDto(
            @JsonProperty("status") String status,
            @JsonProperty("mp4") Mp4ConversionStatusDto mp4) {
        this.status = status;
        this.mp4 = mp4;
    }

    public String getStatus() {
        return status;
    }

    public Mp4ConversionStatusDto getMp4() {
        return mp4;
    }
}
