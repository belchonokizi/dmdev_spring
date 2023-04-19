package com.dmdev.spring.database.repository;

import com.dmdev.spring.database.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

    @Query("select c from Company c " +
            "join fetch c.locales cl " +
            "where c.companyName = :companyName")
    Optional<Company> findByCompanyName(String companyName);

    //Containing - like с двух сторон
    //IgnoreCase - upper функция в sql
    List<Company> findAllByCompanyNameContainingIgnoreCase(String fragment);
}
