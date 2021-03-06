package hu.unideb.rft.parkingmanagement.vo;

import hu.unideb.rft.parkingmanagement.entity.Parking;
import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class StartStopParkingVO extends BaseVO {

    private static final String DATE_PATTERN = "yyyy.MM.dd HH:mm";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

    public StartStopParkingVO(Parking parking) {
        this.setId(parking.getId());
        this.setLicensePlateNumber(parking.getCar().getLicensePlateNumber());
        this.setParkingStart(parking.getParkingStart().format(StartStopParkingVO.DATE_FORMATTER));
        if (parking.getParkingEnd() != null) {
            this.setParkingEnd(parking.getParkingEnd().format(StartStopParkingVO.DATE_FORMATTER));
        }
        this.setParkingZoneCode(parking.getParkingZone().getZoneCode());
    }

    private String licensePlateNumber;
    private String parkingStart;
    private String parkingEnd;
    private String parkingZoneCode;

}
