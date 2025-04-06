package com.example.igniteserver.config;

import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.configuration.ConnectorConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IgniteRestConfig {

    @Bean
    public IgniteConfiguration igniteConfiguration() {
        IgniteConfiguration cfg = new IgniteConfiguration();
        
        // Enable REST API
        ConnectorConfiguration connectorCfg = new ConnectorConfiguration();
        connectorCfg.setPort(8081); // REST API port
        
        // Enable SQL
        cfg.setConnectorConfiguration(connectorCfg);
        
        return cfg;
    }
} 