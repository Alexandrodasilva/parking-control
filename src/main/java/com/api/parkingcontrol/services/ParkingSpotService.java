package com.api.parkingcontrol.services;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.parkingcontrol.models.ParkingSpotModels;
import com.api.parkingcontrol.repositories.ParkingSpotRepository;

@Service
public class ParkingSpotService {
    // @Autowired // esse autowired é dizer que para o spring que vai precisar ser
    // injetado as dependencias do parkingSpotRepository, com toda criação do bin
    final ParkingSpotRepository parkingSpotRepository;

    public ParkingSpotService(ParkingSpotRepository parkingSpotRepository) {
        this.parkingSpotRepository = parkingSpotRepository;
    }

    @Transactional
    public ParkingSpotModels save(ParkingSpotModels parkingSpotModels) {
        return parkingSpotRepository.save(parkingSpotModels);
    }
    public boolean existsByLicensePlateCar(String licensePlateCar) {
    return parkingSpotRepository.existsByLicensePlateCar(licensePlateCar);
    }
    public boolean existsByParkingSpotNumber(String parkingSpotNumber) {
    return parkingSpotRepository.existsByParkingSpotNumber(parkingSpotNumber);
    }

    public List<ParkingSpotModels> findAll(/* Pageable pageable */) {
        return parkingSpotRepository.findAll(/* pageable */);
    }

    public Optional<ParkingSpotModels> findById(UUID id) {
        return parkingSpotRepository.findById(id);
    }

    @Transactional
    public void delet(ParkingSpotModels parkingSpotModels) {
        parkingSpotRepository.delete(parkingSpotModels);
    }
}
