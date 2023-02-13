package com.sk.rk.service;

import com.sk.rk.events.PaymentRequestDTO;
import com.sk.rk.exception.BaseException;
import com.sk.rk.model.entity.Customer;
import com.sk.rk.model.request.AddCustomer;
import com.sk.rk.model.request.UpdateCustomer;
import com.sk.rk.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer getCustomerById(Long customerId) throws BaseException {
        return this.customerRepository.findById(customerId)
                .orElseThrow(() -> new BaseException(400, "Customer not found"));
    }

    @Transactional
    public Customer addCustomer(AddCustomer customer) throws BaseException {

        try {
            this.customerRepository.save(customer);
            return getCustomerById(this.customerRepository.getMaxId());
        } catch (BaseException e) {
            throw new BaseException(400, "Error while adding customer.");
        }
    }

    public Customer updateCustomer(UpdateCustomer customer) throws BaseException {

        try {
            this.customerRepository.update(customer);
            return getCustomerById(customer.getCustomerId());
        } catch (BaseException e) {
            throw new BaseException(400, "Error while updating customer");
        }
    }

    public void deleteCustomer(Long customerId) {
        this.customerRepository.deleteById(customerId);
    }

    public Map<String, Object> getBalance(Long customerId) throws BaseException {
        try {
            return Collections.singletonMap("balance", getCustomerById(customerId).getBalance());
        }catch (BaseException ex) {
            throw new BaseException(400, "Error accessing customer account detail.");
        }
    }

    public Map<String, String> debitBalance(PaymentRequestDTO requestDTO) throws BaseException {
        Customer customer = getCustomerById(requestDTO.getCustomerId());
        customer.setBalance(customer.getBalance().subtract(requestDTO.getAmount()));

        customerRepository.update(createEntityUpdateCustomer(customer));
        return Collections.singletonMap("status", "Balance debited successfully.");
    }

    public Map<String, String> creditBalance(PaymentRequestDTO requestDTO) throws BaseException {
        Customer customer = getCustomerById(requestDTO.getCustomerId());
        customer.setBalance(customer.getBalance().add(requestDTO.getAmount()));

        customerRepository.update(createEntityUpdateCustomer(customer));
        return Collections.singletonMap("status", "Balance credited successfully.");
    }

    private UpdateCustomer createEntityUpdateCustomer(Customer customer) {
        UpdateCustomer updateCustomer = new UpdateCustomer();
        updateCustomer.setCustomerId(customer.getCustomerId());
        updateCustomer.setBalance(customer.getBalance());
        updateCustomer.setEmail(customer.getEmail());
        updateCustomer.setPassword(customer.getPassword());
        updateCustomer.setLastName(customer.getLastName());
        updateCustomer.setFirstName(customer.getFirstName());

        return updateCustomer;
    }
}
