package ru.mirea.intro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Service;
import ru.mirea.intro.MyHealthIndicator;
import ru.mirea.intro.dao.BookDao;
import ru.mirea.intro.dao.RequestDAO;
import ru.mirea.intro.dao.repository.RequestRepository;
import ru.mirea.intro.exception.NoSuchRequest;
import ru.mirea.intro.mapper.RequestMapper;
import ru.mirea.intro.service.TestService;
import ru.mirea.intro.service.model.Request;

import java.util.*;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    RequestRepository requestRepository;
    @Autowired
    MyHealthIndicator healthIndicator;

    @Override
    public Request testServiceGetMethod(Long id) throws NoSuchRequest {
        Optional<RequestDAO> requestDAO = requestRepository.findById(id);
        if (!requestDAO.isPresent()) throw new NoSuchRequest();

        List<BookDao> books = requestDAO.get().getBookDaoList();
        Collections.reverse(books);
        requestDAO.get().setBookDaoList(books);
        return RequestMapper.REQUEST_MAPPER.requestDAOToRequest(requestDAO.get());

    }

    @Override
    public Request testServicePostMethod(Request request) {
        RequestDAO requestDAO = RequestMapper.REQUEST_MAPPER.requestToRequestDAO(request);
        requestDAO.getBookDaoList().forEach(bookDao -> bookDao.setRequestDao(requestDAO));
        RequestDAO insertedRequest = requestRepository.save(requestDAO);

        List<BookDao> books = insertedRequest.getBookDaoList();
        Collections.reverse(books);
        insertedRequest.setBookDaoList(books);
        return RequestMapper.REQUEST_MAPPER.requestDAOToRequest(insertedRequest);
    }


    @Override
    public Request testServicePutMethod(Request request) throws NoSuchRequest {
        RequestDAO requestDAO = RequestMapper.REQUEST_MAPPER.requestToRequestDAO(request);
        Optional<RequestDAO> optionalRequestDAO = requestRepository.findById(requestDAO.getId());
        if (!optionalRequestDAO.isPresent()) throw new NoSuchRequest();
        requestDAO.getBookDaoList().forEach(bookDao -> bookDao.setRequestDao(requestDAO));

        List<BookDao> books = requestRepository.findById(requestDAO.getId()).get().getBookDaoList();
        Collections.reverse(books);
        requestDAO.setBookDaoList(books);
        return RequestMapper.REQUEST_MAPPER.requestDAOToRequest(requestDAO);
    }

    @Override
    public String testServiceDeleteMethod(Long id) throws NoSuchRequest {
        Optional<RequestDAO> optionalRequestDAO = requestRepository.findById(id);
        if (!optionalRequestDAO.isPresent()) throw new NoSuchRequest();
        requestRepository.delete(optionalRequestDAO.get());
        return "Successfully deleted row!";
    }

    @Override
    public Health testServiceHealthMethod() {
        return healthIndicator.health();
    }


}