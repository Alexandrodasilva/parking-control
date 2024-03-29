package com.api.parkingcontrol.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TB_PARKING_SPOT")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ParkingSpotModels implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id; // uuid são identificadores, em que não corre o risco de ter conflitos dentro
                     // arquitetura
    @Column(nullable = false, unique = true, length = 10)
    private String parkingSpotNumber; // numero da vaga
    @Column(nullable = false, unique = true, length = 7)
    private String licensePlateCar; // placa do carro
    @Column(nullable = false, length = 70)
    private String brandCar; // marca do carro
    @Column(nullable = false, length = 70)
    private String modelCar;
    @Column(nullable = false, length = 70)
    private String colorCar;
    @Column(nullable = false)
    private LocalDateTime registrationDate; // data de registro
    @ManyToOne(optional = false)
    @NotNull
    private PersonModel person;
    @ManyToOne(optional = false)
    @NotNull
    private ApartamentModel apartament;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getParkingSpotNumber() {
        return parkingSpotNumber;
    }

    public void setParkingSpotNumber(String parkingStopNumber) {
        this.parkingSpotNumber = parkingStopNumber;
    }

    public String getLicensePlateCar() {
        return licensePlateCar;
    }

    public void setLicensePlateCar(String licensePlatecar) {
        this.licensePlateCar = licensePlatecar;
    }

    public String getBrandCar() {
        return brandCar;
    }

    public void setBrandCar(String branderCar) {
        this.brandCar = branderCar;
    }

    public String getModelCar() {
        return modelCar;
    }

    public void setModelCar(String modelCar) {
        this.modelCar = modelCar;
    }

    public String getColorCar() {
        return colorCar;
    }

    public void setColorCar(String colorCar) {
        this.colorCar = colorCar;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void setPerson(PersonModel person) {
        this.person = person;
    }

    public PersonModel getPerson() {
        return this.person;
    }

    public ApartamentModel getApartament() {
        return apartament;
    }

    public void setApartament(ApartamentModel apartament) {
        this.apartament = apartament;
    }
}
