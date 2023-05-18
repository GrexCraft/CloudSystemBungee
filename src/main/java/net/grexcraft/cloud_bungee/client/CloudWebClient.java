package net.grexcraft.cloud_bungee.client;

import net.grexcraft.cloud.core.dto.ImageDto;
import net.grexcraft.cloud.core.dto.PoolSlotDto;
import net.grexcraft.cloud.core.dto.ServerDto;
import net.grexcraft.cloud.core.request.CreateServerRequest;
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

    public static List<ServerDto> getServers() {
        Mono<List<ServerDto>> response = client.get()
                .uri("/api/v1/server/")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {});

        return response.block();
    }

    public static List<PoolSlotDto> getPoolSlots() {
        Mono<List<PoolSlotDto>> response = client.get()
                .uri("/api/v1/slot/")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {});

        return response.block();
    }
}
