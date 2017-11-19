package hu.unideb.rft.parkingmanagement.controller;

import hu.unideb.rft.parkingmanagement.service.ParkingService;
import hu.unideb.rft.parkingmanagement.vo.ParkingVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/parking")
@CrossOrigin(origins = "http://localhost:4200")
public class ParkingController {

    @Autowired
    ParkingService parkingService;

    @PostMapping("/startParking")
    public ResponseEntity<Object> startParking(@RequestBody ParkingVO parkingVO) {
        if (parkingVO.getLicensePlateNumber() == null) {
            return new ResponseEntity<>("licensePlateNumber most not be null!", HttpStatus.OK);
        }

        if (parkingVO.getParkingZoneId() == null) {
            return new ResponseEntity<>("parkingZoneId must not be null!", HttpStatus.OK);
        }

        return new ResponseEntity<>(parkingService.startParking(parkingVO), HttpStatus.OK);
    }

    @GetMapping("/checkParking/{licensePlateNumber}")
    public ResponseEntity<Object> checkParking(@PathVariable String licensePlateNumber) {

        if (licensePlateNumber == null) {
            return new ResponseEntity<>("licensePlateNumber must not be null!", HttpStatus.OK);
        }

        return new ResponseEntity<>(parkingService.checkParking(licensePlateNumber), HttpStatus.OK);
    }

    @PostMapping("/stopParking/{licensePlateNumber}")
    public ResponseEntity<Object> stopParking(@PathVariable String licensePlateNumber) {
        return new ResponseEntity<>(parkingService.stopParking(licensePlateNumber), HttpStatus.OK);
    }
}
