package github.tidenjohan.putiojava.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ListFilesDto {
    private final String cursor;
    private final List<FileDto> files;
    private final FileDto parent;
    private final long total;

    @JsonCreator
    public ListFilesDto(
            @JsonProperty("cursor") String cursor,
            @JsonProperty("files") List<FileDto> files,
            @JsonProperty("parent") FileDto parent,
            @JsonProperty("total") long total) {
        this.cursor = cursor;
        this.files = files;
        this.parent = parent;
        this.total = total;
    }

    public String getCursor() {
        return cursor;
    }

    public List<FileDto> getFiles() {
        return files;
    }

    public FileDto getParent() {
        return parent;
    }

    public long getTotal() {
        return total;
    }
}
