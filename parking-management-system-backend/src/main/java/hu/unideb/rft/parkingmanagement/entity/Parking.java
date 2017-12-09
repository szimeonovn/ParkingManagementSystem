package hu.unideb.rft.parkingmanagement.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "PARKING")
@Getter
@Setter
public class Parking extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "CAR_ID", nullable = false)
    private Car car;

    @ManyToOne
    @JoinColumn(name = "PARKING_ZONE_ID", nullable = false)
    private ParkingZone parkingZone;

    @Column(name = "PARKING_START")
    private LocalDateTime parkingStart;

    @Column(name = "PARKING_END")
    private LocalDateTime parkingEnd;

}
