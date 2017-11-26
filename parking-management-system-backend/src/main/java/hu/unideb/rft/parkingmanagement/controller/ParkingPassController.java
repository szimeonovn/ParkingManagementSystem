package hu.unideb.rft.parkingmanagement.controller;

import hu.unideb.rft.parkingmanagement.service.ParkingPassService;
import hu.unideb.rft.parkingmanagement.vo.ParkingPassVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/parkingPass")
@CrossOrigin(origins = "*")
public class ParkingPassController {

    @Autowired
    ParkingPassService parkingPassService;

    @PostMapping("/buy")
    public ResponseEntity<Object> buyParkingPass(@RequestBody ParkingPassVO parkingPassVO) {

        return null;
    }
}
