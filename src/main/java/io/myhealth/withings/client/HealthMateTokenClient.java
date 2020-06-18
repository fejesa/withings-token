package io.myhealth.withings.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class HealthMateTokenClient {

    private final RestTemplate restTemplate;

    private final HealthMateOAuthConfig config;

    public HealthMateTokenClient(RestTemplate restTemplate, HealthMateOAuthConfig config) {
        this.restTemplate = restTemplate;
        this.config = config;
    }

    public String accessToken(String code) {
        MultiValueMap<String, String> requestParams= new LinkedMultiValueMap<>();
        requestParams.add("grant_type", "authorization_code");
        requestParams.add("client_id", config.getClientId());
        requestParams.add("client_secret", config.getClientSecret());
        requestParams.add("redirect_uri", config.getRedirectUri());
        requestParams.add("code", code);

        return call(requestParams);
    }

    public String refreshToken(String token) {
        MultiValueMap<String, String> requestParams= new LinkedMultiValueMap<>();
        requestParams.add("grant_type", "refresh_token");
        requestParams.add("client_id", config.getClientId());
        requestParams.add("client_secret", config.getClientSecret());
        requestParams.add("refresh_token", token);
        return call(requestParams);
    }

    private String call(MultiValueMap<String, String> requestParams) {
        var response = restTemplate.postForEntity(config.getTokenHost(), createRequest(requestParams), String.class);
        return response.getBody();
    }

    private HttpEntity<MultiValueMap<String, String>> createRequest(MultiValueMap<String, String> requestParams) {
        return new HttpEntity<>(requestParams, createHeaders());
    }

    private HttpHeaders createHeaders() {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return headers;
    }
}
