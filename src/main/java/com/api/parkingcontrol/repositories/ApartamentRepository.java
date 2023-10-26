package com.api.parkingcontrol.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.api.parkingcontrol.models.ApartamentModel;

@Repository
public interface ApartamentRepository  extends JpaRepository<ApartamentModel, UUID>{
    
}
