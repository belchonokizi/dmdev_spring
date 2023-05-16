package com.dmdev.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.util.Map;

@Validated
@ConfigurationProperties(prefix = "db")
public record DatabaseProperties(String username,
                                 String password,
                                 String driver,
                                 String url,
                                 String hosts,
                                 Map<String, Object> properties,
                                 PoolProperties pool) {

    public record PoolProperties(int size,
                                 int timeout) {
    }
}
