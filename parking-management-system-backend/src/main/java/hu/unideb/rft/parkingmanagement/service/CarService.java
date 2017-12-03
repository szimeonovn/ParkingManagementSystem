package hu.unideb.rft.parkingmanagement.service;

import hu.unideb.rft.parkingmanagement.entity.Car;
import hu.unideb.rft.parkingmanagement.vo.CarVO;

public interface CarService {

    CarVO save(CarVO carVO);

    Car findByLicensePlateNumber(String licensePlateNumber);
}
