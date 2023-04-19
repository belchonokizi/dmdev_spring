package com.dmdev.spring.database.repository;

import com.dmdev.spring.bpp.Transaction;
import com.dmdev.spring.database.entity.Company;
import com.dmdev.spring.database.pool.ConnectionPool;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.Optional;

@Slf4j
@Repository
@Transaction
@RequiredArgsConstructor
public class CompanyRepository implements CrudRepository<Integer, Company>{

    private final ConnectionPool connectionPool;

    @PostConstruct
    private void init() {
        log.info("init company repository");
    }

    @Override
    public Optional<Company> findById(Integer id) {
        log.info("findById method...");
        return Optional.of(new Company(id, null, Collections.emptyMap()));
    }

    @Override
    public void delete(Company entity) {
        log.info("delete method...");
    }
}
