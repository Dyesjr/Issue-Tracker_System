package com.dyes.issuetrackingsystem.service.command;

import com.dyes.issuetrackingsystem.model.Issue;
import com.dyes.issuetrackingsystem.repository.IssueRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DeleteIssueCommandService {

    private final IssueRepository issueRepository;

    @Autowired
    public DeleteIssueCommandService(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    @Transactional
    public void handle (DeleteIssueCommand command) {
        Issue issue = issueRepository.findById(command.getIssueId())
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Issue not found with ID: " + command.getIssueId()));

        issueRepository.delete(issue);
    }
}
