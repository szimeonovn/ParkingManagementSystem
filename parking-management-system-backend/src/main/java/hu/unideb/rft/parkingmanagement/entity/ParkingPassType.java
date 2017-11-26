package hu.unideb.rft.parkingmanagement.entity;

import hu.unideb.parkingmanagement.enums.ParkingPassValidityTime;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PARKING_PASS_TYPE")
@Getter
@Setter
public class ParkingPassType {

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
    private Integer price;

    @Column(name = "VALIDITY_TIME")
    @Enumerated(EnumType.STRING)
    private ParkingPassValidityTime validityTime;

}
