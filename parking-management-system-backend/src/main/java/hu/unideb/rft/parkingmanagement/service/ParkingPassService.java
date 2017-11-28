package hu.unideb.rft.parkingmanagement.service;

import hu.unideb.rft.parkingmanagement.vo.ParkingPassVO;

public interface ParkingPassService {

    Object buyParkingPass(ParkingPassVO parkingPassVO);

    Object hasValidParkingPassInZone(String licensePlateNumber, Long parkingZoneId);
}
