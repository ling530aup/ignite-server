package com.example.igniteserver.config;

import com.example.igniteserver.model.Person;
import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.vm.TcpDiscoveryVmIpFinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class IgniteConfig {

    @Bean
    public Ignite igniteInstance() {
        IgniteConfiguration cfg = new IgniteConfiguration();
        
        // 设置节点名称
        cfg.setIgniteInstanceName("ignite-server");
        
        // 配置 TCP 发现
        TcpDiscoverySpi discoverySpi = new TcpDiscoverySpi();
        TcpDiscoveryVmIpFinder ipFinder = new TcpDiscoveryVmIpFinder();
        ipFinder.setAddresses(Collections.singletonList("127.0.0.1:47500..47509"));
        discoverySpi.setIpFinder(ipFinder);
        cfg.setDiscoverySpi(discoverySpi);

        // 配置 Person 缓存
        CacheConfiguration<Long, Person> personCacheCfg = new CacheConfiguration<>("PersonCache");
        personCacheCfg.setIndexedTypes(Long.class, Person.class);
        personCacheCfg.setBackups(1);
        
        cfg.setCacheConfiguration(personCacheCfg);

        return Ignition.start(cfg);
    }
} 