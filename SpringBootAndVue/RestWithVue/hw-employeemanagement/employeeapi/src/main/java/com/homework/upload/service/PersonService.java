package com.homework.upload.service;

import java.util.List;
import java.util.Optional;

import com.homework.upload.exception.ResourceNotFoundException;
import com.homework.upload.model.Person;
import com.homework.upload.model.PersonDto;
import com.homework.upload.repository.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService implements PersonServiceI {
    @Autowired
    private PersonRepository personRepo;

    @Autowired
    private StorageService storageService;

    @Override
    public List<Person> findAll() {
        return personRepo.findAll();
    }

    @Override
    public Person findById(Long id) {
        return personRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy người dùng!!!"));
    }

    @Override
    public void add(Person person) {
        personRepo.save(person);
    }

    @Override
    public Person addPerson(PersonDto personDto) {
        Person person = new Person();
        person.setFullName(personDto.getFullName());
        person.setJob(personDto.getJob());
        person.setGender(personDto.getGender());
        person.setBirthDate(personDto.getBirthDate());
        personRepo.save(person);
        storageService.uploadFile(personDto.getFile(), person.getId());
        return person;
    }

    @Override
    public Person update(Long id, PersonDto personDto) {
        Person person = this.findById(id);
        person.setFullName(personDto.getFullName());
        person.setJob(personDto.getJob());
        person.setGender(personDto.getGender());
        person.setBirthDate(personDto.getBirthDate());
        storageService.uploadFile(personDto.getFile(), person.getId());
        return personRepo.save(person);
    }

    @Override
    public void delete(Long id) {
        Optional<Person> optPerson = personRepo.findById(id);
        if (optPerson.isPresent()) {
            personRepo.delete(optPerson.get());
        } else {
            throw new ResourceNotFoundException("Không tìm thấy người dùng!!!");
        }
    }
}