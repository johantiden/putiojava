package github.tidenjohan.putiojava.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FileResponseDto {

    private final String status;
    private final FileDto file;

    @JsonCreator
    public FileResponseDto(
            @JsonProperty("status") String status,
            @JsonProperty("file") FileDto file) {
        this.status = status;
        this.file = file;
    }

    public FileDto getFile() {
        return file;
    }

    public String getStatus() {
        return status;
    }
}
