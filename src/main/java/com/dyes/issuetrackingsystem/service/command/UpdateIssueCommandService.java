package com.dyes.issuetrackingsystem.service.command;

import com.dyes.issuetrackingsystem.model.Issue;
import com.dyes.issuetrackingsystem.repository.IssueRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UpdateIssueCommandService {

    private final IssueRepository issueRepository;

    @Autowired
    public UpdateIssueCommandService(IssueRepository issueRepository){
        this.issueRepository = issueRepository;
    }

    @Transactional
    public void handle(UpdateIssueCommand command) {
        // Retrieve the issue by ID
        Issue issue = issueRepository.findById(command.getIssueId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Issue not found with ID: " + command.getIssueId()));

        // Update the issue with the provided command details
        issue.setTitle(command.getTitle());
        issue.setDescription(command.getDescription());
        issue.setAssignee(command.getAssignee());
        issue.setPriority(command.getPriority());

        // Save the updated issue
        try {
            issueRepository.save(issue);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Failed to update issue with ID: " + command.getIssueId(), e);
        }
    }
}

