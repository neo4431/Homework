package com.homework.upload.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.homework.upload.model.Person;
import com.homework.upload.model.PersonDto;
import com.homework.upload.service.PersonServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/people")
@CrossOrigin
public class ApiPersonController {
    @Autowired
    private PersonServiceI personService;

    @GetMapping
    public ResponseEntity<List<Person>> getAll(){
        return ResponseEntity.ok(personService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id){
        return ResponseEntity.ok(personService.findById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addPerson(@ModelAttribute @Valid PersonDto personDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(bindingResult.getAllErrors());
        }
        return ResponseEntity.ok(personService.addPerson(personDto));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updatePerson(@PathVariable("id") Long id, @ModelAttribute @Valid PersonDto personDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(bindingResult.getAllErrors());
        }
        return ResponseEntity.status(HttpStatus.OK.value()).body(personService.update(id, personDto));
    }

    @DeleteMapping("/delete/{id}")
    public Map<String, Boolean> deletePersonById(@PathVariable Long id){
        personService.delete(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}