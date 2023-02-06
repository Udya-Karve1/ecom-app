package com.sk.rk.controller;

import com.sk.rk.model.Application;
import com.sk.rk.model.Profile;
import com.sk.rk.model.Property;
import com.sk.rk.service.ApplicationService;
import com.sk.rk.service.ProfileService;
import com.sk.rk.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sk.rk.model.PropertyAddRequest;
import com.sk.rk.model.PropertyUpdateRequest;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/property")
public class PropertyController {

    @Autowired
    PropertyService propertyService;

    @Autowired
    ApplicationService applicationService;

    @Autowired
    ProfileService profileService;

    @GetMapping("/all")
    public ResponseEntity<List<Map<String, Object>>> getAllProperty() {
        return new ResponseEntity(propertyService.getAllProperties(), HttpStatus.OK);
    }

    @GetMapping("/profile/all")
    public ResponseEntity<List<Map<String, Object>>> getAllProfile() {
        return new ResponseEntity(profileService.getAllProfiles(), HttpStatus.OK);
    }

    @DeleteMapping("/profile/{profile-id}")
    public ResponseEntity<String> deleteProfile(
            @PathVariable("profile-id") Long profileId
    ) {
        try {
            profileService.deleteProfile(profileId);
            return new ResponseEntity("Profile deleted.", HttpStatus.OK);
        } catch(Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }




    @DeleteMapping("/application/{application-id}")
    public ResponseEntity<String> deleteApplication(
            @PathVariable("application-id") Long applicationId
    ) {
        try {
            applicationService.deleteApplication(applicationId);
            return new ResponseEntity("Application deleted.", HttpStatus.OK);
        } catch(Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }



    @GetMapping("/application/all")
    public ResponseEntity<List<Map<String, Object>>> getAllApplication() {
        return new ResponseEntity(applicationService.getAllApplication(), HttpStatus.OK);
    }

    @PostMapping("/application")
    public ResponseEntity<Application> addApplication(
            @RequestBody Application applicationEntity
    ) throws Exception {
        return new ResponseEntity<>(applicationService.saveApplication(applicationEntity), HttpStatus.OK);
    }
    @PutMapping("/application")
    public ResponseEntity<Application> editApplication(
            @RequestBody Application applicationEntity
    ) throws Exception {
        return new ResponseEntity<>(applicationService.updateApplication(applicationEntity), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Property> addProperty(
            @RequestBody PropertyAddRequest propertyEntity
    ) throws Exception {
        return new ResponseEntity<>(propertyService.saveProperty(propertyEntity), HttpStatus.OK);
    }


    @PutMapping
    public ResponseEntity<Property> editProperty(
            @RequestBody PropertyUpdateRequest propertyEntity
    ) throws Exception {
        return new ResponseEntity<>(propertyService.updateProperty(propertyEntity), HttpStatus.OK);
    }


    @PostMapping("/profile")
    public ResponseEntity<Profile> addProfile(
            @RequestBody Profile profileEntity
    ) throws Exception {
        return new ResponseEntity(profileService.saveProfile(profileEntity), HttpStatus.OK);
    }

    @PutMapping("/profile")
    public ResponseEntity<Profile> editProfile(
            @RequestBody Profile profileEntity
    ) throws Exception {
        return new ResponseEntity(profileService.updateProfile(profileEntity), HttpStatus.OK);
    }

    @DeleteMapping("/{property-id}")
    public ResponseEntity<String> deleteProperty(
            @PathVariable("property-id") Long propertyId
    ) {
        try {
            propertyService.deleteProperty(propertyId);
            return new ResponseEntity("Property deleted.", HttpStatus.OK);
        } catch(Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
