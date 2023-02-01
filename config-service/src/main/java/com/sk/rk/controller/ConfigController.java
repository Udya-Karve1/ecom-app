package com.sk.rk.controller;

import com.sk.rk.model.ConfigResponse;
import com.sk.rk.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/config")
public class ConfigController {

    @Autowired
    ConfigService configService;

    @GetMapping("/{application}/{profile}")
    public ResponseEntity<ConfigResponse>  getApplicationProfile(
            @PathVariable(name = "application") String application,
            @PathVariable(name = "profile") String profile
    ) {

        return new ResponseEntity<>(configService.getConfigResponse(application, profile), HttpStatus.OK);
    }

    @GetMapping("/{application}/{profile}/{label}")
    public ResponseEntity<ConfigResponse>  getApplicationProfileLabel(
            @PathVariable(name = "application") String application,
            @PathVariable(name = "profile") String profile,
            @PathVariable(name = "label") String label
    ) {
        return new ResponseEntity<>(configService.getConfigResponse(application, profile, label), HttpStatus.OK);
    }

    @GetMapping(value = {"/{application}.yml", "/{application}-{profile}.yml", "/{application}-{profile}.properties", "/{application}.properties"})
    public ResponseEntity<ConfigResponse>  getApplicationProfileExt(
            @PathVariable(name = "application") String application,
            @PathVariable(name = "profile") String profile
    ) {
        return new ResponseEntity<>(configService.getConfigResponse(application, profile), HttpStatus.OK);
    }

    @GetMapping(value = {"/{label}/{application}-{profile}.yml", "/{label}/{application}-{profile}.properties"})
    public ResponseEntity<ConfigResponse>  getLabelApplicationProfile(
            @PathVariable(name = "application") String application,
            @PathVariable(name = "profile") String profile,
            @PathVariable(name = "label") String label
    ) {
        return new ResponseEntity<>(configService.getConfigResponse(application, profile, label), HttpStatus.OK);
    }
}
