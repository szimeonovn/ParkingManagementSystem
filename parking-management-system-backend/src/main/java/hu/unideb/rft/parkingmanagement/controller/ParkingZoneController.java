package hu.unideb.rft.parkingmanagement.controller;

import hu.unideb.rft.parkingmanagement.service.ParkingZoneService;
import hu.unideb.rft.parkingmanagement.vo.ParkingZoneVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/parkingZone")
public class ParkingZoneController {

    @Autowired
    private ParkingZoneService parkingZoneService;

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody ParkingZoneVO parkingZoneVO) {
        try {
            ParkingZoneVO savedParkingZone = parkingZoneService.save(parkingZoneVO);
            return new ResponseEntity<>(savedParkingZone, HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("There is a parking zone with " + parkingZoneVO.getZoneCode() + " zone code!", HttpStatus.OK);
        }
    }

    @GetMapping("/list")
    public List<ParkingZoneVO> list() {
        return parkingZoneService.list();
    }

}
