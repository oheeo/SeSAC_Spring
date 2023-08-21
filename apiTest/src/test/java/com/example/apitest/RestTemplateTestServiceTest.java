package com.example.apitest;

//import com.example.apitest.controller.PushEventTestController;
import com.example.apitest.controller.RestTemplateTestController;
import com.example.apitest.model.response.LogoutApiResponse;
import com.example.apitest.model.response.PushEventApiResponse;
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

//    @Autowired
//    PushEventTestController restController;
//    @Test
//    public void PushEventTest() {
//        ResponseEntity<PushEventApiResponse> entity = restController.restTemplateTest2();
//        System.out.println(">>> entity = " + entity.getStatusCode());
//        System.out.println(">>> entity = " + entity.getBody().getStatus());
//        System.out.println(">>> entity = " + entity.getBody().getMessage());
//        Assertions.assertEquals(entity.getStatusCodeValue(), 200);
//    }

}
