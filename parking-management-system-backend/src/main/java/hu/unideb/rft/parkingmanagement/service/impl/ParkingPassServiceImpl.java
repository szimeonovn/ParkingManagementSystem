package hu.unideb.rft.parkingmanagement.service.impl;

import hu.unideb.rft.parkingmanagement.entity.Car;
import hu.unideb.rft.parkingmanagement.entity.ParkingPass;
import hu.unideb.rft.parkingmanagement.entity.ParkingZone;
import hu.unideb.rft.parkingmanagement.repository.CarRepository;
import hu.unideb.rft.parkingmanagement.repository.ParkingPassRepository;
import hu.unideb.rft.parkingmanagement.repository.ParkingZoneRepository;
import hu.unideb.rft.parkingmanagement.service.ParkingPassService;
import hu.unideb.rft.parkingmanagement.vo.BuyParkingPassVO;
import hu.unideb.rft.parkingmanagement.vo.ParkingPassVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Transactional
public class ParkingPassServiceImpl implements ParkingPassService {

    private static final String DATE_PATTERN = "yyyy.MM.dd HH:mm";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

    @Autowired
    CarRepository carRepository;

    @Autowired
    ParkingZoneRepository parkingZoneRepository;

    @Autowired
    ParkingPassRepository parkingPassRepository;

    @Override
    public Object buyParkingPass(ParkingPassVO parkingPassVO) {
        parkingPassVO.setLicensePlateNumber(parkingPassVO.getLicensePlateNumber());
        if (parkingPassVO.getLicensePlateNumber().trim().isEmpty()) {
            return "Parking pass must not be empty";
        }

        if (!hasValidParkingPassInZone(parkingPassVO.getLicensePlateNumber(), parkingPassVO.getParkingZoneId())) {

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

            if (parkingPassVO.getValidityTime().equals("MONTHLY")) {
                parkingPass.setValidityEnd(LocalDateTime.now().plusMonths(1));
            }

            if (parkingPassVO.getValidityTime().equals("YEARLY")) {
                parkingPass.setValidityEnd(LocalDateTime.now().plusYears(1));
            }

            ParkingPass savedParkingPass = parkingPassRepository.save(parkingPass);

            BuyParkingPassVO buyParkingPassVO = new BuyParkingPassVO();

            if (savedParkingPass != null) {
                buyParkingPassVO.setLicensePlateNumber(savedParkingPass.getCar().getLicensePlateNumber());
                buyParkingPassVO.setParkingZoneCode(savedParkingPass.getParkingZone().getZoneCode());
                buyParkingPassVO.setValidityStart(savedParkingPass.getValidityStart().format(ParkingPassServiceImpl.DATE_FORMATTER));
                buyParkingPassVO.setValidityEnd(savedParkingPass.getValidityEnd().format(ParkingPassServiceImpl.DATE_FORMATTER));
            } else {
                return "Error in saving parking pass!";
            }

            return buyParkingPassVO;
        } else {
            return "This car already has a parking pass to this parking zone!";
        }

    }

    @Override
    public Boolean hasValidParkingPassInZone(String licensePlateNumber, Long parkingZoneId) {
        licensePlateNumber = licensePlateNumber.toUpperCase();

        Boolean ret = false;

        Car car = carRepository.findByLicensePlateNumber(licensePlateNumber);

        if (car != null) {
            ParkingZone parkingZone = parkingZoneRepository.findOne(parkingZoneId);

            if (parkingZone != null) {
                ParkingPass byCarAndParkingZone = parkingPassRepository.findByCarAndParkingZone(car, parkingZone);
                if (byCarAndParkingZone != null) {
                    ret = true;
                }
            }

        }

        return ret;
    }
}
