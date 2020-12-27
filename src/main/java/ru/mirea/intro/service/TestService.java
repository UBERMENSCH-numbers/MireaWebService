package ru.mirea.intro.service;


import org.springframework.boot.actuate.health.Health;
import ru.mirea.intro.exception.NoSuchRequest;
import ru.mirea.intro.service.model.Request;

public interface TestService {
    Request testServiceGetMethod(Long id) throws NoSuchRequest;

    Request testServicePostMethod(Request request);

    Request testServicePutMethod(Request request) throws NoSuchRequest;

    String testServiceDeleteMethod(Long id) throws NoSuchRequest;

    Health testServiceHealthMethod();
}
