package com.homework.pagingandsorting.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "This field is not empty")
    @Column
    private String name;

    @NotNull(message = "This field is not empty")
    @Column
    private String brand;

    @NotNull(message = "This field is not empty")
    @Column
    private String madeIn;

    @NotNull(message = "This field is not empty")
    @Min(value = 0, message="must be equal or greater than 0")
    @Column
    private Float price;
}