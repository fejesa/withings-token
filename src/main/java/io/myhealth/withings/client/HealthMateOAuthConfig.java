package io.myhealth.withings.client;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class HealthMateOAuthConfig {

    private String tokenHost;

    private String clientId;

    private String clientSecret;

    private String redirectUri;

    private HealthMateOAuthConfig () {}

    public String getTokenHost() {
        return tokenHost;
    }

    public void setTokenHost(String tokenHost) {
        this.tokenHost = tokenHost;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public static HealthMateOAuthConfig load(String path) throws IOException {
        try (InputStream input = new FileInputStream(path)) {
            Properties prop = new Properties();
            prop.load(input);

            HealthMateOAuthConfig config = new HealthMateOAuthConfig();
            config.setClientId(prop.getProperty("withings.api.clientId"));
            config.setClientSecret(prop.getProperty("withings.api.clientSecret"));
            config.setTokenHost(prop.getProperty("withings.api.tokenHost"));
            config.setRedirectUri(prop.getProperty("withings.api.redirectUri"));

            return config;
        }
    }
}
