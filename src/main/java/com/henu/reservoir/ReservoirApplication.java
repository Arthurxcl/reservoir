package com.henu.reservoir;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.henu.reservoir.dao")
public class ReservoirApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReservoirApplication.class, args);
    }

}
