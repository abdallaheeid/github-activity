package org.abdallah.githubactivity.service;

import org.abdallah.githubactivity.client.GithubApiClient;
import org.abdallah.githubactivity.client.GithubClient;
import org.abdallah.githubactivity.exception.UserNotFoundException;
import org.abdallah.githubactivity.model.GithubEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class GithubActivityService {

    private final GithubClient githubClient;

    public GithubActivityService(GithubClient githubClient) {
        this.githubClient = githubClient;
    }

    /**
     * Main service method used by the CLI.
     * - Validates the user
     * - Fetches user events
     * - Converts them into readable activity messages
     */
    public String
    getUserActivity(String username) throws IOException, InterruptedException {
        if (!githubClient.userExists(username)) {
            throw new UserNotFoundException(username);
        }

        String rawEvents = githubClient.fetchUserEvents(username);

        return rawEvents;


    }

    /**
     * Fetches raw GitHub events for a given user
     */
    List<GithubEvent> fetchEvents(String username) {
        return null;
    }

    /**
     * Converts raw GitHub events into formatted text lines
     */
    List<String> formatEvents(List<GithubEvent> events) {
        return null;
    }

    /**
     * Optional: limits the number of results returned
     */
    List<GithubEvent> limitEvents(List<GithubEvent> events, int limit) {
        return null;
    }
}
