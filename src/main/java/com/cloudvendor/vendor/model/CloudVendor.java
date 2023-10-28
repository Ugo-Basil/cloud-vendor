package com.cloudvendor.vendor.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cloud_vendor_api")
public class CloudVendor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long vendorId;

    private  String vendorName;

    private String vendorAddress;

    private  String vendorPhoneNumber;

    public CloudVendor() {
    }

    public CloudVendor(Long vendorId, String vendorName, String vendorAddress, String vendorPhoneNumber) {
//        this.vendorId = vendorId;
        this.vendorName = vendorName;
        this.vendorAddress = vendorAddress;
        this.vendorPhoneNumber = vendorPhoneNumber;
    }

    public Long getVendorId() {
        return vendorId;
    }

//    public void setVendorId(Long vendorId) {
//        this.vendorId = vendorId;
//    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorAddress() {
        return vendorAddress;
    }

    public void setVendorAddress(String vendorAddress) {
        this.vendorAddress = vendorAddress;
    }

    public String getVendorPhoneNumber() {
        return vendorPhoneNumber;
    }

    public void setVendorPhoneNumber(String vendorPhoneNumber) {
        this.vendorPhoneNumber = vendorPhoneNumber;
    }
}
