package com.example.demo;

import com.atlassian.jira.rest.client.api.domain.Issue;

public interface ChildJiraBusinessService {
    String createIssue(String projectKey, Long issueType, String issueSummary);
    String createIssue(Issue issue);
    void updateIssueDescription(String issueKey, String newDescription);
    Issue getIssue(String issueKey);
    void deleteIssue(String issueKey, boolean deleteSubtasks);
}
