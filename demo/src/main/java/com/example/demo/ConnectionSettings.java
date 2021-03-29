package com.example.demo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@Getter
public class ConnectionSettings {
    /*
        Jira родитель
     */
    private final String parentUserName = "t.jira";
    private final String parentPassword = "FlD3MPBt";
    private final String parentJiraUrl = "http://jira.omnichat.tech";

    /*
        Jira наследник
     */
    private final String childUserName = "";
    private final String childPassword = "";
    private final String childJiraUrl = "";


}
