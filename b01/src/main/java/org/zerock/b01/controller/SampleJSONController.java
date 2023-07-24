package org.zerock.b01.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  // JSON 형태로 데이터를 뿌려주는 역할
@Log4j2
public class SampleJSONController {

    @GetMapping("/helloArr")
    public String[] helloArr() {

        log.info("helloArr..................");

        return new String[]{"AAA","BBB","CCC"};
    }
}
