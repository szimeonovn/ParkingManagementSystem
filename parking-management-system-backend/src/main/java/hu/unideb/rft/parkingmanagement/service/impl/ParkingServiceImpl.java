package hu.unideb.rft.parkingmanagement.service.impl;

import hu.unideb.rft.parkingmanagement.entity.Car;
import hu.unideb.rft.parkingmanagement.entity.Parking;
import hu.unideb.rft.parkingmanagement.entity.ParkingZone;
import hu.unideb.rft.parkingmanagement.repository.CarRepository;
import hu.unideb.rft.parkingmanagement.repository.ParkingRepository;
import hu.unideb.rft.parkingmanagement.repository.ParkingZoneRepository;
import hu.unideb.rft.parkingmanagement.service.CarService;
import hu.unideb.rft.parkingmanagement.service.ParkingService;
import hu.unideb.rft.parkingmanagement.vo.CheckParkingVO;
import hu.unideb.rft.parkingmanagement.vo.ParkingVO;
import hu.unideb.rft.parkingmanagement.vo.StartStopParkingVO;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
@Transactional
public class ParkingServiceImpl implements ParkingService {

    @Autowired
    CarService carService;

    @Autowired
    CarRepository carRepository;

    @Autowired
    ParkingRepository parkingRepository;

    @Autowired
    ParkingZoneRepository parkingZoneRepository;

    @Autowired
    Mapper mapper;

    @Override
    public Object startParking(ParkingVO parkingVO) {
        Car parkingCarCandidate = carService.findByLicensePlateNumber(parkingVO.getLicensePlateNumber());

        if (parkingCarCandidate != null) {
            Parking onGoingParking = parkingRepository.findByCarAndParkingEndIsNull(parkingCarCandidate);
            if (onGoingParking != null) {
                return "This car is already parking in " + onGoingParking.getParkingZone().getZoneCode() + " zone!";
            }
        }

        if (parkingCarCandidate == null) {
            Car carToSave = new Car();
            carToSave.setLicensePlateNumber(parkingVO.getLicensePlateNumber().toUpperCase());
            parkingCarCandidate = carRepository.save(carToSave);
        }

        ParkingZone parkingZone = parkingZoneRepository.findOne(parkingVO.getParkingZoneId());

        if (parkingZone == null) {
            return "There is no parking zone with the gived id " + parkingVO.getParkingZoneId() + "!";
        }

        Parking parking = new Parking();
        parking.setCar(parkingCarCandidate);
        parking.setParkingZone(parkingZone);
        parking.setParkingStart(LocalDateTime.now());
        Parking savedParking = parkingRepository.save(parking);

        return new StartStopParkingVO(savedParking);
    }

    @Override
    public Object checkParking(String licensePlateNumber) {
        Car car = carRepository.findByLicensePlateNumber(licensePlateNumber.toUpperCase());
        if (car == null) {
            return "There is no parking car with " + licensePlateNumber + " license plate number!";
        }
        Parking parking = parkingRepository.findByCarAndParkingEndIsNull(car);

        if (parking == null) {
            return "There is no parking car with " + car.getLicensePlateNumber() + " license plate number!";
        }

        double elapsedSeconds = ChronoUnit.SECONDS.between(parking.getParkingStart(), LocalDateTime.now());
        if (elapsedSeconds < 900) {
            elapsedSeconds = 900;
        }
        double parkingCostToPay = (elapsedSeconds / 3600) * parking.getParkingZone().getParkingCostPerHour();
        double elapsedMinutes = ChronoUnit.MINUTES.between(parking.getParkingStart(), LocalDateTime.now());

        CheckParkingVO checkParkingVO = new CheckParkingVO();
        checkParkingVO.setParkingTime(elapsedMinutes);
        checkParkingVO.setParkingCostToPay(Math.round(parkingCostToPay));

        return checkParkingVO;
    }

    @Override
    public Object stopParking(String licensePlateNumber) {
        Car car = carRepository.findByLicensePlateNumber(licensePlateNumber.toUpperCase());

        Parking parking = parkingRepository.findByCarAndParkingEndIsNull(car);

        if (parking == null) {
            return "There is no parking car with " + car.getLicensePlateNumber() + " license plate number!";
        }

        parking.setParkingEnd(LocalDateTime.now());

        Parking savedParking = parkingRepository.save(parking);

        return new StartStopParkingVO(savedParking);
    }
}