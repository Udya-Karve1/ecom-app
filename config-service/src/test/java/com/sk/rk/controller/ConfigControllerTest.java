package com.sk.rk.controller;

import com.sk.rk.config.controller.ConfigController;
import com.sk.rk.config.model.ConfigResponse;
import com.sk.rk.config.service.ConfigService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ConfigController.class)
public class ConfigControllerTest {

    @MockBean
    private ConfigService configService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void getApplicationProfileTest() throws Exception {
        when(configService.getConfigResponse(anyString(), anyString())).thenReturn(new ConfigResponse());
        this.mockMvc.perform(get("/api/config/any/any")).andExpect(status().isOk());
    }

    @Test
    public void getApplicationProfileLabelTest() throws Exception {
        when(configService.getConfigResponse(anyString(), anyString(), anyString())).thenReturn(new ConfigResponse());
        this.mockMvc.perform(get("/api/config/any/any/any")).andExpect(status().isOk());
    }

    @Test
    public void getApplicationProfileExtTest() throws Exception {
        doNothing().when(configService).getConfigResponse(anyString(), anyString());
        this.mockMvc.perform(get("/api/config/application.yml")).andExpect(status().isOk());
    }


}
