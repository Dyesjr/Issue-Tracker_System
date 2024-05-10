package com.dyes.issuetrackingsystem.service.query;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetIssuesByIdQuery {
    private String issueId;
}
