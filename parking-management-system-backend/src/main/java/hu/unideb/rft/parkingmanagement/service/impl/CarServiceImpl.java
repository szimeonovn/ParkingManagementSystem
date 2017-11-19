package hu.unideb.rft.parkingmanagement.service.impl;

import hu.unideb.rft.parkingmanagement.entity.Car;
import hu.unideb.rft.parkingmanagement.repository.CarRepository;
import hu.unideb.rft.parkingmanagement.service.CarService;
import hu.unideb.rft.parkingmanagement.vo.CarVO;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private Mapper mapper;

    @Override
    public Car findByLicensePlateNumber(String licensePlateNumber) {
        return carRepository.findByLicensePlateNumber(licensePlateNumber);
    }

    @Override
    public CarVO save(CarVO carVO) {
        Car carToSave = mapper.map(carVO, Car.class);
        Car savedCar = carRepository.save(carToSave);
        return mapper.map(savedCar, CarVO.class);
    }
}
