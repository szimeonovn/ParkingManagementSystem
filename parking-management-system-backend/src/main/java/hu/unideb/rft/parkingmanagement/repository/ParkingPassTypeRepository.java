package hu.unideb.rft.parkingmanagement.repository;

import hu.unideb.rft.parkingmanagement.entity.ParkingPassType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingPassTypeRepository extends JpaRepository<ParkingPassType, Long> {
}
