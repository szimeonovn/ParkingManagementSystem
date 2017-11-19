package hu.unideb.rft.parkingmanagement.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "PARKING")
@Getter
@Setter
public class Parking {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
    @SequenceGenerator(name = "SEQ", sequenceName = "PARKING_SEQ")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CAR_ID")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "PARKING_ZONE_ID", nullable = false)
    private ParkingZone parkingZone;

    @Column(name = "PARKING_START")
    private LocalDateTime parkingStart;

    @Column(name = "PARKING_END")
    private LocalDateTime parkingEnd;

}
