package hu.unideb.rft.parkingmanagement.controller;

import hu.unideb.rft.parkingmanagement.entity.Test;
import hu.unideb.rft.parkingmanagement.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/test")
public class TestController {

    @Autowired
    private TestRepository testRepository;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/save")
    public Test create(@RequestBody Test test) {
        return testRepository.save(test);
    }

}
