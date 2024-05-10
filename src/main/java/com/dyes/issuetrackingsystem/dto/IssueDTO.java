package com.dyes.issuetrackingsystem.dto;

import com.dyes.issuetrackingsystem.enums.Priority;
import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class IssueDTO {

    private Long issueId;
    private String title;
    private String description;
    private String assignee;
    private Priority priority;



}

