package com.sk.rk.service.step;

import com.sk.rk.events.PaymentRequestDTO;
import com.sk.rk.events.PaymentResponseDTO;
import com.sk.rk.events.enums.PaymentStatus;
import com.sk.rk.service.WorkflowStep;
import com.sk.rk.service.WorkflowStepStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
public class PaymentStep implements WorkflowStep {

    private final WebClient webClient;

    private final PaymentRequestDTO requestDTO;
    private WorkflowStepStatus stepStatus = WorkflowStepStatus.PENDING;

    public PaymentStep(WebClient webClient, PaymentRequestDTO requestDTO) {
        this.webClient = webClient;
        this.requestDTO = requestDTO;
    }

    @Override
    public WorkflowStepStatus getStatus() {
        return this.stepStatus;
    }

    @Override
    public Mono<Boolean> process() {
        log.info("Payment process called .................");
        this.webClient.post()
                .uri("/debit")
                .body(Mono.just(this.requestDTO), PaymentRequestDTO.class)
                .retrieve()
                .bodyToMono(PaymentResponseDTO.class)
                .block();


        return Mono.just(true);
    }

    @Override
    public Mono<Boolean> revert() {
        this.webClient
                .post()
                .uri("/credit")
                .body(Mono.just(this.requestDTO), PaymentRequestDTO.class)
                .retrieve()
                .bodyToMono(Void.class)
                .block();

        return Mono.just(true);

    }
}
