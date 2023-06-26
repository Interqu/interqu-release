package com.interqu.process;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

public class AWSConnection {

    public void analyzeContent(String fileName, MultipartFile video){
        WebClient webClient = WebClient.create();
        Mono<String> responseMono = webClient.post()
        .uri("https://api.example.com/data")
        .bodyValue("{\"key\":\"value\"}")
        .retrieve()
        .bodyToMono(String.class);

        responseMono.subscribe(response -> {
            // Process the response
        });



    }

}
