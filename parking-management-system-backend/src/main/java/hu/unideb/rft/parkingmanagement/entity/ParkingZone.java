package hu.unideb.rft.parkingmanagement.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PARKING_ZONE")
@Getter
@Setter
public class ParkingZone implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Long id;

    @Column(name = "ZONE_CODE", unique = true, nullable = false)
    private String zoneCode;

    @Column(name = "PARKING_COST_PER_HOUR")
    private Integer parkingCostPerHour;

}
