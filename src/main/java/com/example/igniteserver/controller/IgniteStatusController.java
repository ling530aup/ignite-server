package com.example.igniteserver.controller;

import org.apache.ignite.Ignite;
import org.apache.ignite.cluster.ClusterMetrics;
import org.apache.ignite.cluster.ClusterNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/ignite")
public class IgniteStatusController {

    @Autowired
    private Ignite ignite;

    @GetMapping("/status")
    public Map<String, Object> getStatus() {
        Map<String, Object> status = new HashMap<>();
        
        // Get cluster metrics
        ClusterNode localNode = ignite.cluster().localNode();
        ClusterMetrics metrics = localNode.metrics();
        
        status.put("nodeId", localNode.id().toString());
        status.put("isClient", localNode.isClient());
        status.put("hostNames", localNode.hostNames());
        status.put("totalCpus", metrics.getTotalCpus());
        status.put("heapMemoryUsed", metrics.getHeapMemoryUsed());
        status.put("heapMemoryTotal", metrics.getHeapMemoryTotal());
        status.put("nonHeapMemoryUsed", metrics.getNonHeapMemoryUsed());
        status.put("nonHeapMemoryTotal", metrics.getNonHeapMemoryTotal());
        status.put("activeJobs", metrics.getCurrentActiveJobs());
        status.put("waitingJobs", metrics.getCurrentWaitingJobs());
        status.put("totalNodes", ignite.cluster().nodes().size());
        
        return status;
    }

    @GetMapping("/caches")
    public Map<String, Object> getCaches() {
        Map<String, Object> caches = new HashMap<>();
        
        ignite.cacheNames().forEach(cacheName -> {
            Map<String, Object> cacheInfo = new HashMap<>();
            cacheInfo.put("size", ignite.cache(cacheName).size());
            caches.put(cacheName, cacheInfo);
        });
        
        return caches;
    }
} 