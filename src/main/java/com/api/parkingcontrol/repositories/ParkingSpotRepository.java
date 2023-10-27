package com.api.parkingcontrol.repositories;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.api.parkingcontrol.models.ParkingSpotModels;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpotModels, UUID> {

    boolean existsByLicensePlateCar(String licensePlateCar);

    boolean existsByParkingSpotNumber(String parkingSpotNumber);

    List<ParkingSpotModels> findAll(/* Pageable pageable */);
}
