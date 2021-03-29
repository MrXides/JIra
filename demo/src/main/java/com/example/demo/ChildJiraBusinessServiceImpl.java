package com.example.demo;

import com.atlassian.jira.rest.client.api.IssueRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.input.IssueInput;
import com.atlassian.jira.rest.client.api.domain.input.IssueInputBuilder;
import org.springframework.beans.factory.annotation.Autowired;

public class ChildJiraBusinessServiceImpl implements ChildJiraBusinessService{
    @Autowired
    private ChildJiraClient jiraClient;

    @Override
    public String createIssue(Issue issue) {
        JiraRestClient restClient = jiraClient.getJiraRestClient();
        IssueRestClient issueClient = restClient.getIssueClient();
        IssueInput newIssue = new IssueInputBuilder(
                issue.getProject(), issue.getIssueType(), issue.getSummary()).build();
        return issueClient.createIssue(newIssue).claim().getKey();

    }

    @Override
    public String createIssue(String projectKey, Long issueType, String issueSummary) {
        JiraRestClient restClient = jiraClient.getJiraRestClient();
        IssueRestClient issueClient = restClient.getIssueClient();
        IssueInput newIssue = new IssueInputBuilder(
                projectKey, issueType, issueSummary).build();
        return issueClient.createIssue(newIssue).claim().getKey();

    }

    @Override
    public void updateIssueDescription(String issueKey, String newDescription) {
        JiraRestClient restClient = jiraClient.getJiraRestClient();
        IssueInput input = new IssueInputBuilder()
                .setDescription(newDescription)
                .build();
        restClient.getIssueClient()
                .updateIssue(issueKey, input)
                .claim();
    }

    @Override
    public Issue getIssue(String issueKey) {
        JiraRestClient restClient = jiraClient.getJiraRestClient();
        return restClient.getIssueClient()
                .getIssue(issueKey)
                .claim();
    }

    @Override
    public void deleteIssue(String issueKey, boolean deleteSubtasks) {
        JiraRestClient restClient = jiraClient.getJiraRestClient();
        restClient.getIssueClient()
                .deleteIssue(issueKey, deleteSubtasks)
                .claim();
    }
}
