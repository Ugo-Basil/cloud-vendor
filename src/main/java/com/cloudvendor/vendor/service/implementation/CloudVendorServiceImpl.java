package com.cloudvendor.vendor.service.implementation;

import com.cloudvendor.vendor.exception.CloudVendorNotFoundException;
import com.cloudvendor.vendor.model.CloudVendor;
import com.cloudvendor.vendor.repository.CloudVendorRepository;
import com.cloudvendor.vendor.service.interfaces.CloudVendorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CloudVendorServiceImpl implements CloudVendorService {

    CloudVendorRepository cloudVendorRepository;

    public CloudVendorServiceImpl(CloudVendorRepository cloudVendorRepository) {
        this.cloudVendorRepository = cloudVendorRepository;
    }

    @Override
    public String createCloudVendor(CloudVendor cloudVendor) {
        cloudVendorRepository.save(cloudVendor);
        return "Created Successfully";
    }

    @Override
    public String updateCloudVendor(CloudVendor cloudVendor) {
        //More Business Logic

        if(cloudVendorRepository.findById(cloudVendor.getVendorId()).isEmpty())
            throw  new CloudVendorNotFoundException("Cloud Vendor does not exist");
        cloudVendorRepository.save(cloudVendor);
        return "Updated Successfully";
    }




    @Override
    public String deleteCloudVendor(Long cloudVendorId) {


        if(cloudVendorRepository.findById(cloudVendorId).isEmpty())
            throw  new CloudVendorNotFoundException("Cloud Vendor does not exist");
        cloudVendorRepository.deleteById(cloudVendorId);
        return "Success";
    }


    @Override
    public Optional<CloudVendor> getCloudVendor(Long cloudVendorId) {


        if(cloudVendorRepository.findById(cloudVendorId).isEmpty())
            throw  new CloudVendorNotFoundException("Cloud Vendor does not exist");
        return  cloudVendorRepository.findById(cloudVendorId);
    }

    @Override
    public List<CloudVendor> getAllCloudVendors() {
        return cloudVendorRepository.findAll();
    }
}
