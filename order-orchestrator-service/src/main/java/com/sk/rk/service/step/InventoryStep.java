package com.sk.rk.service.step;

import com.sk.rk.events.InventoryRequestDTO;
import com.sk.rk.events.InventoryResponseDTO;
import com.sk.rk.service.WorkflowStep;
import com.sk.rk.service.WorkflowStepStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
public class InventoryStep implements WorkflowStep {

    private WebClient webClient;
    private InventoryRequestDTO requestDTO;
    private WorkflowStepStatus stepStatus = WorkflowStepStatus.PENDING;

    public InventoryStep(WebClient webClient, InventoryRequestDTO requestDTO) {
        this.webClient = webClient;
        this.requestDTO = requestDTO;
    }

    @Override
    public WorkflowStepStatus getStatus() {
        return this.stepStatus;
    }

    @Override
    public Mono<Boolean> process() {
        log.info("Inventory process called .................");
        this.webClient
                .post()
                .uri("/deduct")
                .body(Mono.just(requestDTO), InventoryRequestDTO.class)
                .retrieve()
                .bodyToMono(InventoryResponseDTO.class)
                .block();

        return Mono.just(true);


    }

    @Override
    public Mono<Boolean> revert() {
        this.webClient
                .post()
                .uri("/add")
                .body(Mono.just(requestDTO), InventoryRequestDTO.class)
                .retrieve()
                .bodyToMono(Void.class)
                .block();

        return Mono.just(true);
    }
}
