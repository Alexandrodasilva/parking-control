package com.api.parkingcontrol.services;

import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

import com.api.parkingcontrol.models.PersonModel;
import com.api.parkingcontrol.repositories.PersonRepository;

@Service
public class PersonService {
    final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Transactional
    public PersonModel save(PersonModel person) {
        return personRepository.save(person);
    }
}
