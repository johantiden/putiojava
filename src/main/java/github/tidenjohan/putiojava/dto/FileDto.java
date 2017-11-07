package github.tidenjohan.putiojava.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FileDto extends BaseDto {
    private final String contentType;
    private final String crc32;
    private final String createdAt;
    private final String firstAccessedAt;
    private final String icon;
    private final long id;
    private final boolean isMp4Available;
    private final boolean isShared;
    private final String name;
    private final String opensubtitlesHash;
    private final long parentId;
    private final String screenshot;
    private final long size;

    @JsonCreator
    public FileDto(
            @JsonProperty("status") String status,
            @JsonProperty("content_type") String contentType,
            @JsonProperty("crc32") String crc32,
            @JsonProperty("created_at") String createdAt,
            @JsonProperty("first_accessed_at") String firstAccessedAt,
            @JsonProperty("icon") String icon,
            @JsonProperty("id") long id,
            @JsonProperty("is_mp4_available") boolean isMp4Available,
            @JsonProperty("is_shared") boolean isShared,
            @JsonProperty("name") String name,
            @JsonProperty("opensubtitles_hash") String opensubtitlesHash,
            @JsonProperty("parent_id") long parentId,
            @JsonProperty("screenshot") String screenshot,
            @JsonProperty("size") long size) {
        super(status);
        this.contentType = contentType;
        this.crc32 = crc32;
        this.createdAt = createdAt;
        this.firstAccessedAt = firstAccessedAt;
        this.icon = icon;
        this.id = id;
        this.isMp4Available = isMp4Available;
        this.isShared = isShared;
        this.name = name;
        this.opensubtitlesHash = opensubtitlesHash;
        this.parentId = parentId;
        this.screenshot = screenshot;
        this.size = size;
    }

    public String getContentType() {
        return contentType;
    }

    public String getCrc32() {
        return crc32;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getFirstAccessedAt() {
        return firstAccessedAt;
    }

    public String getIcon() {
        return icon;
    }

    public long getId() {
        return id;
    }

    public boolean is_mp4_available() {
        return isMp4Available;
    }

    public boolean is_shared() {
        return isShared;
    }

    public String getName() {
        return name;
    }

    public String getOpensubtitlesHash() {
        return opensubtitlesHash;
    }

    public long getParentId() {
        return parentId;
    }

    public String getScreenshot() {
        return screenshot;
    }

    public long getSize() {
        return size;
    }
}
