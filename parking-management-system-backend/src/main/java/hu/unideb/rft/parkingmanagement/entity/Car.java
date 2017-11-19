package hu.unideb.rft.parkingmanagement.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "CAR")
@Getter
@Setter
public class Car {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "CAR_SEQ")
    private Long id;

    @Column(name = "LICENSE_PLATE_NUMBER", unique = true, nullable = false)
    private String licensePlateNumber;

}
