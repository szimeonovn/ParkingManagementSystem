package hu.unideb.rft.parkingmanagement.controller;

import hu.unideb.rft.parkingmanagement.entity.ParkingPass;
import hu.unideb.rft.parkingmanagement.repository.ParkingPassRepository;
import hu.unideb.rft.parkingmanagement.service.ParkingPassService;
import hu.unideb.rft.parkingmanagement.vo.BuyParkingPassVO;
import hu.unideb.rft.parkingmanagement.vo.ErrorVO;
import hu.unideb.rft.parkingmanagement.vo.ParkingPassVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/parkingPass")
public class ParkingPassController {

    @Autowired
    private ParkingPassService parkingPassService;

    @Autowired
    private ParkingPassRepository parkingPassRepository;

    @PostMapping("/buy")
    public ResponseEntity<Object> buyParkingPass(@RequestBody ParkingPassVO parkingPassVO) {

        if (parkingPassVO.getValidityTime() == null) {
            return new ResponseEntity<>(new ErrorVO("Validity time must not be null!"), HttpStatus.OK);
        }

        if (parkingPassVO.getLicensePlateNumber() == null) {
            return new ResponseEntity<>(new ErrorVO("License plate number must not be null!"), HttpStatus.OK);
        }

        if (parkingPassVO.getParkingZoneId() == null) {
            return new ResponseEntity<>(new ErrorVO("Parking zone Id must not be null!"), HttpStatus.OK);
        }

        return new ResponseEntity<>(parkingPassService.buyParkingPass(parkingPassVO), HttpStatus.OK);
    }

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public ResponseEntity<Object> list() {

        List<ParkingPass> parkingPassList = parkingPassRepository.findAll();
        List<BuyParkingPassVO> passVOList = parkingPassList.stream().map(pass -> BuyParkingPassVO.createFromEntity(pass)).collect(Collectors.toList());

        return new ResponseEntity<>(passVOList, HttpStatus.OK);
    }
}
