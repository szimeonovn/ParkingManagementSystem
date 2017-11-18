package hu.unideb.rft.parkingmanagement.service.impl;

import hu.unideb.rft.parkingmanagement.entity.ParkingZone;
import hu.unideb.rft.parkingmanagement.repository.ParkingZoneRepository;
import hu.unideb.rft.parkingmanagement.service.ParkingZoneService;
import hu.unideb.rft.parkingmanagement.vo.ParkingZoneVO;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ParkingZoneServiceImpl implements ParkingZoneService {

    @Autowired
    private ParkingZoneRepository parkingZoneRepository;

    @Autowired
    private Mapper mapper;

    @Override
    public ParkingZoneVO save(ParkingZoneVO parkingZoneVO) {

        ParkingZone parkingZoneToSave = mapper.map(parkingZoneVO, ParkingZone.class);
        ParkingZone savedParkingZone = parkingZoneRepository.save(parkingZoneToSave);
        return mapper.map(savedParkingZone, ParkingZoneVO.class);

    }
}
