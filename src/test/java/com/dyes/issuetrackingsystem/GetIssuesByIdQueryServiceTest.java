package com.dyes.issuetrackingsystem;

import com.dyes.issuetrackingsystem.dto.IssueDTO;
import com.dyes.issuetrackingsystem.enums.Priority;
import com.dyes.issuetrackingsystem.model.Issue;
import com.dyes.issuetrackingsystem.repository.IssueRepository;
import com.dyes.issuetrackingsystem.service.query.GetIssuesByIdQueryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class GetIssuesByIdQueryServiceTest {

    @Mock
    private IssueRepository issueRepository;

    @InjectMocks
    private GetIssuesByIdQueryService getIssuesByIdQueryService;

    @Test
    public void testGetIssueById_Success() {
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

        // Test service method
        IssueDTO issueDTO = getIssuesByIdQueryService.getIssueById(issueId);

        // Assert that the retrieved IssueDTO matches the existing issue
        assertEquals(issueId, issueDTO.getIssueId());
        assertEquals("Test Issue", issueDTO.getTitle());
        assertEquals("This is a test issue", issueDTO.getDescription());
        assertEquals("Assignee", issueDTO.getAssignee());
        assertEquals(Priority.HIGH, issueDTO.getPriority());
    }

    @Test(expected = ResponseStatusException.class)
    public void testGetIssueById_NotFound() {
        // Prepare mock data
        Long issueId = 1L;

        // Stubbing: Mock behavior of issueRepository.findById() to return an empty Optional
        when(issueRepository.findById(issueId)).thenReturn(Optional.empty());

        // Test service method
        getIssuesByIdQueryService.getIssueById(issueId);
    }
}

