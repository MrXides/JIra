package com.example.demo;

import com.atlassian.jira.rest.client.api.domain.Issue;

public interface JiraBusinessService {
    String createIssue(String projectKey, Long issueType, String issueSummary);
    void updateIssueDescription(String issueKey, String newDescription);
    Issue getIssue(String issueKey);
    void deleteIssue(String issueKey, boolean deleteSubtasks);
    void copyIssue();
}
