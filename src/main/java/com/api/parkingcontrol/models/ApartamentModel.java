package com.api.parkingcontrol.models;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.*;

@Entity
@Table(name = "TB_APARTAMENT")
public class ApartamentModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, length = 130)
    private String nameApartament;
    @Column(nullable = false, length = 30)
    private String block;

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getNameApartament() {
        return nameApartament;
    }
    public void setNameApartament(String nameApartament) {
        this.nameApartament = nameApartament;
    }
    public String getBlock() {
        return block;
    }
    public void setBlock(String block) {
        this.block = block;
    }
}
