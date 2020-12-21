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

import java.util.Optional;

@Service

public class TestServiceImpl implements TestService {
    @Autowired
    RequestRepository requestRepository;

    @Override
    public Request testServiceGetMethod(Long id) throws NoSuchRequest {
        Optional<RequestDAO> requestDAO = requestRepository.findById(id);
        if (requestDAO.isPresent()) {
            return RequestMapper.REQUEST_MAPPER.requestDAOToRequest(requestDAO.get());
        }
        throw new NoSuchRequest();
    }

    @Override
    public String testServicePostMethod(Request request) {
        RequestDAO requestDAO = RequestMapper.REQUEST_MAPPER.requestToRequestDAO(request);
        requestDAO.getBookDaoList().forEach(bookDao -> bookDao.setRequestDao(requestDAO));
        requestRepository.save(requestDAO);
        return "Successfully inserted row!";
    }


    @Override
    public String testServicePutMethod(Request request) throws NoSuchRequest {
        RequestDAO requestDAO = RequestMapper.REQUEST_MAPPER.requestToRequestDAO(request);
        Optional<RequestDAO> optionalRequestDAO = requestRepository.findById(requestDAO.getId());
        if (!optionalRequestDAO.isPresent()) throw new NoSuchRequest();
        requestDAO.getBookDaoList().forEach(bookDao -> bookDao.setRequestDao(optionalRequestDAO.get()));
        optionalRequestDAO.get().setRequestValue(requestDAO.getRequestValue());
        optionalRequestDAO.get().updateBookDaoList(requestDAO.getBookDaoList());
        requestRepository.save(optionalRequestDAO.get());
        return "Successfully updated row!";
    }

    @Override
    public String testServiceDeleteMethod(Long id) throws NoSuchRequest {
        Optional<RequestDAO> optionalRequestDAO = requestRepository.findById(id);
        if (!optionalRequestDAO.isPresent()) throw new NoSuchRequest();
        requestRepository.delete(optionalRequestDAO.get());
        return "Successfully deleted row!";
    }


}