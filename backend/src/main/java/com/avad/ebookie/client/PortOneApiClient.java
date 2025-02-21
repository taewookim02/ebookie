package com.avad.ebookie.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class PortOneApiClient {


    private final WebClient webClient;

    @Value("${application.port-one.base-url}")
    private String baseUrl;

    @Value("${application.port-one.secret-key}")
    private String secretKey;

    @Value("${application.port-one.store-key}")
    private String storeKey;


    // 단건 조회 API
    public Map<String, Object> getPaymentDetails(String paymentId) {
        System.out.println("baseUrl + \"/payments/\" + paymentId + \"?storeId=\"  + storeKey = " + baseUrl + "/payments/" + paymentId + "?storeId="  + storeKey);
        return webClient
                .get()
                .uri(baseUrl + "/payments/" + paymentId + "?storeId="  + storeKey)
                .header("Authorization", "PortOne " + secretKey)
                .retrieve()
                .bodyToMono(Map.class)
                .block();
    }
}
