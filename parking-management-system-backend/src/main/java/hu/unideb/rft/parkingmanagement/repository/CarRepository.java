package hu.unideb.rft.parkingmanagement.repository;

import hu.unideb.rft.parkingmanagement.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {

    Car findByLicensePlateNumber(String licensePlateNumber);

}
