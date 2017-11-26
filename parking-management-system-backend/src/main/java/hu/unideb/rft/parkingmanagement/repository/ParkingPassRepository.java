package hu.unideb.rft.parkingmanagement.repository;

import hu.unideb.rft.parkingmanagement.entity.ParkingPass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingPassRepository extends JpaRepository<ParkingPass, Long> {
}
