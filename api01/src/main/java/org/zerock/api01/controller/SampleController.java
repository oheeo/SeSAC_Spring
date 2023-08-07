package org.zerock.api01.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/sample")
public class SampleController {

    // 단순하게 문자열의 배열을 반환
    // '/api/sample/doA'경로로 호출 가능
    @ApiOperation("Sample GET doA")
    @GetMapping("/doA")
    public List<String> doA() {
        return Arrays.asList("AAA","BBB","CCC");
    }
}
