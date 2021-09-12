package com.homework.jpa_unit_test.repository;

import java.util.List;

import com.homework.jpa_unit_test.entity.Person;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepo extends JpaRepository<Person, Long> {
    // Đếm số người theo từng công việc theo thứ tự giảm dần
    @Query("select new com.homework.jpa_unit_test.repository.JobCount(p.job, count(*))"
    +"from Person as p group by p.job order by count(*) desc")
    List<JobCount> getListOfJobCount(); 

    // Đếm top 5 công việc có nhiều người làm nhất
    @Query("select new com.homework.jpa_unit_test.repository.JobCount(p.job, count(*))"
    +"from Person as p group by p.job order by count(*) desc")
    List<JobCount> getTopJobs(Pageable pageable);

    // Đếm số lượng người nhóm theo từng công việc và cùng thành phố
    @Query("select new com.homework.jpa_unit_test.repository.JobAndCityCount(p.job, p.city, count(*))"
    +"from Person as p group by p.job, p.city")
    List<JobAndCityCount> getListOfJobAndCityCount(); 

    // Đếm mức lương trung bình theo từng công việc thứ tự giảm dần
    @Query("select new com.homework.jpa_unit_test.repository.JobAverageSalary(p.job, avg(p.salary))"
    +"from Person as p group by p.job order by avg(p.salary) desc")
    List<JobAverageSalary> getListAverageSalaryOfJob(); 

    // Đếm mức lương trung bình theo từng thành phố thứ tự giảm dần
    @Query("select new com.homework.jpa_unit_test.repository.CityAvgSalary(p.city, avg(p.salary))"
    +"from Person as p group by p.city order by avg(p.salary) desc")
    List<CityAvgSalary> getListAverageSalaryOfCity();

    // Đếm 5 công việc nhiều nhất theo từng thành phố
    @Query("select new com.homework.jpa_unit_test.repository.JobAndCityCount(p.job, p.city, count(*))"
    +"from Person as p group by p.city, p.job order by count(*) desc")
    List<JobAndCityCount> getTopJobGroupByCity(Pageable pageable);
}