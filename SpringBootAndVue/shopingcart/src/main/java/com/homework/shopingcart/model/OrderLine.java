package com.homework.shopingcart.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
public class OrderLine implements Serializable {
    private static final long serialVersionUID = -3239063316084655346L;
    @Id
    private Long id;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @PrimaryKeyJoinColumn
    private Product product;
    @Column
    private int count;

    public void increase() {
        this.count += 1;
    }

    public void decrease() {
        this.count = this.count - 1;
    }

    @ManyToOne()
    @JoinColumn(name = "cart_id")
    @JsonIgnore
    private Cart cart;

}