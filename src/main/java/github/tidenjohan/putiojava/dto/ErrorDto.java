package github.tidenjohan.putiojava.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorDto extends BaseDto {
    public final String errorType;
    public final String errorMessage;

    @JsonCreator
    public ErrorDto(
            @JsonProperty("status") String status,
            @JsonProperty("error_type") String errorType,
            @JsonProperty("error_message") String errorMessage) {
        super(status);
        this.errorType = errorType;
        this.errorMessage = errorMessage;
    }

    public String getErrorType() {
        return errorType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
