package hu.unideb.rft.parkingmanagement.entity;

import hu.unideb.parkingmanagement.enums.ParkingPassValidityTime;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "PARKING_PASS_TYPE")
@Getter
@Setter
public class ParkingPassType extends BaseEntity {

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
    private Integer price;

    @Column(name = "VALIDITY_TIME")
    @Enumerated(EnumType.STRING)
    private ParkingPassValidityTime validityTime;

}
