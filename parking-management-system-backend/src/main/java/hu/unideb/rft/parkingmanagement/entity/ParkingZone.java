package hu.unideb.rft.parkingmanagement.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "PARKING_ZONE")
@Getter
@Setter
public class ParkingZone extends BaseEntity {

    @Column(name = "ZONE_CODE", unique = true, nullable = false)
    private String zoneCode;

    @Column(name = "PARKING_COST_PER_HOUR")
    private Integer parkingCostPerHour;

}
