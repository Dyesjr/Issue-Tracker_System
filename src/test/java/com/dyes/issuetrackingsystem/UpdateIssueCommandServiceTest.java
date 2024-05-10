package com.dyes.issuetrackingsystem;

import com.dyes.issuetrackingsystem.enums.Priority;
import com.dyes.issuetrackingsystem.model.Issue;
import com.dyes.issuetrackingsystem.repository.IssueRepository;
import com.dyes.issuetrackingsystem.service.command.UpdateIssueCommand;
import com.dyes.issuetrackingsystem.service.command.UpdateIssueCommandService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class UpdateIssueCommandServiceTest {

    @Mock
    private IssueRepository issueRepository;

    @InjectMocks
    private UpdateIssueCommandService updateIssueCommandService;

    @Test
    public void testHandleIssueUpdate_Success() {
        // Prepare mock data
        Long issueId = 1L;
        Issue existingIssue = new Issue();
        existingIssue.setIssueId(issueId);
        existingIssue.setTitle("Test Issue");
        existingIssue.setDescription("This is a test issue");
        existingIssue.setAssignee("Assignee");
        existingIssue.setPriority(Priority.HIGH);

        // Stubbing: Mock behavior of issueRepository.findById()
        when(issueRepository.findById(issueId)).thenReturn(Optional.of(existingIssue));

        // Prepare update command
        UpdateIssueCommand command = new UpdateIssueCommand();
        command.setIssueId(issueId);
        command.setTitle("Updated Test Issue");
        command.setDescription("This is an updated test issue");
        command.setAssignee("Updated Assignee");
        command.setPriority(Priority.LOW);

        // Test service method
        updateIssueCommandService.handle(command);

        // Verify that issueRepository.save() is called once with the updated issue
        verify(issueRepository, times(1)).save(existingIssue);

        // Assert that the issue's fields are updated correctly
        assertEquals("Updated Test Issue", existingIssue.getTitle());
        assertEquals("This is an updated test issue", existingIssue.getDescription());
        assertEquals("Updated Assignee", existingIssue.getAssignee());
        assertEquals(Priority.LOW, existingIssue.getPriority());
    }

    @Test(expected = ResponseStatusException.class)
    public void testHandleIssueUpdate_NotFound() {
        // Prepare mock data
        Long issueId = 1L;

        // Stubbing: Mock behavior of issueRepository.findById() to return an empty Optional
        when(issueRepository.findById(issueId)).thenReturn(Optional.empty());

        // Prepare update command
        UpdateIssueCommand command = new UpdateIssueCommand();
        command.setIssueId(issueId);
        command.setTitle("Updated Test Issue");

        // Test service method
        updateIssueCommandService.handle(command);
    }
}


