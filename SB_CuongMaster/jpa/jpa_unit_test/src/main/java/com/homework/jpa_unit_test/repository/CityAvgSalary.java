package com.homework.jpa_unit_test.repository;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CityAvgSalary {
    private String city;
    private Double avgSalary;
}