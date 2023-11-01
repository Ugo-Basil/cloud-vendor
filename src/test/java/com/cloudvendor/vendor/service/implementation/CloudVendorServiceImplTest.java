package com.cloudvendor.vendor.service.implementation;

import com.cloudvendor.vendor.model.CloudVendor;
import com.cloudvendor.vendor.repository.CloudVendorRepository;
import com.cloudvendor.vendor.service.interfaces.CloudVendorService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;


class CloudVendorServiceImplTest {

    @Mock
    private CloudVendorRepository cloudVendorRepository;
    private CloudVendorService cloudVendorService;

    AutoCloseable autoCloseable;
    CloudVendor cloudVendor;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        cloudVendorService = new CloudVendorServiceImpl(cloudVendorRepository);
        cloudVendor = new CloudVendor(1L,"AWS", "Amazon Web Services",
                "https://aws.amazon.com/");
    }

    @AfterEach
    void tearDown() throws  Exception {
        autoCloseable.close();
    }

    @Test
    void testCreateCloudVendor() {
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class);

        when(cloudVendorRepository.save(cloudVendor)).thenReturn(cloudVendor);
        assertThat(cloudVendorService.createCloudVendor(cloudVendor))
                .isEqualTo("Created Successfully");
    }

    @Test
    void testGetCloudVendor() {
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class);
        System.out.println("print " + cloudVendor.toString());
       when(cloudVendorRepository.findById(1L)).thenReturn(Optional.ofNullable(cloudVendor));
       assertThat(cloudVendorService.getCloudVendor(1L).get().getVendorName())
               .isEqualTo(cloudVendor.getVendorName());
    }

    @Test
    void  testGetByVendorName() {
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class);

        when(cloudVendorRepository.findByVendorName("Aws")).thenReturn(
                new ArrayList<CloudVendor>(Collections.singleton(cloudVendor)) );
        assertThat(cloudVendorService.getByVendorName("Aws").get(0).getVendorId())
                .isEqualTo(cloudVendor.getVendorId());
    }

    @Test
    void testGetAllCloudVendors() {
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class);

        when(cloudVendorRepository.findAll()).thenReturn(
                new ArrayList<CloudVendor>(Collections.singleton(cloudVendor)) );
        assertThat(cloudVendorService.getAllCloudVendors().get(0).getVendorId())
                .isEqualTo(cloudVendor.getVendorId());
    }

    @Test
    void testUpdateCloudVendor() {
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class);

            when(cloudVendorRepository.findById(cloudVendor.getVendorId())).thenReturn(Optional.ofNullable(cloudVendor));
            assertThat(cloudVendorService.updateCloudVendor(cloudVendor))
                    .isEqualTo("Updated Successfully");


    }

        @Test
    void testDeleteCloudVendor() {
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class);

        when(cloudVendorRepository.findById(1L)).thenReturn(Optional.ofNullable(cloudVendor));
        assertThat(cloudVendorService.deleteCloudVendor(1L))
                .isEqualTo("Success");

    }
}