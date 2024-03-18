package com.sk.rk.service;

import com.sk.rk.exception.BaseException;
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

@Service
public class ApplicationService {

    @Autowired
    ApplicationRepository applicationRepository;

    @Autowired
    PropertyRepository propertyRepository;

    public List<Map<String, Object>> getAllApplication() {
        return applicationRepository.getAllAplications();
    }

    public Application saveApplication(Application application) throws BaseException {

        List<Application> applications = applicationRepository
                .findByApplicationNameIgnoreCase(application.getApplicationName());

        if(CollectionUtils.isEmpty(applications)) {
            return applicationRepository.save(application);
        } else {
            throw new BaseException(400, "Application already exists with name : " + application.getApplicationName());
        }
    }


    public Application updateApplication(Application application) throws BaseException {

        List<Application> applications = applicationRepository
                .findByApplicationNameIgnoreCase(application.getApplicationName());

        if(CollectionUtils.isEmpty(applications)) {
            return applicationRepository.save(application);
        } else {
            throw new BaseException(400, "Application already exists with name : " + application.getApplicationName());
        }
    }

    @Transient
    public void deleteApplication(Long applicationId) throws BaseException {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(()->new BaseException(400, "Application not found"));

        List<Property> properties = propertyRepository.findByApplication(application);

        if(!CollectionUtils.isEmpty(properties)) {
            throw new BaseException(400, "Can not delete application, it is mapped with property");
        }
        applicationRepository.delete(application);
    }
}
