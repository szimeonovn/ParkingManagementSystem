package hu.unideb.rft.parkingmanagement.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "PARKING_PASS")
@Getter
@Setter
public class ParkingPass extends BaseEntity {

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
