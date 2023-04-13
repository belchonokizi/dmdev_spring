package com.dmdev.spring.database.pool;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConnectionPool {

    private final String username;

    public ConnectionPool(@Value(value = "${db.username}") String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
