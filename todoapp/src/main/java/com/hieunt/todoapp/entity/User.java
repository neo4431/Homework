package com.hieunt.todoapp.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Email
    @NotNull(message = "Username is required!!!")
    @NotBlank(message = "Username is required!!!")
    private String email;

    @NotNull(message = "Password is required!!!")
    @NotBlank(message = "Password is required!!!")
    @Size(min = 6, message = "Password must be at least 6 characters!!!")
    private String password;

    private String fullName;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Project> projects;

    @PrePersist
    public void setCreateAt(){
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    public void updatedAt(){
        this.updatedAt = new Timestamp(System.currentTimeMillis());
    }
}