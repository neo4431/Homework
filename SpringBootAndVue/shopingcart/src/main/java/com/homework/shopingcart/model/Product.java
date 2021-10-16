package com.homework.shopingcart.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class Product implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6201237877676650734L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String manufacturer;

    @Column
    private Long price;

    @Column
    private String photo;

    public Product(String name, String manufacturer, Long price, String photo) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.price = price;
        this.photo = photo;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JsonIgnore
    private OrderLine orderLine;

}