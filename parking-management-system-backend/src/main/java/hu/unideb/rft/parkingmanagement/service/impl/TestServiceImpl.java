package hu.unideb.rft.parkingmanagement.service.impl;

import hu.unideb.rft.parkingmanagement.entity.Test;
import hu.unideb.rft.parkingmanagement.repository.TestRepository;
import hu.unideb.rft.parkingmanagement.service.TestService;
import hu.unideb.rft.parkingmanagement.vo.TestVO;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TestServiceImpl implements TestService {

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private Mapper mapper;

    @Override
    public TestVO save(TestVO testVO) {

        Test testToSave = mapper.map(testVO, Test.class);
        Test saved = testRepository.save(testToSave);
        return mapper.map(saved, TestVO.class);

    }
}
