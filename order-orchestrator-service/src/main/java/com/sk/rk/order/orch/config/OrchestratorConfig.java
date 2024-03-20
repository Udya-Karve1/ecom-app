package com.sk.rk.order.orch.config;

import com.sk.rk.order.orch.service.OrchestratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrchestratorConfig {
    @Autowired
    private OrchestratorService orchestratorService;

/*    @Bean
    public Function<Flux<OrchestratorRequestDTO>, Flux<OrchestratorResponseDTO>> processor(){
        return flux -> flux
                .flatMap(dto -> this.orchestratorService.orderProduct(dto))
                .doOnNext(dto -> System.out.println("Status123 : " + dto.getStatus()));
    }*/
}
