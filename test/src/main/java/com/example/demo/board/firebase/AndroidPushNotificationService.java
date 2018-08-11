package com.example.demo.board.firebase;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AndroidPushNotificationService {
    private static final String FIREBASE_SERVER_KEY = "AAAAeA_lAx0:APA91bE13jBM3QI-T4NY4xr07kXgA0x3Wai9W9bofKxHslA9R_dzVG8Dl-KdDAgBwamwA87V6fgux6ptR0EHpK4VdF5CLx-eK4iociD2ZIPGHq7rYLhEcapVkGACmrg_wQprHDvqpWz6oaaYyWYV6qB9n7WZrmXWEw";
    private static final String FIREBASE_API_URL="http://fcm.googleapis.com/fcm/send";

    @Async
    public CompletableFuture<String> send(HttpEntity<String> entity){
        RestTemplate restTemplate = new RestTemplate(); // Spring에서 restful api를 요청하기 위해

        ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();

        interceptors.add(new HeaderRequestInterceptor("Authorization", "key=" + FIREBASE_SERVER_KEY));
        interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json"));
        interceptors.add(new HeaderRequestInterceptor("charset", "UTF-8"));
        restTemplate.setInterceptors(interceptors);

        String firebaseResponse = restTemplate.postForObject(FIREBASE_API_URL, entity, String.class);

        return CompletableFuture.completedFuture(firebaseResponse);
    }
}
