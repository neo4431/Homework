package com.hieunt.todoapp.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String projectName;

    @Column(unique = true)
    private String projectIdentifier;

    private String description;

    private Timestamp startDate;

    private Timestamp endDate;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private String projectLeader;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProjectTask> projectTasks;

    @PrePersist
    public void setCreateAt(){
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    public void updatedAt(){
        this.updatedAt = new Timestamp(System.currentTimeMillis());
    }
}