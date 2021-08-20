package com.homework.upload.service;

import java.util.List;

import com.homework.upload.model.Person;
import com.homework.upload.model.PersonDto;

public interface PersonServiceI {
    public List<Person> findAll();

    public Person findById(Long id);

    public void add(Person person);

    public Person update(Long id, PersonDto personDto);

    public void delete(Long id);

    public Person addPerson(PersonDto personDto);

}