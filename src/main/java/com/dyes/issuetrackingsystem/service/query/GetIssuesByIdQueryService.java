package com.dyes.issuetrackingsystem.service.query;

import com.dyes.issuetrackingsystem.dto.IssueDTO;
import com.dyes.issuetrackingsystem.model.Issue;
import com.dyes.issuetrackingsystem.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class GetIssuesByIdQueryService {

    private final IssueRepository issueRepository;

    @Autowired
    public GetIssuesByIdQueryService(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    public IssueDTO getIssueById(Long issueId) {
        Issue issue = issueRepository.findById(issueId)

                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Issue with that ID not found: " + issueId));

        return convertToDTO(issue);
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
