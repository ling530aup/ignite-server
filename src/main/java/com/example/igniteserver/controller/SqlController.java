package com.example.igniteserver.controller;

import org.apache.ignite.Ignite;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/sql")
public class SqlController {

    @Autowired
    private Ignite ignite;

    @PostMapping("/execute")
    public List<List<?>> executeQuery(@RequestBody Map<String, String> request) {
        String sql = request.get("sql");
        String cacheName = request.get("cacheName");

        SqlFieldsQuery query = new SqlFieldsQuery(sql);
        return ignite.cache(cacheName).query(query).getAll();
    }

    @PostMapping("/create-table")
    public String createTable(@RequestBody Map<String, String> request) {
        String tableName = request.get("tableName");
        String cacheName = request.get("cacheName");
        String columns = request.get("columns");

        String sql = String.format("CREATE TABLE IF NOT EXISTS %s (%s) WITH \"template=partitioned,CACHE_NAME=%s\"",
                tableName, columns, cacheName);

        SqlFieldsQuery query = new SqlFieldsQuery(sql);
        ignite.cache(cacheName).query(query).getAll();
        return "Table created successfully";
    }

    @PostMapping("/insert")
    public String insertData(@RequestBody Map<String, Object> request) {
        String tableName = request.get("tableName").toString();
        String cacheName = request.get("cacheName").toString();
        Map<String, Object> data = (Map<String, Object>) request.get("data");

        String columns = String.join(", ", data.keySet());
        String values = data.values().stream()
                .map(v -> v instanceof String ? "'" + v + "'" : v.toString())
                .collect(Collectors.joining(", "));

        String sql = String.format("INSERT INTO %s (%s) VALUES (%s)", tableName, columns, values);

        SqlFieldsQuery query = new SqlFieldsQuery(sql);
        ignite.cache(cacheName).query(query).getAll();
        return "Data inserted successfully";
    }
} 