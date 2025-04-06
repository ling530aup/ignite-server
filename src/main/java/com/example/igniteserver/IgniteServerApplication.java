package com.example.igniteserver;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class IgniteServerApplication {

    @Autowired
    private IgniteConfiguration igniteConfiguration;

    public static void main(String[] args) {
        SpringApplication.run(IgniteServerApplication.class, args);
    }

    @Bean
    public Ignite igniteInstance() {
        return Ignition.start(igniteConfiguration);
    }
} 