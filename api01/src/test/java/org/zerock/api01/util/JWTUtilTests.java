package org.zerock.api01.util;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
@Log4j2
public class JWTUtilTests {

    @Autowired
    private JWTUtil jwtUtil;

    @Test
    public void testGenetate() {

        Map<String, Object> claimMap = Map.of("mid", "ABCD");

        String jwtStr = jwtUtil.generateToken(claimMap,1);

        log.info(jwtStr);
    }

    @Test
    public void testValidate() {

        // 유효 시간이 지난 토큰
        String jwtStr = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2OTEzODY5MzEsIm1pZCI6IkFCQ0QiLCJpYXQiOjE2OTEzODY4NzF9.0Af3TdHuDASvLrzFeO5h80JMXeGDSoxUUQNp2zgPNYw";

        Map<String, Object> claim = jwtUtil.validateToken(jwtStr);

        log.info(claim);
    }

    @Test
    public void testAll() {

        String jwtStr = jwtUtil.generateToken(Map.of("mid","AAA","email","aaa@bbb.com"),1);

        log.info(jwtStr);

        Map<String, Object> claim = jwtUtil.validateToken(jwtStr);

        log.info("MID: " + claim.get("mid"));

        log.info("EMAIL: " + claim.get("email"));
    }
}
