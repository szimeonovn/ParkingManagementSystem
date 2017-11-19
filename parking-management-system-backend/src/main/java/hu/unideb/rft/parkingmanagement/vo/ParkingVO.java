package hu.unideb.rft.parkingmanagement.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParkingVO extends BaseVO {

    private String licensePlateNumber;
    private Long parkingZoneId;

}
