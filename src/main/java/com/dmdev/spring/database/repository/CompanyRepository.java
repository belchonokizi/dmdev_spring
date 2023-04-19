package com.dmdev.spring.database.repository;

import com.dmdev.spring.database.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

    //использование PartTreeJpaQuery
    //тут как критерий запроса используется поле сущности
    Optional<Company> findByCompanyName(String companyName);

    //Containing - like с двух сторон
    //IgnoreCase - upper функция в sql
    List<Company> findAllByCompanyNameContainingIgnoreCase(String fragment);
}
