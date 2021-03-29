package com.example.demo;

import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter

public class JiraSettings {
    private final String parentProjectKey = "OMNIDEV";
    private final String childProjectKey = "MES";
}
