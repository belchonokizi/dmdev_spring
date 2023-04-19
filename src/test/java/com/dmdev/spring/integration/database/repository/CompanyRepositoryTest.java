package com.dmdev.spring.integration.database.repository;

import com.dmdev.spring.database.entity.Company;
import com.dmdev.spring.integration.annotation.IT;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@IT
@RequiredArgsConstructor
@Transactional
class CompanyRepositoryTest {
    private final EntityManager entityManager;

    @Test
    void findById() {
        Company resultCompany = entityManager.find(Company.class, 1);
        assertNotNull(resultCompany);
        assertThat(resultCompany.getLocales()).hasSize(2);
    }
}