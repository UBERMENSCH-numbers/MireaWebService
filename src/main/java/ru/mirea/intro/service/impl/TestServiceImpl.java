package ru.mirea.intro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mirea.intro.dao.RequestDAO;
import ru.mirea.intro.dao.repository.RequestRepository;
import ru.mirea.intro.mapper.RequestMapper;
import ru.mirea.intro.service.TestService;
import ru.mirea.intro.service.model.Request;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    RequestRepository requestRepository;

    @Override
    public Request testServiceGetMethod(Long id) {
        return new Request("New custom value");
    }

    @Override
    public String testServicePostMethod(Request request) {
        RequestDAO requestDAO = RequestMapper.REQUEST_MAPPER.requestToRequestDAO(request);
        requestRepository.save(requestDAO);
        return "Successfully inserted row!";
    }
}
