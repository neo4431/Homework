package com.homework.jpa_unit_test.repository;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JobAverageSalary {
    private String job;
    private Double avgSalary;

}