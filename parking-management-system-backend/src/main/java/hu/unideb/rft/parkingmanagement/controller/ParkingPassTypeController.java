package hu.unideb.rft.parkingmanagement.controller;

import hu.unideb.rft.parkingmanagement.service.ParkingPassTypeService;
import hu.unideb.rft.parkingmanagement.vo.ParkingPassTypeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/parkingPassType")
@CrossOrigin(origins = "*")
public class ParkingPassTypeController {

    @Autowired
    ParkingPassTypeService parkingPassTypeService;

    @GetMapping("/list")
    public List<ParkingPassTypeVO> list() {
        return parkingPassTypeService.list();
    }

}


