package com.dmdev.spring.integration.service;

import com.dmdev.spring.database.pool.ConnectionPool;
import com.dmdev.spring.integration.annotation.IT;
import com.dmdev.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

@IT
@RequiredArgsConstructor
public class UserServiceIT {

    private final UserService userService;
    private final ConnectionPool connectionPool;

    @Test
    void test() {
        System.out.println();
    }
}
