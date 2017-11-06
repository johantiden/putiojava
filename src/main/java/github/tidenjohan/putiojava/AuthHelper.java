package github.tidenjohan.putiojava;

import com.fasterxml.jackson.core.type.TypeReference;
import github.tidenjohan.putiojava.dto.AccessTokenDto;
import github.tidenjohan.putiojava.internals.PutIoHttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;

import java.util.Objects;

public class AuthHelper {

    public static final String ACCESS_TOKEN_URL = PutIo.API_BASE + "/oauth2/access_token";
    public static final String AUTHENTICATION_URL = PutIo.API_BASE + "/oauth2/authenticate";

    private final PutIoHttpClient client;

    private final String clientId;
    private final String clientSecret;
    private final String redirectUri;
    private final String type;

    public AuthHelper(PutIoHttpClient client, String clientId, String clientSecret, String redirectUri, String type) {
        this.client = Objects.requireNonNull(client);
        this.clientId = Objects.requireNonNull(clientId);
        this.clientSecret = Objects.requireNonNull(clientSecret);
        this.redirectUri = Objects.requireNonNull(redirectUri);
        this.type = Objects.requireNonNull(type);
    }

    public AuthHelper(PutIoHttpClient client, String clientId, String clientSecret, String redirectUri) {
        this(client, clientId, clientSecret, redirectUri, "code");
    }

    /**
     * Get url to redirect user to for authentication.
     */
    public String getAuthenticationUrl(String code) {
        return RequestBuilder.get(AUTHENTICATION_URL)
                .addParameter("client_id", clientId)
                .addParameter("response_type", type)
                .addParameter("redirect_uri", redirectUri)
                .getUri()
                .toString();
    }

    public PutIoToken getAccessToken(String code) throws ApiException {
        HttpUriRequest request = RequestBuilder.get(ACCESS_TOKEN_URL)
                .addParameter("client_id", clientId)
                .addParameter("client_secret", clientSecret)
                .addParameter("grant_type", "authorization_code")
                .addParameter("redirect_uri", redirectUri)
                .addParameter("code", code)
                .build();

        AccessTokenDto accessTokenDto = client.executeTo(request, new TypeReference<AccessTokenDto>() {});
        return new PutIoToken(accessTokenDto.getAccess_token());
    }

}
