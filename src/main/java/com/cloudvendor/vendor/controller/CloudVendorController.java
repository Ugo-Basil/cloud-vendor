package com.cloudvendor.vendor.controller;

import com.cloudvendor.vendor.exception.CloudVendorNotFoundException;
import com.cloudvendor.vendor.model.CloudVendor;
import com.cloudvendor.vendor.repository.CloudVendorRepository;
import com.cloudvendor.vendor.response.ResponseHandler;
import com.cloudvendor.vendor.service.interfaces.CloudVendorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/vendor/api")
public class CloudVendorController {
    CloudVendorService cloudVendorService;
    private final CloudVendorRepository cloudVendorRepository;

    //constructor
    public CloudVendorController(CloudVendorService cloudVendorService,
                                 CloudVendorRepository cloudVendorRepository) {
        this.cloudVendorService = cloudVendorService;
        this.cloudVendorRepository = cloudVendorRepository;
    }

    //Get All Cloud Vendors
    @GetMapping("/vendors")
    public ResponseEntity<Object> getCloudVendors ()
    {
        return ResponseHandler.responseBuilder("Success", HttpStatus.OK,
                cloudVendorService.getAllCloudVendors());
    }

    //Get Cloud Vendor
    @GetMapping("{vendorId}")
    public ResponseEntity<Object> getCloudVendor(@PathVariable("vendorId") Long vendorId)
    {
       return ResponseHandler.responseBuilder("Success", HttpStatus.OK,
               cloudVendorService.getCloudVendor(vendorId));
    }

    //Create Cloud Vendor
    @PostMapping("/create")
    public  ResponseEntity<Object> createCloudVendor(@RequestBody CloudVendor cloudVendor)
    {

           return ResponseHandler.responseBuilder(
                    "Success", HttpStatus.OK,
                    cloudVendorService.createCloudVendor(cloudVendor));

    }

    //Update Cloud Vendor
    @PutMapping("{vendorId}")
    public ResponseEntity<Object> updateCloudVendor(@PathVariable("vendorId") Long vendorId, @RequestBody CloudVendor cloudVendor) {


        Optional<CloudVendor> existingVendor = cloudVendorRepository.findById(vendorId);
        if (existingVendor.isEmpty()) {
            throw new CloudVendorNotFoundException("Cloud Vendor does not exist");
        }

        CloudVendor updatedVendor = existingVendor.get();
        updatedVendor.setVendorName(cloudVendor.getVendorName());
        updatedVendor.setVendorAddress(cloudVendor.getVendorAddress());
        updatedVendor.setVendorPhoneNumber(cloudVendor.getVendorPhoneNumber());


        cloudVendorRepository.save(updatedVendor);


        return ResponseHandler.responseBuilder("Cloud Vendor updated successfully", HttpStatus.OK, updatedVendor);
    }



    //Delete Cloud Vendor
    @DeleteMapping("{vendorId}")
    public   ResponseEntity<Object> deleteCloudVendor(@PathVariable("vendorId")
                                                        Long vendorId)

    {
        return ResponseHandler.responseBuilder(
                "Success", HttpStatus.OK,
                cloudVendorService.deleteCloudVendor(vendorId));
    }
}
