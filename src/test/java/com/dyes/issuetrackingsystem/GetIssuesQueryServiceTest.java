package com.dyes.issuetrackingsystem;

import com.dyes.issuetrackingsystem.dto.IssueDTO;
import com.dyes.issuetrackingsystem.model.Issue;
import com.dyes.issuetrackingsystem.repository.IssueRepository;
import com.dyes.issuetrackingsystem.service.query.GetIssuesQueryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class GetIssuesQueryServiceTest {

    @Mock
    private IssueRepository issueRepository;

    @InjectMocks
    private GetIssuesQueryService getIssuesQueryService;

    @Test
    public void testGetAllIssues_Success() {
        // Prepare mock data
        List<Issue> mockIssues = new ArrayList<>();
        mockIssues.add(new Issue());
        mockIssues.add(new Issue());

        // Stubbing: Mock behavior of issueRepository.findAll()
        when(issueRepository.findAll()).thenReturn(mockIssues);

        // Test service method
        List<IssueDTO> issueDTOs = getIssuesQueryService.getAllIssues();

        // Assert that the number of retrieved IssueDTOs matches the number of mock issues
        assertEquals(mockIssues.size(), issueDTOs.size());

        // Assert that each retrieved IssueDTO matches its corresponding mock issue
        for (int i = 0; i < mockIssues.size(); i++) {
            Issue mockIssue = mockIssues.get(i);
            IssueDTO issueDTO = issueDTOs.get(i);

            assertEquals(mockIssue.getIssueId(), issueDTO.getIssueId());
            assertEquals(mockIssue.getTitle(), issueDTO.getTitle());
            assertEquals(mockIssue.getDescription(), issueDTO.getDescription());
            assertEquals(mockIssue.getAssignee(), issueDTO.getAssignee());
            assertEquals(mockIssue.getPriority(), issueDTO.getPriority());
        }
    }


}