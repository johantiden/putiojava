package github.tidenjohan.putiojava.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Mp4ConversionStatusDto {
    private final String status;
    private final long percentDone;
    private final long size;

    @JsonCreator
    public Mp4ConversionStatusDto(
            @JsonProperty("status") String status,
            @JsonProperty("percent_done") long percentDone,
            @JsonProperty("size") long size) {
        this.status = status;
        this.percentDone = percentDone;
        this.size = size;
    }

    public String getStatus() {
        return status;
    }

    public long getPercentDone() {
        return percentDone;
    }

    public long getSize() {
        return size;
    }
}
