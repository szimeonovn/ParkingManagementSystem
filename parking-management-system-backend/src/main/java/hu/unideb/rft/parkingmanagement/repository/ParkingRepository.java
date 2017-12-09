package hu.unideb.rft.parkingmanagement.repository;

import hu.unideb.rft.parkingmanagement.entity.Car;
import hu.unideb.rft.parkingmanagement.entity.Parking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParkingRepository extends JpaRepository<Parking, Long> {
    Parking findByCarAndParkingEndIsNull(Car car);

    List<Parking> findAllByParkingEndIsNull();
}
