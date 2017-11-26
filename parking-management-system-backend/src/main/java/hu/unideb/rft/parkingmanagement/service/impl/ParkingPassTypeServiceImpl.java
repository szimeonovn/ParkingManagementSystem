package hu.unideb.rft.parkingmanagement.service.impl;

import hu.unideb.rft.parkingmanagement.repository.ParkingPassTypeRepository;
import hu.unideb.rft.parkingmanagement.service.ParkingPassTypeService;
import hu.unideb.rft.parkingmanagement.vo.ParkingPassTypeVO;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ParkingPassTypeServiceImpl implements ParkingPassTypeService {

    @Autowired
    ParkingPassTypeRepository parkingPassTypeRepository;

    @Autowired
    Mapper mapper;

    @Override
    public List<ParkingPassTypeVO> list() {
        return parkingPassTypeRepository.findAll().stream().map(parkingPassType -> mapper.map(parkingPassType, ParkingPassTypeVO.class)).collect(Collectors.toList());
    }
}
