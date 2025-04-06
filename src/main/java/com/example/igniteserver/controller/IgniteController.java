package com.example.igniteserver.controller;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class IgniteController {

    @Autowired
    private Ignite ignite;

    @PostMapping("/cache/{name}")
    public String createCache(@PathVariable String name) {
        IgniteCache<String, String> cache = ignite.getOrCreateCache(name);
        return "Cache " + name + " created successfully";
    }

    @PutMapping("/cache/{name}/{key}")
    public String putValue(@PathVariable String name, 
                          @PathVariable String key, 
                          @RequestBody String value) {
        IgniteCache<String, String> cache = ignite.cache(name);
        cache.put(key, value);
        return "Value stored successfully";
    }

    @GetMapping("/cache/{name}/{key}")
    public String getValue(@PathVariable String name, @PathVariable String key) {
        IgniteCache<String, String> cache = ignite.cache(name);
        return cache.get(key);
    }
} 