package com.narosco.testrecoveryjson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestTestRecoveryJsonApplication {

    public static void main(String[] args) {
        SpringApplication.from(TestRecoveryJsonApplication::main).with(TestTestRecoveryJsonApplication.class).run(args);
    }

}
