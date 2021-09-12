package com.homework.jpa_unit_test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "person")
@Data
@AllArgsConstructor
@Builder
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FULL_NAME", nullable = false)
    private String fullName;

    @Column(name = "JOB", nullable = false)
    private String job;

    @Column(name = "GENDER", nullable = false)
    private String gender;

    @Column(name = "CITY", nullable = false)
    private String city;

    @Column(name = "SALARY", nullable = false)
    private Double salary;
}