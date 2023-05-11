package com.dmdev.spring.integration.service;

import com.dmdev.spring.database.entity.Role;
import com.dmdev.spring.dto.UserCreateEditDto;
import com.dmdev.spring.dto.UserReadDto;
import com.dmdev.spring.integration.IntegrationTestBase;
import com.dmdev.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor
public class UserServiceIT extends IntegrationTestBase {

    private static final Long USER_1 = 1L;
    private static final Integer COMPANY_1 = 1;

    private final UserService userService;

    @Test
    void findAll() {
        List<UserReadDto> result = userService.findAll();
        assertThat(result).hasSize(5);
    }

    @Test
    void findById() {
        Optional<UserReadDto> maybeUser = userService.findById(USER_1);
        assertTrue(maybeUser.isPresent());
        maybeUser.ifPresent(user -> assertEquals("Ivan", user.getFirstname()));
    }

    @Test
    void create() {
        UserCreateEditDto userDto = new UserCreateEditDto(
                "test@gmail.com",
                LocalDate.now(),
                "Test",
                "Test",
                Role.ADMIN,
                COMPANY_1
        );
        UserReadDto actual = userService.create(userDto);
        assertEquals(userDto.getUsername(), actual.getUsername());
        assertEquals(userDto.getFirstname(), actual.getFirstname());
        assertEquals(userDto.getBirthDate(), actual.getBirthDate());
        assertEquals(userDto.getLastname(), actual.getLastname());
        assertSame(userDto.getRole(), actual.getRole());
        assertEquals(userDto.getCompanyId(), actual.getCompany().getId());
    }

    @Test
    void update() {
        UserCreateEditDto userDto = new UserCreateEditDto(
                "test@gmail.com",
                LocalDate.now(),
                "Test",
                "Test",
                Role.ADMIN,
                COMPANY_1
        );
        Optional<UserReadDto> actual = userService.update(USER_1, userDto);
        assertTrue(actual.isPresent());
        actual.ifPresent(actualUser -> {
            assertEquals(userDto.getUsername(), actualUser.getUsername());
            assertEquals(userDto.getFirstname(), actualUser.getFirstname());
            assertEquals(userDto.getBirthDate(), actualUser.getBirthDate());
            assertEquals(userDto.getLastname(), actualUser.getLastname());
            assertSame(userDto.getRole(), actualUser.getRole());
            assertEquals(userDto.getCompanyId(), actualUser.getCompany().getId());
        });
    }

    @Test
    void delete() {
        assertFalse(userService.delete(-124L));
        assertTrue(userService.delete(USER_1));
    }
}
