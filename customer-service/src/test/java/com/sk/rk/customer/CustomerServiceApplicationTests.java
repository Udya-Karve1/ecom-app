package com.sk.rk.customer;

import com.sk.rk.controller.CustomerController;
import com.sk.rk.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
class CustomerServiceApplicationTests {


	@Autowired
	protected MockMvc mockMvc;

	@MockBean
	private CustomerService customerService;


	@Test
	void getCustomerByIdTest() throws Exception {
		when(customerService.getCustomerById(Mockito.anyLong())).thenReturn(Mockito.any());
		this.mockMvc.perform(get("/1")).andExpect(status().isOk());
	}


	@Test
	void saveCustomerTest() throws Exception {
		when(customerService.getCustomerById(Mockito.anyLong())).thenReturn(Mockito.any());
		this.mockMvc.perform(get("/1")).andExpect(status().isOk());
	}

}
