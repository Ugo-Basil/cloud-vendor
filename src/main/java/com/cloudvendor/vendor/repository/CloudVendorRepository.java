package com.cloudvendor.vendor.repository;

import com.cloudvendor.vendor.model.CloudVendor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CloudVendorRepository extends JpaRepository<CloudVendor, Long> {

    List<CloudVendor> findByVendorName(String vendorName);
}
