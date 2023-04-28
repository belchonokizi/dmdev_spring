package com.dmdev.spring.integration;

import com.dmdev.spring.integration.annotation.IT;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;

@IT
//указываем скрипты, которые надо накатить на тестовую базу
@Sql({"classpath:sql/data.sql"})
public abstract class IntegrationTestBase {

    private static final PostgreSQLContainer<?> CONTAINER =
            new PostgreSQLContainer<>("postgres:14.1");

    //контейнер запустится 1 раз для всех тестов
    @BeforeAll
    static void runContainer() {
        CONTAINER.start();
    }

    //динаммический url
    @DynamicPropertySource
    static void postgresProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", CONTAINER::getJdbcUrl);
    }
}
