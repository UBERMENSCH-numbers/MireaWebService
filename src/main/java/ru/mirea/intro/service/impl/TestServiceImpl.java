package ru.mirea.intro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    @Override
    public Request testServiceGetMethod(Long id) throws NoSuchRequest {
        Optional<RequestDAO> requestDAO = requestRepository.findById(id);
        if (requestDAO.isPresent()) {
            requestDAO.get().getBookDaoList().sort(Comparator.comparingLong(BookDao::getId).reversed());
            return RequestMapper.REQUEST_MAPPER.requestDAOToRequest(requestDAO.get());
        }
        throw new NoSuchRequest();
    }

    @Override
    public Request testServicePostMethod(Request request) {
        RequestDAO requestDAO = RequestMapper.REQUEST_MAPPER.requestToRequestDAO(request);
        requestDAO.getBookDaoList().forEach(bookDao -> bookDao.setRequestDao(requestDAO));
        RequestDAO insertedRequest = requestRepository.save(requestDAO);
        insertedRequest.getBookDaoList().sort(Comparator.comparingLong(BookDao::getId).reversed());
        return RequestMapper.REQUEST_MAPPER.requestDAOToRequest(insertedRequest);
    }


    @Override
    public Request testServicePutMethod(Request request) throws NoSuchRequest {
        RequestDAO requestDAO = RequestMapper.REQUEST_MAPPER.requestToRequestDAO(request);
        Optional<RequestDAO> optionalRequestDAO = requestRepository.findById(requestDAO.getId());
        if (!optionalRequestDAO.isPresent()) throw new NoSuchRequest();
        requestDAO.getBookDaoList().forEach(bookDao -> bookDao.setRequestDao(requestDAO));
        RequestDAO updatedRequest = requestRepository.save(requestDAO);
        updatedRequest.getBookDaoList().sort(Comparator.comparingLong(BookDao::getId).reversed());
        return RequestMapper.REQUEST_MAPPER.requestDAOToRequest(updatedRequest);
    }

    @Override
    public String testServiceDeleteMethod(Long id) throws NoSuchRequest {
        Optional<RequestDAO> optionalRequestDAO = requestRepository.findById(id);
        if (!optionalRequestDAO.isPresent()) throw new NoSuchRequest();
        requestRepository.delete(optionalRequestDAO.get());
        return "Successfully deleted row!";
    }


}