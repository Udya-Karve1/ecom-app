package com.sk.rk.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sk.rk.events.OrderAddUpdateRequest;
import com.sk.rk.events.OrderCompletedEvent;
import com.sk.rk.model.CustomerOrder;
import com.sk.rk.repository.CustomerOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@Slf4j
public class KafkaOrderListener {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CustomerOrderRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    @KafkaListener(topics = "order_completed_event", groupId = "group_id")
    public void consume(OrderCompletedEvent orderCompletedEvent) throws JsonProcessingException, InterruptedException {
        log.info("Received orderAddUpdateRequest: {}", objectMapper.writeValueAsString(orderCompletedEvent));

        List<CustomerOrder> customerOrders = repository.findByOrderId(orderCompletedEvent.getOrderId());

        if(CollectionUtils.isEmpty(customerOrders)) {
            repository.save(prepareEntityCustomerOrder(orderCompletedEvent, getOrderDetail(orderCompletedEvent)));
        } else {
            repository.updateOrderStatus(customerOrders.get(0).getCustomerOrderId()
                    , new Timestamp(System.currentTimeMillis()), orderCompletedEvent.getOrderStatus());
        }
    }

    private CustomerOrder prepareEntityCustomerOrder(OrderCompletedEvent orderCompletedEvent, Map<String, Object> orderDetail) {
        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setCustomerId(orderCompletedEvent.getCustomerId());
        customerOrder.setOrderStatus(orderCompletedEvent.getOrderStatus());
        customerOrder.setProductId(orderCompletedEvent.getProductId());
        customerOrder.setDateCreated(orderCompletedEvent.getDateCreated());
        customerOrder.setDateUpdated(orderCompletedEvent.getDateUpdated());

        customerOrder.setFirstName(orderDetail.get("firstName").toString());
        customerOrder.setLastName(orderDetail.get("lastName").toString());
        customerOrder.setEmail(orderDetail.get("email").toString());

        customerOrder.setProductName(orderDetail.get("productName").toString());
        customerOrder.setPrice(new BigDecimal(orderDetail.get("price").toString()));

        return customerOrder;

    }


    private Map<String, Object> getOrderDetail(OrderCompletedEvent orderCompletedEvent) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        List<Callable<Map<String, Object>>> callables = Arrays.asList(
                () -> {
                    ResponseEntity customerResponse = restTemplate.exchange(
                            "CUSTOMER-SRVICE/V1/API/" + orderCompletedEvent.getCustomerId()
                            , HttpMethod.GET, prepareHttpEntity(), ResponseEntity.class);
                    return Collections.emptyMap();
                },
                ()-> {
                    ResponseEntity productResponse = restTemplate.exchange(
                            "PRODUCT-SRVICE/V1/API/" + orderCompletedEvent.getProductId()
                            , HttpMethod.GET, prepareHttpEntity(), ResponseEntity.class);
                    return Collections.emptyMap();
                }
        );
        return getResult(executor, callables);
    }

    private Map<String, Object> getResult(ExecutorService executor, Collection<Callable<Map<String, Object>>> callables) throws InterruptedException {
        Map<String, Object> result = new HashMap<>();
        executor.invokeAll(callables).stream().forEach(f -> {
            try {
                result.putAll(f.get());
            } catch (InterruptedException | ExecutionException e) {
                String message = e.getMessage();
                //Handle sql error message
                if(e.getCause() instanceof SQLException){
                    message = e.getCause().getMessage();
                } else if(e.getCause() instanceof UncategorizedSQLException){
                    message = ((UncategorizedSQLException)e.getCause()).getSQLException().getMessage();
                }
                log.error("Error while executing callable/feature ", e);
                Thread.currentThread().interrupt();
                throw new IllegalStateException(message, e);
            } catch (Exception e) {
                log.error("Error while executing callable/feature ", e);
                throw new IllegalStateException(e.getMessage(), e);
            }
        });
        return  result;
    }




    private HttpEntity prepareHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        return new HttpEntity<String>(headers);
    }
}
