package com.example.apitest;

import com.example.apitest.controller.RestTemplateTestController;
import com.example.apitest.model.response.LogoutApiResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class RestTemplateTestServiceTest {

    @Autowired
    RestTemplateTestController controller;

    @Test
    public void restTemplateTest() {
        ResponseEntity<LogoutApiResponse> entity = controller.restTemplateTest1();
        System.out.println(">>> entity = " + entity.getStatusCode());
        System.out.println(">>> entity = " + entity.getBody().getStatus());
        Assertions.assertEquals(entity.getStatusCodeValue(), 200);
    }

}
