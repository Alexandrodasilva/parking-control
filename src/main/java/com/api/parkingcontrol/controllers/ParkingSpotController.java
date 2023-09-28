package com.api.parkingcontrol.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.parkingcontrol.controllers.dtos.ParkingSpotDto;
import com.api.parkingcontrol.models.ParkingSpotModels;
import com.api.parkingcontrol.models.Person;
import com.api.parkingcontrol.services.ParkingSpotService;
import com.api.parkingcontrol.services.PersonService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/parking-spot")
public class ParkingSpotController {

    final ParkingSpotService parkingSpotService;
    final PersonService personService;

    public ParkingSpotController(ParkingSpotService parkingSpotService, PersonService personService) {
        this.parkingSpotService = parkingSpotService;
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<Object> saveParkingSpot(@RequestBody @Valid ParkingSpotModels parkingSpotDto) {

        if (parkingSpotService.existsByLicensePlateCar(parkingSpotDto.getLicensePlateCar())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: license plate car is already in  use");
        }
        if (parkingSpotService.existsByParkingSpotNumber(parkingSpotDto.getParkingSpotNumber())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Parking spot is already in use");
        }
        if (parkingSpotService.existsByApartmentAndBlock(parkingSpotDto.getApartment(), parkingSpotDto.getBlock())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Conflict: paking spot already registered  for this apartment/block");
        }
        Person person = personService.save(parkingSpotDto.getPerson());

        var parkingSpotModels = new ParkingSpotModels();
        BeanUtils.copyProperties(parkingSpotDto, parkingSpotModels);
        parkingSpotModels.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        parkingSpotModels.setPerson(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingSpotService.save(parkingSpotModels));
    }

    @GetMapping
    public ResponseEntity<List<ParkingSpotModels>> getAllParkingSpots(
    /*
     * @PageableDefault(page = 0, size = 10, sort = "id",
     * direction = Sort.Direction.ASC) Pageable pageable
     */) {
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.findAll(/* pageable */));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneParkingSpot(@PathVariable(value = "id") UUID id) {
        Optional<ParkingSpotModels> parkingSpotModelOptional = parkingSpotService.findById(id);
        if (!parkingSpotModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("parking spot not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletParkingSpot(@PathVariable(value = "id") UUID id) {
        Optional<ParkingSpotModels> parkingSpotModelOptional = parkingSpotService.findById(id);
        if (!parkingSpotModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("parking spot not found");
        }
        parkingSpotService.delet(parkingSpotModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("sucessfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateParkingSpot(@PathVariable(value = "id") UUID id,
            @RequestBody @Valid ParkingSpotDto parkingSpotDto) {
        Optional<ParkingSpotModels> parkingSpotModelOptional = parkingSpotService.findById(id);
        if (!parkingSpotModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("parking spot not found");
        }
        ParkingSpotModels parkingSpotModels = parkingSpotModelOptional.get();
        parkingSpotModels.setParkingSpotNumber(parkingSpotDto.getParkingSpotNumber());
        parkingSpotModels.setLicensePlateCar(parkingSpotDto.getLicensePlateCar());
        parkingSpotModels.setModelCar(parkingSpotDto.getModelCar());
        parkingSpotModels.setBrandCar(parkingSpotDto.getBrandCar());
        parkingSpotModels.setColorCar(parkingSpotDto.getColorCar());
        parkingSpotModels.setResponsibleName(parkingSpotDto.getResponsibleName());
        parkingSpotModels.setApartment(parkingSpotDto.getApartment());
        parkingSpotModels.setBlock(parkingSpotDto.getBlock());
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.save(parkingSpotModels));
    }

}
