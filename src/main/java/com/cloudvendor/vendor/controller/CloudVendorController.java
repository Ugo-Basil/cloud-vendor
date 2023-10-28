package com.cloudvendor.vendor.controller;

import com.cloudvendor.vendor.model.CloudVendor;
import com.cloudvendor.vendor.response.ResponseHandler;
import com.cloudvendor.vendor.service.interfaces.CloudVendorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vendor/api")
public class CloudVendorController {
    CloudVendorService cloudVendorService;

    //constructor
    public CloudVendorController(CloudVendorService cloudVendorService) {
        this.cloudVendorService = cloudVendorService;
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

    public ResponseEntity<Object> updateCloudVendor(@PathVariable("vendorId")
                                    @RequestBody CloudVendor cloudVendor)
    {

       return ResponseHandler.responseBuilder(
                "Success", HttpStatus.OK,
                cloudVendorService.updateCloudVendor(cloudVendor));
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
