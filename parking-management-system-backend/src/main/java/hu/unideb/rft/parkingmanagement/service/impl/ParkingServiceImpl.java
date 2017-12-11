package hu.unideb.rft.parkingmanagement.service.impl;

import hu.unideb.rft.parkingmanagement.entity.Car;
import hu.unideb.rft.parkingmanagement.entity.Parking;
import hu.unideb.rft.parkingmanagement.entity.ParkingZone;
import hu.unideb.rft.parkingmanagement.repository.CarRepository;
import hu.unideb.rft.parkingmanagement.repository.ParkingPassRepository;
import hu.unideb.rft.parkingmanagement.repository.ParkingRepository;
import hu.unideb.rft.parkingmanagement.repository.ParkingZoneRepository;
import hu.unideb.rft.parkingmanagement.service.CarService;
import hu.unideb.rft.parkingmanagement.service.ParkingService;
import hu.unideb.rft.parkingmanagement.vo.CheckParkingVO;
import hu.unideb.rft.parkingmanagement.vo.ErrorVO;
import hu.unideb.rft.parkingmanagement.vo.ParkingVO;
import hu.unideb.rft.parkingmanagement.vo.StartStopParkingVO;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ParkingServiceImpl implements ParkingService {

    @Autowired
    private CarService carService;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ParkingRepository parkingRepository;

    @Autowired
    private ParkingZoneRepository parkingZoneRepository;

    @Autowired
    private ParkingPassRepository parkingPassRepository;

    @Autowired
    private Mapper mapper;

    @Override
    public Object startParking(ParkingVO parkingVO) {
        parkingVO.setLicensePlateNumber(parkingVO.getLicensePlateNumber().toUpperCase());
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
    public Object checkParking(ParkingVO parkingVO) {
        parkingVO.setLicensePlateNumber(parkingVO.getLicensePlateNumber().toUpperCase());
        Car car = carRepository.findByLicensePlateNumber(parkingVO.getLicensePlateNumber());

        if (car == null) {
            return new ErrorVO("There is no parking car with " + parkingVO.getLicensePlateNumber() + " license plate number!");
        }

        ParkingZone zone = parkingZoneRepository.findOne(parkingVO.getParkingZoneId());

        if (zone == null) {
            return "There is no parking zone with the specified id in the database!";
        }

        Parking parking = parkingRepository.findByCarAndParkingZoneAndParkingEndIsNull(car, zone);

        if (parking == null) {
            return new ErrorVO("There is no parking car with " + car.getLicensePlateNumber() + " license plate number in " + zone.getZoneCode() + " zone!");
        }

        double elapsedSeconds = ChronoUnit.SECONDS.between(parking.getParkingStart(), LocalDateTime.now());
        if (elapsedSeconds < 900) {
            elapsedSeconds = 900;
        }

        double parkingCostToPay;

        if (parkingPassRepository.findByCarAndParkingZone(car, parkingZoneRepository.findOne(parkingVO.getParkingZoneId())) != null) {
            parkingCostToPay = 0;
        } else {
            parkingCostToPay = (elapsedSeconds / 3600) * parking.getParkingZone().getParkingCostPerHour();
        }

        double elapsedMinutes = ChronoUnit.MINUTES.between(parking.getParkingStart(), LocalDateTime.now());

        CheckParkingVO checkParkingVO = new CheckParkingVO();
        checkParkingVO.setParkingTime(elapsedMinutes);
        checkParkingVO.setParkingCostToPay(Math.round(parkingCostToPay));

        return checkParkingVO;
    }

    @Override
    public Object stopParking(String licensePlateNumber) {
        licensePlateNumber = licensePlateNumber.toUpperCase();
        Car car = carRepository.findByLicensePlateNumber(licensePlateNumber);

        Parking parking = parkingRepository.findByCarAndParkingEndIsNull(car);

        if (parking == null) {
            return "There is no parking car with " + car.getLicensePlateNumber() + " license plate number!";
        }

        parking.setParkingEnd(LocalDateTime.now());

        Parking savedParking = parkingRepository.save(parking);

        return new StartStopParkingVO(savedParking);
    }

    @Override
    public Object findAllParkingCars() {
        List<Parking> onGoingParkings = parkingRepository.findAllByParkingEndIsNull();
        return onGoingParkings.stream().map(StartStopParkingVO::new).collect(Collectors.toList());
    }
}