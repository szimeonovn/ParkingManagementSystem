package hu.unideb.rft.parkingmanagement.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuyParkingPassVO {

    private String licensePlateNumber;
    private String parkingZoneCode;
    private String validityStart;
    private String validityEnd;

}
