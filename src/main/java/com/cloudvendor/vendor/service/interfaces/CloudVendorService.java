package com.cloudvendor.vendor.service.interfaces;

import com.cloudvendor.vendor.model.CloudVendor;

import java.util.List;
import java.util.Optional;

public interface CloudVendorService {
    public  String createCloudVendor(CloudVendor cloudVendor);

    public  String updateCloudVendor(CloudVendor cloudVendor);

    public String deleteCloudVendor(Long cloudVendorId);

    public Optional<CloudVendor> getCloudVendor(Long cloudVendorId);

    public List<CloudVendor> getAllCloudVendors();
}
