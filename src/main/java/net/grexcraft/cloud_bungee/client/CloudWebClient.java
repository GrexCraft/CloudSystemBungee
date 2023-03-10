package net.grexcraft.cloud_bungee.client;

import net.grexcraft.cloud_bungee.dto.ImageDto;
import net.grexcraft.cloud_bungee.model.CreateServerRequest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

public class CloudWebClient {
    private static final WebClient client = WebClient.create("http://cloud-service:8080");

    private static List<ImageDto> images;

    private CloudWebClient() {}

    public static String createServer(CreateServerRequest request) {
        return client.post()
                .uri("/api/v1/server/create")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(request), CreateServerRequest.class)
                .retrieve()
                .bodyToMono(String.class).block();
    }

    public static void fetchImages() {
        Mono<List<ImageDto>> response = client.get()
                .uri("/api/v1/image/")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {});

        images = response.block();
    }

    public static List<ImageDto> getImages() {
        return images;
    }
}
