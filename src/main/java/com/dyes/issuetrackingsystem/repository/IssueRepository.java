package com.dyes.issuetrackingsystem.repository;

import com.dyes.issuetrackingsystem.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {
    List<Issue> findAll();

    Optional<Issue> findById(Long issueId);
}
