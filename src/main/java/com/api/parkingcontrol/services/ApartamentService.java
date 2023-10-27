package com.api.parkingcontrol.services;

import org.springframework.stereotype.Service;

import com.api.parkingcontrol.models.ApartamentModel;
import com.api.parkingcontrol.repositories.ApartamentRepository;

@Service
public class ApartamentService {
    final ApartamentRepository apartamentRepository;

    public ApartamentService(ApartamentRepository apartamentRepository) {
        this.apartamentRepository = apartamentRepository;
    }

    public ApartamentModel save(ApartamentModel apartament) {
        return apartamentRepository.save(apartament);
    }

    public boolean existsByApartmentAndBlock(String apartment, String block) {
        return apartamentRepository.existsByApartmentAndBlock(apartment, block);
    }
}
