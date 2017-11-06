package github.tidenjohan.putiojava.dto;

import java.util.Objects;

public class AccessTokenDto extends BaseDto {
    public final String access_token;

    private AccessTokenDto(String status, String access_token) {
        super(status);
        this.access_token = Objects.requireNonNull(access_token);
    }

    public String getAccess_token() {
        return access_token;
    }
}
