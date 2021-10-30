package com.hieunt.todoapp.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "project_tasks")
public class ProjectTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer projectSequence;

    private Integer summary;

    private String acceptanceCriteria;

    private String status;

    private Integer priority;

    private Timestamp dueDate;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
}