package io.myhealth.withings;

import io.myhealth.withings.client.HealthMateOAuthConfig;
import io.myhealth.withings.client.HealthMateTokenClient;
import java.io.IOException;
import org.springframework.web.client.RestTemplate;

public class HealthMateClientApp {

    public static void main(String[] args) throws IOException {
        if (args.length < 3) {
            throw new IllegalArgumentException();
        }
        var client = new HealthMateTokenClient(new RestTemplate(), HealthMateOAuthConfig.load(args[1]));
        if ("-refresh".equals(args[0])) {
            System.out.println(client.refreshToken(args[2]));
        }
        if ("-access".equals(args[0])) {
            System.out.println(client.accessToken(args[2]));
        }
    }
}
