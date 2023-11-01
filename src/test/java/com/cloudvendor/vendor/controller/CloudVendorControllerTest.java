package com.cloudvendor.vendor.controller;

import com.cloudvendor.vendor.model.CloudVendor;
import com.cloudvendor.vendor.service.interfaces.CloudVendorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CloudVendorController.class)
class CloudVendorControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CloudVendorService cloudVendorService;


    CloudVendor cloudVendor_one;
    CloudVendor cloudVendor_two;

    CloudVendor getCloudVendor_three;
    List<CloudVendor> cloudVendorList = new ArrayList<>();



    @BeforeEach
    void setUp() {

        cloudVendor_one = new CloudVendor(1L,"AWS", "Amazon Web Services",
                "https://aws.amazon.com/");

        cloudVendor_two = new CloudVendor(2L,"GCP", "Google Cloud Platform",
                "https://cloud.google.com/");


        cloudVendorList.add(cloudVendor_one);

        cloudVendorList.add(cloudVendor_two);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testGetCloudVendors() throws Exception {
        when(cloudVendorService.getAllCloudVendors()).thenReturn(cloudVendorList);

        this.mockMvc.perform(get("/vendor/api/vendors"))
                .andDo(print()).andExpect(status().isOk());

    }

    @Test
    void testGetCloudVendor() throws Exception {
        when(cloudVendorService.getCloudVendor(1L))
                .thenReturn(Optional.ofNullable(cloudVendor_one));

        this.mockMvc.perform(get("/vendor/api/1"))
                .andDo(print()).andExpect(status().isOk());


    }

    @Test
    void testCreateCloudVendor() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(cloudVendor_one);

        when(cloudVendorService.createCloudVendor(cloudVendor_one))
                .thenReturn("Created Successfully");

        this.mockMvc.perform(post("/vendor/api/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void updateCloudVendor() throws Exception {
        // Configure the behavior of the cloudVendorService.updateCloudVendor method
        when(cloudVendorService.updateCloudVendor(cloudVendor_one))
                .thenReturn("Updated Successfully");

        // Perform the behavior being tested
        this.mockMvc.perform(put("/vendor/api/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"vendorName\":\"AWS\",\"vendorAddress\":\"Amazon Web Services\",\"vendorPhoneNumber\":\"https://aws.amazon.com/\"}"))
                .andDo(print()).andReturn();




    }


    @Test
    void testDeleteCloudVendor() throws Exception {
        when (cloudVendorService.deleteCloudVendor(1L))
                .thenReturn("Success");

        this.mockMvc.perform(get("/vendor/api/1"))
                .andDo(print()).andExpect(status().isOk());


    }
}