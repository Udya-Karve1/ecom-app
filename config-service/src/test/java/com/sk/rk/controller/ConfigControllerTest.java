package com.sk.rk.controller;

import com.sk.rk.model.ConfigResponse;
import com.sk.rk.service.ConfigService;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ConfigController.class)
class ConfigControllerTest {

    @MockBean
    private ConfigService configService;

    @Autowired
    private MockMvc mockMvc;

    @Inject
    private ConfigController configController;


    @Test
    void getApplicationProfileTest() throws Exception {
        when(configService.getConfigResponse(anyString(), anyString())).thenReturn(new ConfigResponse());
        this.mockMvc.perform(get("/api/config/any/any")).andExpect(status().isOk());
    }

    @Test
    void getApplicationProfileLabelTest() throws Exception {
        when(configService.getConfigResponse(anyString(), anyString(), anyString())).thenReturn(new ConfigResponse());
        this.mockMvc.perform(get("/api/config/any/any/any")).andExpect(status().isOk());
    }

    @Test
    void getApplicationProfileExtTest1() throws Exception {
        when(configService.getConfigResponse(anyString(), anyString())).thenReturn(new ConfigResponse());
        this.mockMvc.perform(get("/api/config/application.yml")).andExpect(status().isOk());
    }

    @Test
    void getApplicationProfileExtTest2() throws Exception {
        when(configService.getConfigResponse(anyString(), anyString())).thenReturn(new ConfigResponse());
        this.mockMvc.perform(get("/api/config/application-local.yml")).andExpect(status().isOk());
    }


    @Test
    void getApplicationProfileExtTest3() throws Exception {
        when(configService.getConfigResponse(anyString(), anyString())).thenReturn(new ConfigResponse());
        this.mockMvc.perform(get("/api/config/application.properties")).andExpect(status().isOk());
    }

    @Test
    void getApplicationProfileExtTest4() throws Exception {
        when(configService.getConfigResponse(anyString(), anyString())).thenReturn(new ConfigResponse());
        this.mockMvc.perform(get("/api/config/application-local.properties")).andExpect(status().isOk());
    }

    @Test
    void getApplicationProfileExtTest5() throws Exception {
        when(configService.getConfigResponse(anyString(), anyString(), anyString())).thenReturn(new ConfigResponse());
        this.mockMvc.perform(get("/api/config/dev/application-dev.properties")).andExpect(status().isOk());
    }

    @Test
    void getApplicationProfileExtTest6() throws Exception {
        when(configService.getConfigResponse(anyString(), anyString(), anyString())).thenReturn(new ConfigResponse());
        this.mockMvc.perform(get("/api/config/dev/application-dev.yml")).andExpect(status().isOk());

    }

}
