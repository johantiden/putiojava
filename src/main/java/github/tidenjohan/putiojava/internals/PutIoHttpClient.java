package github.tidenjohan.putiojava.internals;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import github.tidenjohan.putiojava.ApiException;
import github.tidenjohan.putiojava.PutIoToken;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
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

    public <T> T get(String url, PutIoToken token, TypeReference<T> typeLiteral, NameValuePair... parameters) throws ApiException {
        return executeTo(buildGet(url, token, parameters), typeLiteral);
    }

    public static HttpUriRequest buildGet(String url, PutIoToken token, NameValuePair... parameters) {
        return RequestBuilder.get(url)
                .addParameters(parameters)
                .addHeader("Authorization", "token " + token.getToken())
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build();
    }
//
//    public static HttpUriRequest buildPost(String url, PutIoToken token, HttpEntity entity) {
//        return RequestBuilder.post(url)
//                .addHeader("Authorization", "token " + token.getToken())
//                .addHeader("Content-Type", "application/json")
//                .addHeader("Accept", "application/json")
//                .setEntity(entity)
//                .build();
//    }
    public static HttpUriRequest buildPost(String url, PutIoToken token, NameValuePair... parameters) throws UnsupportedEncodingException {
        return RequestBuilder.post(url)
                .addHeader("Authorization", "token " + token.getToken())
                .addHeader("Accept", "application/json")
                .setEntity(new UrlEncodedFormEntity(Arrays.asList(parameters)))
                .build();
    }

//    public <T> T post(String url, PutIoToken token, TypeReference<T> typeLiteral, HttpEntity entity) throws ApiException {
//        HttpUriRequest httpUriRequest = buildPost(url, token, entity);
//        return executeTo(httpUriRequest, typeLiteral);
//    }
    public <T> T post(String url, PutIoToken token, TypeReference<T> typeLiteral, NameValuePair... parameters) throws ApiException {
        HttpUriRequest httpUriRequest = null;
        try {
            httpUriRequest = buildPost(url, token, parameters);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Failed to post " + url, e);
        }
        return executeTo(httpUriRequest, typeLiteral);
    }

    public String executeToString(HttpUriRequest request) throws ApiException {
        try (CloseableHttpResponse execute = httpClient.execute(request)) {

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            execute.getEntity().writeTo(baos);
            String body = baos.toString();
            if (execute.getStatusLine().getStatusCode() <= 299) {
                return body;
            } else {
                System.out.println("statuscode: " + execute.getStatusLine().getStatusCode());
                if (!body.isEmpty()) {
                    throw new ApiException(body);
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
        if (typeReference == null) {
            return null;
        }
        try {
            return objectMapper.readValue(body, typeReference);
        } catch (IOException e) {
            throw new RuntimeException("Could not parse dto", e);
        }
    }

}
