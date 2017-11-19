package hu.unideb.rft.parkingmanagement.service;

import hu.unideb.rft.parkingmanagement.vo.ParkingVO;

public interface ParkingService {

    Object startParking(ParkingVO parkingVO);

    Object checkParking(String licensePlateNumber);

    Object stopParking(String licensePlateNumber);
}
