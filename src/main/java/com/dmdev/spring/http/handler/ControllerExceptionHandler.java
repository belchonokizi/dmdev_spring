package com.dmdev.spring.http.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
//для того, чтобы handler был глобальным для всех контроллеров
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleExceptions(Exception exception) {
        log.error("Failed to return");
        return "error/error500";
    }
}
