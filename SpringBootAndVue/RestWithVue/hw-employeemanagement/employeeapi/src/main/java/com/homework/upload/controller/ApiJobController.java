package com.homework.upload.controller;

import java.util.List;

import javax.validation.Valid;

import com.homework.upload.model.Job;
import com.homework.upload.service.JobServiceI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/jobs")
@CrossOrigin
public class ApiJobController {
    @Autowired
    private JobServiceI jobService;

    @GetMapping
    public ResponseEntity<List<Job>> getJobs() {
        return ResponseEntity.ok(jobService.findAll());
    }

    @PostMapping(value="/add")
    public ResponseEntity<?> postMethodName(@RequestBody @Valid Job job) {
        Job response = jobService.addJob(job);
        if(response == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body("Nghề này đã tồn tại!!!");
        }
        return ResponseEntity.status(HttpStatus.OK.value()).body(response);
    }
    
}