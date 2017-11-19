package hu.unideb.rft.parkingmanagement.service;

import hu.unideb.rft.parkingmanagement.vo.ParkingZoneVO;

import java.util.List;

public interface ParkingZoneService {

    ParkingZoneVO save(ParkingZoneVO parkingZoneVO);

    List<ParkingZoneVO> list();
}
