package hu.unideb.rft.parkingmanagement.vo;

import hu.unideb.rft.parkingmanagement.entity.ParkingPass;
import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class BuyParkingPassVO {

    private static final String DATE_PATTERN = "yyyy.MM.dd HH:mm";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

    public static BuyParkingPassVO createFromEntity(ParkingPass parkingPass) {
        BuyParkingPassVO vo = new BuyParkingPassVO();
        vo.setLicensePlateNumber(parkingPass.getCar().getLicensePlateNumber());
        vo.setParkingZoneCode(parkingPass.getParkingZone().getZoneCode());
        vo.setValidityStart(parkingPass.getValidityStart().format(BuyParkingPassVO.DATE_FORMATTER));
        vo.setValidityEnd(parkingPass.getValidityEnd().format(BuyParkingPassVO.DATE_FORMATTER));
        return vo;
    }

    private String licensePlateNumber;
    private String parkingZoneCode;
    private String validityStart;
    private String validityEnd;



}
