package ru.mirea.intro.service;


import ru.mirea.intro.service.model.Request;

public interface TestService {
    Request testServiceGetMethod(Long id);

    String testServicePostMethod(Request request);
}
