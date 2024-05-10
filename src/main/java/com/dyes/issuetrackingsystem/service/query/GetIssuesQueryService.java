package com.dyes.issuetrackingsystem.service.query;

import com.dyes.issuetrackingsystem.dto.IssueDTO;
import com.dyes.issuetrackingsystem.model.Issue;
import com.dyes.issuetrackingsystem.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetIssuesQueryService {

    private final IssueRepository issueRepository;

    @Autowired
    public GetIssuesQueryService(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    public List<IssueDTO> getAllIssues() {
        List<Issue> issues = issueRepository.findAll();

        if(issues.isEmpty()) {
            return Collections.emptyList();
        }
        return convertToDTOs(issues);
    }

    private List<IssueDTO> convertToDTOs(List<Issue> issues) {
        return issues.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private IssueDTO convertToDTO(Issue issue) {
        return IssueDTO.builder()
                .issueId(issue.getIssueId())
                .title(issue.getTitle())
                .description(issue.getDescription())
                .assignee(issue.getAssignee())
                .priority(issue.getPriority())
                .build();
    }
}

