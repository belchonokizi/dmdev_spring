package com.dmdev.spring.database.pool;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConnectionPool {

    @Value(value = "${db.username}")
    private final String username;

    public String getUsername() {
        return username;
    }
}
