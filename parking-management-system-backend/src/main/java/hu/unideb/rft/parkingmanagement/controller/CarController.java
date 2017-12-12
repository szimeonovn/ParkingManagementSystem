package hu.unideb.rft.parkingmanagement.controller;

import hu.unideb.rft.parkingmanagement.entity.Car;
import hu.unideb.rft.parkingmanagement.repository.CarRepository;
import hu.unideb.rft.parkingmanagement.vo.CarVO;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/car")
public class CarController {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private Mapper mapper;

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public ResponseEntity<Object> list() {

        List<Car> carList = carRepository.findAll();
        List<CarVO> carVOList = carList.stream().map(car -> mapper.map(car, CarVO.class)).collect(Collectors.toList());

        return new ResponseEntity<>(carVOList, HttpStatus.OK);
    }

}
