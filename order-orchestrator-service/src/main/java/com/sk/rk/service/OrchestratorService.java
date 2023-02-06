package com.sk.rk.service;

import com.sk.rk.events.InventoryRequestDTO;
import com.sk.rk.events.OrchestratorRequestDTO;
import com.sk.rk.events.OrchestratorResponseDTO;
import com.sk.rk.events.PaymentRequestDTO;
import com.sk.rk.events.enums.OrderStatus;
import com.sk.rk.service.step.InventoryStep;
import com.sk.rk.service.step.PaymentStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class OrchestratorService {

    @Autowired
    @Qualifier("payment")
    private WebClient paymentClient;

    @Autowired
    @Qualifier("inventory")
    private WebClient inventoryClient;

    public Mono<OrchestratorResponseDTO> orderProduct(final OrchestratorRequestDTO requestDTO){
        Workflow orderWorkflow = this.getOrderWorkflow(requestDTO);

        return Flux.fromStream(orderWorkflow.getSteps().stream())
                .flatMap(WorkflowStep::process)
                        .handle(((aBoolean, synchronousSink)->{
                            if(aBoolean)
                                synchronousSink.next(true);
                            else
                                synchronousSink.error(new WorkflowException("Create order failed"));
                        }))
                                .then(Mono.fromCallable(() -> getResponseDTO(requestDTO, OrderStatus.ORDER_COMPLETED)))
                                        .onErrorResume(ex->this.revertOrder(orderWorkflow, requestDTO));

/*
        return Flux.fromStream(() -> orderWorkflow.getSteps().stream())

                .flatMap(workflowStep -> {
                    return workflowStep.process();
                })
                */
/*.flatMap(workflowStep -> {
                    return workflowStep.process();
                })*//*

                .handle(((aBoolean, synchronousSink) -> {
                    if(aBoolean)
                        synchronousSink.next(true);
                    else
                        synchronousSink.error(new WorkflowException("create order failed!"));
                }))
                .then(Mono.fromCallable(() -> getResponseDTO(requestDTO, OrderStatus.ORDER_COMPLETED)))
                .onErrorResume(ex -> this.revertOrder(orderWorkflow, requestDTO));
*/





        return Mono.fromCallable(() -> getResponseDTO(requestDTO, OrderStatus.ORDER_COMPLETED));
    }

    private Mono<OrchestratorResponseDTO> revertOrder(final Workflow workflow, final OrchestratorRequestDTO requestDTO){
        return Flux.fromStream(() -> workflow.getSteps().stream())
                .filter(wf -> wf.getStatus().equals(WorkflowStepStatus.COMPLETE))
                .flatMap(WorkflowStep::revert)
                .retry(3)
                .then(Mono.just(this.getResponseDTO(requestDTO, OrderStatus.ORDER_CANCELLED)));
    }

    private Workflow getOrderWorkflow(OrchestratorRequestDTO requestDTO){
        WorkflowStep paymentStep = new PaymentStep(this.paymentClient, this.getPaymentRequestDTO(requestDTO));
        WorkflowStep inventoryStep = new InventoryStep(this.inventoryClient, this.getInventoryRequestDTO(requestDTO));
        return new OrderWorkflow(List.of(paymentStep, inventoryStep));
    }

    private OrchestratorResponseDTO getResponseDTO(OrchestratorRequestDTO requestDTO, OrderStatus status){
        OrchestratorResponseDTO responseDTO = new OrchestratorResponseDTO();
        responseDTO.setOrderId(requestDTO.getOrderId());
        responseDTO.setAmount(requestDTO.getAmount());
        responseDTO.setProductId(requestDTO.getProductId());
        responseDTO.setCustomerId(requestDTO.getCustomerId());
        responseDTO.setStatus(status);
        return responseDTO;
    }

    private PaymentRequestDTO getPaymentRequestDTO(OrchestratorRequestDTO requestDTO){
        PaymentRequestDTO paymentRequestDTO = new PaymentRequestDTO();
        paymentRequestDTO.setCustomerId(requestDTO.getCustomerId());
        paymentRequestDTO.setAmount(requestDTO.getAmount());
        paymentRequestDTO.setOrderId(requestDTO.getOrderId());
        return paymentRequestDTO;
    }

    private InventoryRequestDTO getInventoryRequestDTO(OrchestratorRequestDTO requestDTO){
        InventoryRequestDTO inventoryRequestDTO = new InventoryRequestDTO();
        inventoryRequestDTO.setCustomerId(requestDTO.getCustomerId());
        inventoryRequestDTO.setProductId(requestDTO.getProductId());
        inventoryRequestDTO.setOrderId(requestDTO.getOrderId());
        return inventoryRequestDTO;
    }
}
