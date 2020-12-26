package ru.mirea.intro.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.mirea.intro.dao.RequestDAO;
import ru.mirea.intro.exception.NoSuchRequest;
import ru.mirea.intro.service.TestService;
import ru.mirea.intro.service.model.Book;
import ru.mirea.intro.service.model.Request;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
class TestServiceImplTest {
    @Autowired
    TestService testService;

    @DisplayName("Testing for NoSuchRequest")
    @Test
    void testServiceGetMethodException() {
        Assertions.assertThrows(NoSuchRequest.class, () -> testService.testServiceGetMethod(1234L)
        );
    }

//
    @DisplayName("Testing for normal post")
    @Test
    @Transactional
    void testServicePostMethod() {
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book(456L, "Толстой Тест 123", "Война и Мир Тест 123"));
        Request request = new Request(new Random().nextLong(), "Второй запрос из теста", bookList);
        Request insertedRequest = testService.testServicePostMethod(request);

        request.setId(insertedRequest.getId());
        request.getBookList().get(0).setId(insertedRequest.getBookList().get(0).getId());
        Assertions.assertEquals(request, insertedRequest);
    }

    @DisplayName("Testing for normal response")
    @Test
    @Transactional
    void testServiceGetMethod() throws NoSuchRequest {
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book(3L, "string", "string"));
        Request request = new Request(52L, "string", bookList);
        Assertions.assertEquals(request, testService.testServiceGetMethod(52L));
    }

    @DisplayName("Testing for normal put")
    @Test
    @Transactional
    void testServicePutMethod() throws NoSuchRequest {
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book(1L, "Тест 1 Не Обновленный", "Тест 1 Не Обновленный"));
        bookList.add(new Book(2L, "Тест 2 Не Обновленный", "Тест 2 Не Обновленный"));
        bookList.add(new Book(3L, "Тест 3 Не Обновленный", "Тест 3 Не Обновленный"));
        Request request = new Request(1L, "Не обновленный запрос из теста", bookList);
        testService.testServicePostMethod(request);

        List<Book> bookListUpdated = new ArrayList<>();
        bookList.add(new Book(1L, "Тест 1 Обновленный", "Тест 1 Обновленный"));
        bookList.add(new Book(2L, "Тест 2 Обновленный", "Тест 2 Обновленный"));
        bookList.add(new Book(3L, "Тест 3 Обновленный", "Тест 3 Обновленный"));
        Request requestUpdated = new Request(1L, "Обновленный запрос из теста", bookListUpdated);
        testService.testServicePutMethod(requestUpdated);
        Assertions.assertEquals(testService.testServiceGetMethod(1L), requestUpdated);
    }

    @DisplayName("Testing for NoSuchRequest put")
    @Test
    @Transactional
    void testServiceNoSuchRequestPutMethod() {
        List<Book> bookList = new ArrayList<>();
        Request request = new Request(1231241421251L, "Не обновленный запрос из теста", bookList);
        Assertions.assertThrows(NoSuchRequest.class, () -> testService.testServicePutMethod(request));
    }

    @DisplayName("Testing for normal delete")
    @Test
    @Transactional
    void testServiceDeleteMethod() throws NoSuchRequest {
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book(1L, "Тест 1 Не Обновленный", "Тест 1 Не Обновленный"));
        bookList.add(new Book(2L, "Тест 2 Не Обновленный", "Тест 2 Не Обновленный"));
        bookList.add(new Book(3L, "Тест 3 Не Обновленный", "Тест 3 Не Обновленный"));
        Request request = new Request(1L, "Не обновленный запрос из теста", bookList);
        testService.testServicePostMethod(request);

        String response = testService.testServiceDeleteMethod(1L);
        Assertions.assertEquals(response, "Successfully deleted row!");
        Assertions.assertThrows(NoSuchRequest.class, () -> testService.testServiceDeleteMethod(1L));
    }

    @DisplayName("Testing for NoSuchRequest delete")
    @Test
    @Transactional
    void testServiceNoSuchRequestDeleteMethod() {
        Assertions.assertThrows(NoSuchRequest.class, () -> testService.testServiceDeleteMethod(1241231241L));
    }



}