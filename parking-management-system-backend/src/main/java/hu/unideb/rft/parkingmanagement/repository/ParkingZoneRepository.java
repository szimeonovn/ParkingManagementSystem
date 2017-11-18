package hu.unideb.rft.parkingmanagement.repository;

import hu.unideb.rft.parkingmanagement.entity.ParkingZone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingZoneRepository extends JpaRepository<ParkingZone, Long> {
}
