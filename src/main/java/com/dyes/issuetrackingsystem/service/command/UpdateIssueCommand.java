package com.dyes.issuetrackingsystem.service.command;

import com.dyes.issuetrackingsystem.enums.Priority;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class UpdateIssueCommand {

    private Long issueId;
    private String title;
    private String description;
    private String assignee;

    @Enumerated(EnumType.STRING)
    private Priority priority;
}