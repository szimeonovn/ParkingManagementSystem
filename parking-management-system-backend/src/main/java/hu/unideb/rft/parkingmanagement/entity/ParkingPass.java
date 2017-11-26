package hu.unideb.rft.parkingmanagement.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "PARKING_PASS")
@Getter
@Setter
public class ParkingPass {

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CAR_ID", nullable = false)
    Car car;

    @ManyToOne
    @JoinColumn(name = "PARKING_ZONE_ID", nullable = false)
    ParkingZone parkingZone;

    @Column(name = "VALIDITY_START")
    LocalDateTime validityStart;

    @Column(name = "VALIDITY_END")
    LocalDateTime validityEnd;

}
