//package com.example.apitest.controller;
//
//import com.example.apitest.model.response.PushEventApiResponse;
//import com.example.apitest.service.PushEventApiService;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@AllArgsConstructor
//@Slf4j
//public class PushEventTestController {
//
//    private final PushEventApiService pushEventApiService;
//
//    @PostMapping
//    public ResponseEntity<PushEventApiResponse> restTemplateTest2() {
//        return ResponseEntity.ok(pushEventApiService.callPostExternalServer());
//    }
//}
