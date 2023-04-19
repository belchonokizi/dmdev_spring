package com.dmdev.spring.integration.database.repository;

import com.dmdev.spring.database.entity.Company;
import com.dmdev.spring.database.repository.CompanyRepository;
import com.dmdev.spring.integration.annotation.IT;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Commit;

import javax.persistence.EntityManager;

import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@IT
@RequiredArgsConstructor
class CompanyRepositoryTest {
    private static final Integer APPLE_ID = 5;
    private final EntityManager entityManager;
    private final CompanyRepository companyRepository;

    @Test
    void checkFindByQueries() {
        companyRepository.findByCompanyName("google");
        companyRepository.findAllByCompanyNameContainingIgnoreCase("a");

    }

    @Test
    void delete() {
        Optional<Company> maybeCompany = companyRepository.findById(APPLE_ID);
        assertTrue(maybeCompany.isPresent());
        maybeCompany.ifPresent(companyRepository::delete);
        //метод delete не отработает, пока не произойдет коммит транзакций
        //или не вызовется flush явно
        entityManager.flush();
        assertTrue(companyRepository.findById(APPLE_ID).isEmpty());
    }

    @Test
    void findById() {
        Company resultCompany = entityManager.find(Company.class, 1);
        assertNotNull(resultCompany);
        assertThat(resultCompany.getLocales()).hasSize(2);
    }

    @Test
    @Commit
    void save() {
        var company = Company.builder()
                .companyName("Apple1")
                .locales(Map.of(
                        "ru", "Apple описание",
                        "en", "Apple description"
                ))
                .build();
        entityManager.persist(company);
        assertNotNull(company.getId());
    }
}