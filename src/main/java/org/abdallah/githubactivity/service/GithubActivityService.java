package org.abdallah.githubactivity.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.abdallah.githubactivity.client.GithubClient;
import org.abdallah.githubactivity.exception.UserNotFoundException;
import org.abdallah.githubactivity.model.GithubEvent;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class GithubActivityService {

    private final GithubClient githubClient;

    public GithubActivityService(GithubClient githubClient) {
        this.githubClient = githubClient;
    }

    public List<String> getUserActivity(String username) throws IOException, InterruptedException {
        if (!githubClient.userExists(username)) {
            throw new UserNotFoundException(username);
        }

        String rawEvents = githubClient.fetchUserEvents(username);

        List<GithubEvent> githubEvents = fetchEvents(rawEvents);

        List<GithubEvent> githubEventsLimited = limitEvents(githubEvents, 5);

        return formatEvents(githubEventsLimited);

    }

    private List<GithubEvent> fetchEvents(String jsonEvents) throws IOException {
        if (jsonEvents == null || jsonEvents.isEmpty()) {
            return new ArrayList<>();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        GithubEvent[] githubEvents = objectMapper.readValue(jsonEvents, GithubEvent[].class);
        return Arrays.asList(githubEvents);
    }

    List<String> formatEvents(List<GithubEvent> events) {
        if (events == null || events.isEmpty()) {
            return new ArrayList<>();
        }

        List<String> formattedEvents = new ArrayList<>();

        for (GithubEvent event : events) {
            String type = event.getType();
            String repoName = event.getRepo() != null ? event.getRepo().getName() : "(Unknown Repository)";

            String description;

            switch (type) {
                case "PushEvent" -> description = "Pushed commits to " + repoName;
                case "WatchEvent" -> description = "Starred " + repoName;
                case "IssueEvent" -> description = "Opened an Issue in " + repoName;
                default -> description = type + " in " + repoName;
            }

            formattedEvents.add("- " + description);
        }

        return formattedEvents;
    }

    private List<GithubEvent> limitEvents(List<GithubEvent> events, int limit) {
        if (events == null || events.isEmpty() || limit <= 0) {
            return new ArrayList<>();
        }
        if (events.size() <= limit) {
            return events;
        }
        return new ArrayList<>(events.subList(0, limit));
    }
}
