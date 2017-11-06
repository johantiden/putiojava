package github.tidenjohan.putiojava.dto;

public class ErrorDto extends BaseDto {
    public final String error_type;
    public final String error_message;

    public ErrorDto(String status, String error_type, String error_message) {
        super(status);
        this.error_type = error_type;
        this.error_message = error_message;
    }

    public String getError_type() {
        return error_type;
    }

    public String getError_message() {
        return error_message;
    }
}
