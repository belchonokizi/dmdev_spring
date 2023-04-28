package com.dmdev.spring.database.repository;

import com.dmdev.spring.database.entity.Role;
import com.dmdev.spring.database.entity.User;
import com.dmdev.spring.dto.PersonalInfo;
import com.dmdev.spring.dto.UserFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
public class FilterUserRepositoryImpl implements FilterUserRepository {

    public static final String FIND_BY_COMPANY_AND_ROLE = """
            SELECT 
            firstname, 
            lastname,
            birth_date
            FROM users
            WHERE company_id = ?
            AND role = ?
            """;

    private final EntityManager entityManager;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<User> findAllByFilter(UserFilter userFilter) {
        //динамический where
//        Predicate predicate = QPredicates.builder()
//                .add(userFilter.firstname(), user.firstname::containsIgnoreCase)
//                .add(userFilter.lastname(), user.lastname::containsIgnoreCase)
//                .add(userFilter.birthDate(), user.birthDate::before)
//                .build();
//
//        return new JPAQuery<User>(entityManager)
//                .select(user)
//                .from(user)
//                .where(predicate)
//                .fetch();
        return null;
    }

    @Override
    public List<PersonalInfo> findAllByCompanyIdAndRole(Integer companyId, Role role) {
        return jdbcTemplate.query(FIND_BY_COMPANY_AND_ROLE, (rs, rowNum) -> new PersonalInfo(
                rs.getString("firstname"),
                rs.getString("lastname"),
                rs.getDate("birth_date").toLocalDate()
        ), companyId, role.name());
    }
}
