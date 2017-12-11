package hu.unideb.rft.parkingmanagement.controller;

import hu.unideb.rft.parkingmanagement.service.ParkingService;
import hu.unideb.rft.parkingmanagement.vo.ErrorVO;
import hu.unideb.rft.parkingmanagement.vo.ParkingVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/parking")
public class ParkingController {

    @Autowired
    ParkingService parkingService;

    @PostMapping("/startParking")
    public ResponseEntity<Object> startParking(@RequestBody ParkingVO parkingVO) {
        if (parkingVO.getLicensePlateNumber() == null) {
            return new ResponseEntity<>("licensePlateNumber must not be null!", HttpStatus.OK);
        }

        if (parkingVO.getParkingZoneId() == null) {
            return new ResponseEntity<>("parkingZoneId must not be null!", HttpStatus.OK);
        }

        return new ResponseEntity<>(parkingService.startParking(parkingVO), HttpStatus.OK);
    }

    @PostMapping("/checkParking")
    public ResponseEntity<Object> checkParking(@RequestBody ParkingVO parkingVO) {
        if (parkingVO.getLicensePlateNumber() == null) {
            return new ResponseEntity<>(new ErrorVO("licensePlateNumber must not be null!"), HttpStatus.OK);
        }

        if (parkingVO.getParkingZoneId() == null) {
            return new ResponseEntity<>(new ErrorVO("parkingZoneId must not be null!"), HttpStatus.OK);
        }

        return new ResponseEntity<>(parkingService.checkParking(parkingVO), HttpStatus.OK);
    }

    @PostMapping("/stopParking/{licensePlateNumber}")
    public ResponseEntity<Object> stopParking(@PathVariable String licensePlateNumber) {
        return new ResponseEntity<>(parkingService.stopParking(licensePlateNumber), HttpStatus.OK);
    }

    @GetMapping("/listOnGoing")
    // @PreAuthorize("hasAuthority('ADMIN_USER')")
    public ResponseEntity<Object> listOnGoingParkings() {
        return new ResponseEntity<>(parkingService.findAllParkingCars(), HttpStatus.OK);
    }
}
