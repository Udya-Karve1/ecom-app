package com.sk.rk.customer.service;

import com.sk.rk.common.exception.BaseException;
import com.sk.rk.customer.model.entity.Customer;
import com.sk.rk.customer.model.request.AddCustomer;
import com.sk.rk.customer.model.request.UpdateCustomer;
import com.sk.rk.customer.repository.CustomerRepositoryImpl;
import com.sk.rk.events.PaymentRequestDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Assertions;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class CustomerServiceApplicationTests {

	@Autowired
	protected MockMvc mockMvc;

	@Mock
	private CustomerRepositoryImpl customerRepository;

	@InjectMocks
	private CustomerService customerService;


	@Test
	void getCustomerByIdTest() throws Exception {
		when(customerRepository.findById(anyLong())).thenReturn(Optional.of(new Customer()));
		Assertions.assertNotNull(customerService.getCustomerById(1L));
	}

	@Test
	void getCustomerByIdTestException() throws Exception {
		when(customerRepository.findById(anyLong())).thenReturn(Optional.empty());
		Assertions.assertThrows(BaseException.class, ()->customerService.getCustomerById(1L));
	}

	@Test
	void addCustomerTest() throws Exception {
		when(customerRepository.save(any())).thenReturn(1);
		when(customerRepository.findById(anyLong())).thenReturn(Optional.of(new Customer()));

		Assertions.assertNotNull(customerService.addCustomer(new AddCustomer()));
	}


	@Test
	void addCustomerTestException() throws Exception {
		when(customerRepository.save(any())).thenReturn(1);
		when(customerRepository.findById(anyLong())).thenReturn(Optional.empty());
		Assertions.assertThrows(BaseException.class, ()->customerService.addCustomer(new AddCustomer()));
	}

	@Test
	void updateCustomerTestException() throws Exception {
		when(customerRepository.update(any())).thenReturn(1);
		when(customerRepository.findById(anyLong())).thenReturn(Optional.empty());

		Assertions.assertThrows(BaseException.class, ()->customerService.updateCustomer(new UpdateCustomer()));
	}


	@Test
	void updateCustomerTest() throws Exception {
		when(customerRepository.update(any())).thenReturn(1);
		when(customerRepository.findById(anyLong())).thenReturn(Optional.of(new Customer()));

		Assertions.assertNotNull(customerService.updateCustomer(createUpdateCustomer()));
	}

	@Test
	void deleteCustomerTest() throws Exception {
		this.customerService.deleteCustomer(1L);
		verify(customerRepository, times(1)).deleteById(anyLong());
	}


	@Test
	void getBalanceTest() throws Exception {
		when(customerRepository.findById(anyLong())).thenReturn(Optional.of(new Customer(1L, "first", "last", "",  "", 5.5)));
		Assertions.assertNotNull(customerService.getBalance(1L));
	}


	@Test
	void getBalanceTestException() throws Exception {
		when(customerRepository.findById(anyLong())).thenReturn(Optional.empty());
		Assertions.assertThrows(BaseException.class, ()->customerService.getBalance(1L));
	}

	@Test
	void debitBalanceTest() throws BaseException {
		when(customerRepository.findById(anyLong())).thenReturn(Optional.of(new Customer(1L, "first", "last", "",  "", 5.5)));
		when(customerRepository.update(any())).thenReturn(1);
		Assertions.assertNotNull(customerService.debitBalance(createPaymentRequestDTO()));
	}


	@Test
	void creditBalanceTest() throws BaseException {
		when(customerRepository.findById(anyLong())).thenReturn(Optional.of(new Customer(1L, "first", "last", "",  "", 5.5)));
		when(customerRepository.update(any())).thenReturn(1);
		Assertions.assertNotNull(customerService.creditBalance(createPaymentRequestDTO()));
	}

	private PaymentRequestDTO createPaymentRequestDTO() {
		PaymentRequestDTO dto = new PaymentRequestDTO();
		dto.setCustomerId(1L);
		dto.setAmount(1000.10);
		dto.setOrderId(1000L);
		return dto;
	}
	private UpdateCustomer createUpdateCustomer() {
		UpdateCustomer updateCustomer = new UpdateCustomer();
		updateCustomer.setCustomerId(1L);
		updateCustomer.setBalance(10000.50);
		updateCustomer.setPassword("asdfasdf");
		updateCustomer.setEmail("my@email.com");
		updateCustomer.setLastName("last");
		updateCustomer.setLastName("first");

		return updateCustomer;
	}
}
