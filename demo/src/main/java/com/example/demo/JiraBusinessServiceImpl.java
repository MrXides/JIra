package com.example.demo;

import com.atlassian.jira.rest.client.api.*;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import com.atlassian.jira.rest.client.api.domain.input.IssueInput;
import com.atlassian.jira.rest.client.api.domain.input.IssueInputBuilder;
import io.atlassian.util.concurrent.Promise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class JiraBusinessServiceImpl implements JiraBusinessService{
    @Autowired
    private JiraClient jiraClient;
    @Autowired
    private JiraSettings jiraSettings;
    @Autowired
    private ChildJiraBusinessService childJiraBusinessService;

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

    public void copyIssue(){
        JiraRestClient restClient = jiraClient.getJiraRestClient();
        Promise<SearchResult> searchResultPromise = restClient.getSearchClient().searchJql("project = " + jiraSettings.getParentProjectKey());
        for(Issue issue : searchResultPromise.claim().getIssues()){
            childJiraBusinessService.createIssue(issue);
        }
    }
}
