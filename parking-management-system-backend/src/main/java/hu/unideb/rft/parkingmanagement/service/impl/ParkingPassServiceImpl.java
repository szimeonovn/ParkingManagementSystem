package hu.unideb.rft.parkingmanagement.service.impl;

import hu.unideb.rft.parkingmanagement.entity.Car;
import hu.unideb.rft.parkingmanagement.entity.ParkingPass;
import hu.unideb.rft.parkingmanagement.entity.ParkingZone;
import hu.unideb.rft.parkingmanagement.repository.CarRepository;
import hu.unideb.rft.parkingmanagement.repository.ParkingZoneRepository;
import hu.unideb.rft.parkingmanagement.service.ParkingPassService;
import hu.unideb.rft.parkingmanagement.vo.ParkingPassVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@Transactional
public class ParkingPassServiceImpl implements ParkingPassService {

    @Autowired
    CarRepository carRepository;

    @Autowired
    ParkingZoneRepository parkingZoneRepository;

    @Override
    public Object buyParkingPass(ParkingPassVO parkingPassVO) {
        ParkingPass parkingPass = new ParkingPass();

        parkingPassVO.setLicensePlateNumber(parkingPassVO.getLicensePlateNumber().toUpperCase());

        Car car = carRepository.findByLicensePlateNumber(parkingPassVO.getLicensePlateNumber());

        if (car == null) {
            Car carToSave = new Car();
            carToSave.setLicensePlateNumber(parkingPassVO.getLicensePlateNumber());
            car = carRepository.save(carToSave);
        }

        parkingPass.setCar(car);

        ParkingZone parkingZone = parkingZoneRepository.findOne(parkingPassVO.getParkingZoneId());

        parkingPass.setParkingZone(parkingZone);
        parkingPass.setValidityStart(LocalDateTime.now());

        if (parkingPassVO.getValidityTime() == "MONTHLY") {
            parkingPass.setValidityEnd(LocalDateTime.now().plusMonths(1));
        }

        if (parkingPassVO.getValidityTime() == "YEARLY") {
            parkingPass.setValidityEnd(LocalDateTime.now().plusYears(1));
        }

        return null;
    }
}
