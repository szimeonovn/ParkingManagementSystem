package hu.unideb.rft.parkingmanagement.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CAR")
@Getter
@Setter
public class Car extends BaseEntity {

    @Column(name = "LICENSE_PLATE_NUMBER", unique = true, nullable = false)
    private String licensePlateNumber;

}
