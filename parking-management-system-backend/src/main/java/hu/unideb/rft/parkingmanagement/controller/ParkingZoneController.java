package hu.unideb.rft.parkingmanagement.controller;

import hu.unideb.rft.parkingmanagement.service.ParkingZoneService;
import hu.unideb.rft.parkingmanagement.vo.ParkingZoneVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/parkingZone")
@CrossOrigin(origins = "http://localhost:4200")
public class ParkingZoneController {

    @Autowired
    private ParkingZoneService parkingZoneService;

    @PostMapping("/save")
    public ParkingZoneVO save(@RequestBody ParkingZoneVO parkingZoneVO) {
        return parkingZoneService.save(parkingZoneVO);
    }


}
