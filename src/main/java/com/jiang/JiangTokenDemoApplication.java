package com.jiang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jiang.mapper")
public class JiangTokenDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(JiangTokenDemoApplication.class, args);
    }

}
