package hu.unideb.rft.parkingmanagement.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.io.Serializable;

@Data
@Entity
public class ParkingZone implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "PARKING_ZONE_SEQ")
    private Long id;

    @Column(unique = true, nullable = false)
    private String zoneCode;
}
