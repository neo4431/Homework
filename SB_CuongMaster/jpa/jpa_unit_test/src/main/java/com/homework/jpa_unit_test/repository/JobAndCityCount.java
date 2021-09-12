package com.homework.jpa_unit_test.repository;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JobAndCityCount {
    private String job;
    private String city;
    private Long count;
}