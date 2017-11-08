package github.tidenjohan.putiojava.dto;

import java.util.Objects;

public class AccessTokenDto {
    public final String access_token;

    private AccessTokenDto(String access_token) {
        this.access_token = Objects.requireNonNull(access_token);
    }

    public String getAccess_token() {
        return access_token;
    }
}
