package hu.unideb.rft.parkingmanagement.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParkingPassVO {

    private String licensePlateNumber;
    private Long parkingZoneId;
    private String validityTime;

}
