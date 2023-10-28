package com.cloudvendor.vendor.repository;

import com.cloudvendor.vendor.model.CloudVendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CloudVendorRepository extends JpaRepository<CloudVendor, Long> {
}
