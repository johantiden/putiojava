package github.tidenjohan.putiojava.internals;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import github.tidenjohan.putiojava.ApiException;
import github.tidenjohan.putiojava.dto.ErrorDto;
import github.tidenjohan.putiojava.PutIoToken;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Objects;

public class PutIoHttpClient {

    private final CloseableHttpClient httpClient;
    private final ObjectMapper objectMapper;

    public PutIoHttpClient(CloseableHttpClient httpClient, ObjectMapper objectMapper) {
        this.httpClient = Objects.requireNonNull(httpClient);
        this.objectMapper = Objects.requireNonNull(objectMapper);
    }

    public <T> T get(String url, PutIoToken token, TypeReference<T> typeLiteral) throws ApiException {
        return executeTo(buildGet(url, token), typeLiteral);
    }

    public static HttpUriRequest buildGet(String url, PutIoToken token) {
        return RequestBuilder.get(url)
                .addParameter("oauth_token", token.getToken())
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build();
    }

    public static HttpUriRequest buildPost(String url, PutIoToken token, String body) {
        return RequestBuilder.post(url)
                .addParameter("oauth_token", token.getToken())
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .setEntity(new StringEntity(body, "UTF-8"))
                .build();
    }

    public <T> T post(String url, PutIoToken token, Object body, TypeReference<T> typeLiteral) throws ApiException {
        String bodyString = null;
        try {
            bodyString = objectMapper.writeValueAsString(body);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to write object to JSON");
        }
        HttpUriRequest httpUriRequest = buildPost(url, token, bodyString);
        return executeTo(httpUriRequest, typeLiteral);
    }

    public void post(String url, PutIoToken traktToken, Object body) throws ApiException {
        post(url, traktToken, body, new TypeReference<Object>() {});
    }

    public String executeToString(HttpUriRequest request) throws ApiException {
        try (CloseableHttpResponse execute = httpClient.execute(request)) {

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            execute.getEntity().writeTo(baos);
            String body = baos.toString();
            if (execute.getStatusLine().getStatusCode() <= 299) {
                return body;
            } else {
                if (!body.isEmpty()) {
                    try {
                        ErrorDto dto = objectMapper.readValue(body, new TypeReference<ErrorDto>() {
                        });
                        throw new RuntimeException("Failed to execute request\n" + dto.error_type + ":" + dto.error_message);
                    } catch (IOException e) {
                        throw new RuntimeException("Failed to parse errorDto.", e);
                    }
                } else {
                    throw new RuntimeException("Failed to execute request. Body was empty");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed get request: " +request.getURI(), e);
        }

    }

    public <T> T executeTo(HttpUriRequest request, TypeReference<T> typeReference) throws ApiException {
        String body = executeToString(request);
        try {
            return objectMapper.readValue(body, typeReference);
        } catch (IOException e) {
            throw new RuntimeException("Could not parse dto", e);
        }
    }

}
