package com.homework.jpa_unit_test;

import com.homework.jpa_unit_test.repository.CityAvgSalary;
import com.homework.jpa_unit_test.repository.JobAndCityCount;
import com.homework.jpa_unit_test.repository.JobAverageSalary;
import com.homework.jpa_unit_test.repository.JobCount;
import com.homework.jpa_unit_test.repository.PersonRepo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@DataJpaTest
@Sql("/person.sql")
public class PersonTest {
    @Autowired
    PersonRepo personRepo;

    @Test
    @DisplayName("Get List Of Job Count")
    public void getListOfJobCountTest() {
        List<JobCount> list = personRepo.getListOfJobCount();
        list.forEach(System.out::println);
        assertThat(list.get(0).getCount()).isEqualTo(4);
    }

    @Test
    @DisplayName("Get Top 5 Job")
    public void getTop5JobsTest(){
        List<JobCount> list = personRepo.getTopJobs(PageRequest.of(0, 5));
        list.forEach(System.out::println);
        assertThat(list.size()).isEqualTo(5);
    }

    @Test
    @DisplayName("Get List Of Job And City Count")
    public void getListOfJobAndCityCountTest(){
        List<JobAndCityCount> list = personRepo.getListOfJobAndCityCount();
        list.forEach(System.out::println);
        assertThat(list.size()).isGreaterThan(0);
    }

    @Test
    @DisplayName("Get List Average Salary Of Job")
    public void getListAverageSalaryOfJobTest(){
        List<JobAverageSalary> list = personRepo.getListAverageSalaryOfJob();
        list.forEach(System.out::println);
        assertThat(list.size()).isGreaterThan(0);
    }

    @Test
    @DisplayName("get List Average Salary Of City")
    public void getListAverageSalaryOfCityTest(){
        List<CityAvgSalary> list = personRepo.getListAverageSalaryOfCity();
        list.forEach(System.out::println);
        assertThat(list.size()).isGreaterThan(0);
    }

    @Test
    @DisplayName("get Top 5 Job Group By City")
    public void getTop5JobGroupByCityTest(){
        List<JobAndCityCount> list = personRepo.getTopJobGroupByCity(PageRequest.of(0, 5));
        list.forEach(System.out::println);
        assertThat(list.size()).isGreaterThan(0);
    }
}