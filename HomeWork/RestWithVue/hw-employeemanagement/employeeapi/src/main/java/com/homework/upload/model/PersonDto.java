package com.homework.upload.model;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Long id;

    @NotEmpty(message = "{not.null}")
    @Size(min = 5, max = 50, message = "{name.size}")
    private String fullName;

    @NotEmpty(message = "{not.null}")
    private String job;

    @NotEmpty(message = "{not.null}")
    private String gender;

    @NotNull(message = "{not.null}")
    @NotEmpty(message = "{not.null}")
    private String birthDate;

    @NotNull(message = "{not.null}")
    private MultipartFile file;
}