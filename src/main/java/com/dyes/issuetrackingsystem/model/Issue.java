package com.dyes.issuetrackingsystem.model;

import com.dyes.issuetrackingsystem.enums.Priority;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long issueId;

    @NotBlank(message = "You must give a name for the issue")

    private String title;

    @Size(max=100)
    private String description;

    @NotBlank(message = "Name is mandatory")
    @Size(min=4)
    private String assignee;

    @Enumerated(EnumType.STRING)
    private Priority priority;
}

