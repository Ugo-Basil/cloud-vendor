package com.cloudvendor.vendor.repository;


import com.cloudvendor.vendor.model.CloudVendor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class CloudVendorRepositoryTest {
    //given -when -then

    @Autowired
    private CloudVendorRepository cloudVendorRepository;
    CloudVendor cloudVendor;


    @BeforeEach
    void setUp() {
        cloudVendor = new CloudVendor(1L,"AWS","Seattle","1234567890");
        cloudVendorRepository.save(cloudVendor);
    }

    @AfterEach
    void tearDown() {
        cloudVendor = null;
        cloudVendorRepository.deleteAll();
    }

    // @Test case Success
    @Test
    void testFindByVendorName_Found() {
        List<CloudVendor> cloudVendorList = cloudVendorRepository.findByVendorName("AWS");
        assertThat(cloudVendorList.get(0).getVendorId()).isEqualTo(cloudVendor.getVendorId());
        assertThat(cloudVendorList.get(0).getVendorAddress()).isEqualTo(cloudVendor.getVendorAddress());
    }


    // @Test case Failure
    @Test
    void testFindByVendorName_NotFound() {
        List<CloudVendor> cloudVendorList = cloudVendorRepository.findByVendorName("GCP");
          assertThat(cloudVendorList.isEmpty()).isTrue();
    }



}
