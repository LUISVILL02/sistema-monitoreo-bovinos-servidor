package org.server.apimonitoreo;

import org.springframework.boot.SpringApplication;

public class TestApiMonitoreoApplication {

    public static void main(String[] args) {
        SpringApplication.from(ApiMonitoreoApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
