/**
 *
 * This will refresh materialized view
 *
 */
package com.sk.rk.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.sk.rk.events.OrderCompletedEvent;
import com.sk.rk.model.CustomerOrder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Map;


@Service
@Slf4j
public class OrderConsumer {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${customer.get-customer.url}")
    private String getCustomerUrl;

    @Value("${customer.get-product.url}")
    private String getProductUrl;

    @Autowired
    private CustomerOrderService customerOrderService;

    @KafkaListener(topics = "${spring.topic.order-completed}", groupId = "${spring.group-id.order-completed}")
    public void consumer(OrderCompletedEvent order) throws JsonProcessingException {
        log.info("consumer: {}", objectMapper.writeValueAsString(order));
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        Map<String, Object>  customerResponse = restTemplate.exchange(getCustomerUrl+"/" + order.getCustomerId(), HttpMethod.GET, entity, Map.class).getBody();
        Map<String, Object>  productResponse = restTemplate.exchange(getProductUrl+"/" + order.getProductId(), HttpMethod.GET, entity, Map.class).getBody();

        customerOrderService.addOrder(createCustomerOrder(order, customerResponse, productResponse));
    }

    private CustomerOrder createCustomerOrder(OrderCompletedEvent order, Map<String, Object> customerResponse, Map<String, Object>  productResponse) {
        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setOrderId(order.getOrderId());
        customerOrder.setCustomerId(order.getCustomerId());
        customerOrder.setProductId(order.getProductId());
        customerOrder.setDateCreated(order.getDateCreated());
        customerOrder.setDateUpdated(order.getDateUpdated());

        Map<String, Object> customerData = (Map<String, Object>) customerResponse.get("data");

        customerOrder.setFirstName(customerData.get("firstName").toString());
        customerOrder.setLastName(customerData.get("lastName").toString());
        customerOrder.setEmail(customerData.get("email").toString());

        Map<String, Object> productData = (Map<String, Object>) productResponse.get("data");
        customerOrder.setProductName(productData.get("productName").toString());
        customerOrder.setPrice(new Double(productData.get("price").toString()));

        return customerOrder;
    }
}
