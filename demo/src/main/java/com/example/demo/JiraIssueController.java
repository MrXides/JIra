package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//localhost:8080/issue/create
@RestController
@RequestMapping(path = "/issue")
public class JiraIssueController {
    @Autowired
    private JiraBusinessService jiraBusinessService;

    @PostMapping("/create")
    protected String createIssue(@RequestParam("projectKey") String projectKey,
                                 @RequestParam("issueType") Long issueType,
                                 @RequestParam("issueSummary") String issueSummary){
        return jiraBusinessService.createIssue(projectKey, issueType, issueSummary);
    }

    @GetMapping("/copy")
    protected void Copy(){
        jiraBusinessService.copyIssue();
    }
}
