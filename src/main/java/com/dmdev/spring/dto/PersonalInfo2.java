package com.dmdev.spring.dto;

import org.springframework.beans.factory.annotation.Value;

public interface PersonalInfo2 {

    String getFirstname();
    String getLastname();
    String getBirthDate();

    //использование комбинации полей
    @Value("#{target.firstname + ' ' + target.lastname}")
    String getFullName();
}
