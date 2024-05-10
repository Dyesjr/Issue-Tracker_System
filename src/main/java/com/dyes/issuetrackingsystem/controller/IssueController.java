package com.dyes.issuetrackingsystem.controller;

import com.dyes.issuetrackingsystem.dto.IssueDTO;
import com.dyes.issuetrackingsystem.service.command.*;
import com.dyes.issuetrackingsystem.service.query.GetIssuesByIdQueryService;
import com.dyes.issuetrackingsystem.service.query.GetIssuesQueryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/issues")
public class IssueController {

    private final CreateIssueCommandService createIssueCommandService;
    private final GetIssuesQueryService getIssuesQueryService;
    private final GetIssuesByIdQueryService getIssuesByIdQueryService;
    private final UpdateIssueCommandService updateIssueCommandService;
    private final DeleteIssueCommandService deleteIssueCommandService;


    @Autowired
    public IssueController(CreateIssueCommandService createIssueCommandService, GetIssuesQueryService getIssuesQueryService
            , GetIssuesByIdQueryService getIssuesByIdQueryService
            , UpdateIssueCommandService updateIssueCommandService
            , DeleteIssueCommandService deleteIssueCommandService) {

        this.createIssueCommandService = createIssueCommandService;
        this.getIssuesQueryService = getIssuesQueryService;
        this.getIssuesByIdQueryService = getIssuesByIdQueryService;
        this.updateIssueCommandService = updateIssueCommandService;
        this.deleteIssueCommandService = deleteIssueCommandService;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping()
    public ResponseEntity<List<IssueDTO>> getAllIssues () {
        List<IssueDTO> issues = getIssuesQueryService.getAllIssues();
        return new ResponseEntity<>(issues, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{issueId}")
    public ResponseEntity<IssueDTO> getIssueById(@PathVariable Long issueId) {
        IssueDTO issue = getIssuesByIdQueryService.getIssueById(issueId);
        return new ResponseEntity<>(issue, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<String> createIssue(@Valid @RequestBody CreateIssueCommand command) {
        String createdIssueId = String.valueOf(createIssueCommandService.handle(command));
        return new ResponseEntity<>(createdIssueId, HttpStatus.CREATED);
    }

    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{issueId}")
    public ResponseEntity<Void> updateIssue(@PathVariable("issueId") Long issueId, @RequestBody UpdateIssueCommand command) {
        command.setIssueId(issueId);
        updateIssueCommandService.handle(command);
        return ResponseEntity.ok().build();
    }


    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("delete/{issueId}")
    public ResponseEntity<Void> deleteIssue(@PathVariable("issueId") Long issueId) {
        DeleteIssueCommand command = new DeleteIssueCommand();
        command.setIssueId(issueId);
        deleteIssueCommandService.handle(command);
        return ResponseEntity.noContent().build();
    }


}

