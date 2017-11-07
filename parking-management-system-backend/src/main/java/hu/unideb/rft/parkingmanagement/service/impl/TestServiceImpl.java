package hu.unideb.rft.parkingmanagement.service.impl;

import hu.unideb.rft.parkingmanagement.repository.TestRepository;
import hu.unideb.rft.parkingmanagement.service.TestService;
import hu.unideb.rft.parkingmanagement.vo.TestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TestServiceImpl implements TestService {

    @Autowired
    private TestService testService;

    @Autowired
    private TestRepository testRepository;

    @Override
    public TestVO save(TestVO testVO) {
        return testService.save(testVO);
    }
}
