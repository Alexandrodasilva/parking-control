package com.api.parkingcontrol.services;

import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

import com.api.parkingcontrol.models.Person;
import com.api.parkingcontrol.repositories.PersonRepository;

@Service
public class PersonService {
    final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Transactional
    public Person save(Person person) {
        return personRepository.save(person);
    }
}
