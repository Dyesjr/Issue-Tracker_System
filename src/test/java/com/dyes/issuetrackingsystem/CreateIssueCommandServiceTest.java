package com.dyes.issuetrackingsystem;

import com.dyes.issuetrackingsystem.enums.Priority;
import com.dyes.issuetrackingsystem.model.Issue;
import com.dyes.issuetrackingsystem.repository.IssueRepository;
import com.dyes.issuetrackingsystem.service.command.CreateIssueCommand;
import com.dyes.issuetrackingsystem.service.command.CreateIssueCommandService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class CreateIssueCommandServiceTest {

    @Mock
    private IssueRepository issueRepository;

    @InjectMocks
    private CreateIssueCommandService createIssueCommandService;

    @Test
    public void testHandleIssueCreation_Success() {
        // Prepare mock data
        CreateIssueCommand command = new CreateIssueCommand();
        command.setTitle("Test Issue");
        command.setDescription("This is a test issue");
        command.setAssignee("Assignee");
        command.setPriority(Priority.HIGH);

        // Stubbing: Mock behavior of issueRepository.save()
        when(issueRepository.save(any(Issue.class))).thenAnswer(invocation -> {
            Issue savedIssue = invocation.getArgument(0); // Retrieve the saved issue
            savedIssue.setIssueId(1L); // Set a mock ID for the saved issue
            return savedIssue;
        });

        // Test service method
        Long createdIssueId = createIssueCommandService.handle(command);

        // Verify that issueRepository.save() is called once with any Issue object
        verify(issueRepository, times(1)).save(any(Issue.class));

        // Assert that the created issue ID is not null
        assertNotNull(createdIssueId);
    }
}
