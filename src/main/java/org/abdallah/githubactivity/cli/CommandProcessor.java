package org.abdallah.githubactivity.cli;

import org.abdallah.githubactivity.service.GithubActivityService;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class CommandProcessor {

    private final GithubActivityService  githubActivityService;

    public CommandProcessor(GithubActivityService githubActivityService) {
        this.githubActivityService = githubActivityService;
    }

    public void process(String[] args) throws IOException, InterruptedException {

        if (args.length == 0) {
            System.out.println("Please provide a github user name");
            return;
        }

        String userName = args[0];

        String userActivities = githubActivityService.getUserActivity(userName);

        System.out.println("User activities: " + userActivities);

    }
}
