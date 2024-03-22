package com.sk.rk.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.sk.rk.config.controller.PropertyController;
import com.sk.rk.config.model.Application;
import com.sk.rk.config.model.Profile;
import com.sk.rk.config.model.Property;
import com.sk.rk.config.service.ApplicationService;
import com.sk.rk.config.service.ProfileService;
import com.sk.rk.config.service.PropertyService;
import com.sk.rk.common.exception.BaseException;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PropertyController.class)
class PropertyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Inject
    private PropertyController propertyController;

    @MockBean
    private PropertyService propertyService;

    @MockBean
    private ApplicationService applicationService;

    @MockBean
    private ProfileService profileService;


    @Test
    void getAllPropertyTest() throws Exception {
        when(propertyService.getAllProperties()).thenReturn(Collections.emptyList());
        this.mockMvc.perform(get("/property/all")).andExpect(status().isOk());
    }

    @Test
    void getAllProfileTest() throws Exception {
        when(profileService.getAllProfiles()).thenReturn(Collections.emptyList());
        this.mockMvc.perform(get("/property/profile/all")).andExpect(status().isOk());
    }


    @Test
    void getPropertyByIdTest() throws Exception {
        when(propertyService.getPropertyById(Mockito.anyLong())).thenReturn(Collections.emptyMap());
        this.mockMvc.perform(get("/property/property/1")).andExpect(status().isOk());
    }

    @Test
    void deleteProfileTest() throws Exception {
        doNothing().when(profileService).deleteProfile(Mockito.anyLong());
        this.mockMvc.perform(delete("/property/profile/1")).andExpect(status().isOk());
    }

    @Test
    void deleteApplicationTest() throws Exception {
        doNothing().when(applicationService).deleteApplication(Mockito.anyLong());
        this.mockMvc.perform(delete("/property/application/1")).andExpect(status().isOk());
    }


    @Test
    void getAllApplicationTest() throws Exception {
        when(applicationService.getAllApplication()).thenReturn(Collections.emptyList());
        this.mockMvc.perform(get("/property/application/all")).andExpect(status().isOk());
    }

    @Test
    void addApplicationTest() throws Exception {
        when(applicationService.saveApplication(any())).thenReturn(new Application());
        this.mockMvc.perform(post("/property/application")
                .content(asJsonString(new Application()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated());
    }


    @Test
    void editApplicationTest() throws Exception {
        when(applicationService.updateApplication(any())).thenReturn(new Application());
        this.mockMvc.perform(put("/property/application")
                .content(asJsonString(new Application()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    void addPropertyTest() throws Exception {
        when(propertyService.saveProperty(any())).thenReturn(new Property());
        this.mockMvc.perform(post("/property")
                .content(asJsonString(new Application()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    void editPropertyTest() throws Exception {
        when(propertyService.saveProperty(any())).thenReturn(new Property());
        this.mockMvc.perform(put("/property")
                .content(asJsonString(new Application()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }



    @Test
    void addProfileTest() throws Exception {
        when(profileService.saveProfile(any())).thenReturn(new Profile());
        this.mockMvc.perform(post("/property/profile")
                .content(asJsonString(new Application()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated());
    }

    @Test
    void editProfileTest() throws Exception {
        when(profileService.updateProfile(any())).thenReturn(new Profile());
        this.mockMvc.perform(put("/property/profile")
                .content(asJsonString(new Application()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    void deletePropertyByIdTest1() throws Exception {
        doThrow(new BaseException(400, "property not found")).when(propertyService).deleteProperty(anyLong());
        this.mockMvc.perform(delete("/property/1")).andExpect(status().isBadRequest());

    }


    @Test
    void deleteProfileTest1() throws Exception {

        doThrow(new BaseException(400, "property not found")).when(profileService).deleteProfile(Mockito.anyLong());
        this.mockMvc.perform(delete("/property/profile/0")).andExpect(status().isBadRequest());
    }

    @Test
    void deleteApplicationTest1() throws Exception {
        doThrow(new BaseException(400, "property not found")).when(applicationService).deleteApplication(Mockito.anyLong());
        this.mockMvc.perform(delete("/property/application/0")).andExpect(status().isBadRequest());
    }


    @Test
    void deletePropertyByIdTest() throws Exception {
        doNothing().when(propertyService).deleteProperty(anyLong());
        this.mockMvc.perform(delete("/property/0")).andExpect(status().isOk());
    }


    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
