package com.dyes.issuetrackingsystem.service.command;

import com.dyes.issuetrackingsystem.model.Issue;
import com.dyes.issuetrackingsystem.repository.IssueRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateIssueCommandService {

    private final IssueRepository issueRepository;

    @Autowired
    public CreateIssueCommandService(IssueRepository issueRepository){
        this.issueRepository = issueRepository;
    }

    @Transactional
    public Long handle(CreateIssueCommand command) {
        try {
            Issue issue = new Issue();
            issue.setIssueId((command.getIssueId()));
            issue.setTitle(command.getTitle());
            issue.setDescription(command.getDescription());
            issue.setAssignee(command.getAssignee());
            issue.setPriority(command.getPriority());

            issueRepository.save(issue);
            return issue.getIssueId();
        } catch (Exception e) {
//            e.printStackTrace();

            throw new RuntimeException("An error occurred while trying to create the issue. You could have keyed in wrong values. Try again later");
        }
    }
}

