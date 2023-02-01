package com.sk.rk.service;

import com.sk.rk.model.Application;
import com.sk.rk.model.Property;
import com.sk.rk.repository.ApplicationRepository;
import com.sk.rk.repository.PropertyRepository;
import jakarta.persistence.Transient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ApplicationService {

    @Autowired
    ApplicationRepository applicationRepository;

    @Autowired
    PropertyRepository propertyRepository;

    public List<Map<String, Object>> getAllApplication() {
        return applicationRepository.getAllAplications();
    }

    public Application saveApplication(Application application) throws Exception {

        List<Application> applications = applicationRepository
                .findByApplicationIgnoreCase(application.getApplication());

        if(CollectionUtils.isEmpty(applications)) {
            return applicationRepository.save(application);
        } else {
            throw new Exception("Application already exists with name : " + application.getApplication());
        }
    }


    public Application updateApplication(Application application) throws Exception {

        List<Application> applications = applicationRepository
                .findByApplicationIgnoreCase(application.getApplication());

        if(CollectionUtils.isEmpty(applications)) {
            return applicationRepository.save(application);
        } else {
            throw new Exception("Application already exists with name : " + application.getApplication());
        }
    }

    @Transient
    public void deleteApplication(Long applicationId) throws Exception {

        List<Property> properties = propertyRepository.findByApplicationId(applicationId);

        if(!CollectionUtils.isEmpty(properties)) {
            throw new Exception("Can not delete application, it is mapped with property");
        }
        Optional<Application> profileEntity = applicationRepository.findById(applicationId);
        if(profileEntity.isPresent()) {
            applicationRepository.delete(profileEntity.get());
        } else {
            throw new Exception("Application not exists.");
        }
    }
}
