package github.tidenjohan.putiojava;

import java.util.Objects;

public class PutIoToken {
    private final String token;

    public PutIoToken(String token) {
        this.token = Objects.requireNonNull(token);
    }

    public String getToken() {
        return token;
    }
}
