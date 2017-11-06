package github.tidenjohan.putiojava.dto;

public class FileDto extends BaseDto {
    private final String content_type;
    private final String crc32;
    private final String created_at;
    private final String first_accessed_at;
    private final String icon;
    private final long id;
    private final boolean is_mp4_available;
    private final boolean is_shared;
    private final String name;
    private final String opensubtitles_hash;
    private final long parent_id;
    private final String screenshot;
    private final long size;

    public FileDto(String status, String content_type, String crc32, String created_at, String first_accessed_at, String icon, long id, boolean is_mp4_available, boolean is_shared, String name, String opensubtitles_hash, long parent_id, String screenshot, long size) {
        super(status);
        this.content_type = content_type;
        this.crc32 = crc32;
        this.created_at = created_at;
        this.first_accessed_at = first_accessed_at;
        this.icon = icon;
        this.id = id;
        this.is_mp4_available = is_mp4_available;
        this.is_shared = is_shared;
        this.name = name;
        this.opensubtitles_hash = opensubtitles_hash;
        this.parent_id = parent_id;
        this.screenshot = screenshot;
        this.size = size;
    }

    public String getContent_type() {
        return content_type;
    }

    public String getCrc32() {
        return crc32;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getFirst_accessed_at() {
        return first_accessed_at;
    }

    public String getIcon() {
        return icon;
    }

    public long getId() {
        return id;
    }

    public boolean is_mp4_available() {
        return is_mp4_available;
    }

    public boolean is_shared() {
        return is_shared;
    }

    public String getName() {
        return name;
    }

    public String getOpensubtitles_hash() {
        return opensubtitles_hash;
    }

    public long getParent_id() {
        return parent_id;
    }

    public String getScreenshot() {
        return screenshot;
    }

    public long getSize() {
        return size;
    }
}
