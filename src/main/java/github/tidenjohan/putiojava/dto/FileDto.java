package github.tidenjohan.putiojava.dto;

public class FileDto extends BaseDto {
    public FileDto(String status, String content_type, String crc32, String created_at, String first_accessed_at, String icon, long id, boolean is_mp4_available, boolean is_shared, String name, String opensubtitles_hash, long parent_id, String screenshot, long size) {
        super(status, content_type, crc32, created_at, first_accessed_at, icon, id, is_mp4_available, is_shared, name, opensubtitles_hash, parent_id, screenshot, size);
    }
}
