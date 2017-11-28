package hu.unideb.rft.parkingmanagement.repository;

import hu.unideb.rft.parkingmanagement.entity.Car;
import hu.unideb.rft.parkingmanagement.entity.ParkingPass;
import hu.unideb.rft.parkingmanagement.entity.ParkingZone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingPassRepository extends JpaRepository<ParkingPass, Long> {

    ParkingPass findByCar(Car car);

    ParkingPass findByCarAndParkingZone(Car car, ParkingZone parkingZone);

}
