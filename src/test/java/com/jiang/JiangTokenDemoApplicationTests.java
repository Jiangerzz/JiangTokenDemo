package com.jiang;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class JiangTokenDemoApplicationTests {

    @Test
    void  testBCryptPasswordEncoder(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode("1347477532");
        String encode1 = bCryptPasswordEncoder.encode("1347477532");
        System.out.println(encode);
        System.out.println(encode1);
        System.out.println(bCryptPasswordEncoder.matches("1347477532","$2a$10$OLW4CnZbScM2.kzrgl8ZP.oOQHLRKU0eAVkeDMqQU9ywQhHIuLFD2"));
    }

}
