package org.abdallah.githubactivity.client;

import org.abdallah.githubactivity.exception.GitHubApiException;
import org.abdallah.githubactivity.exception.UserNotFoundException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class GithubApiClient implements GithubClient{

    @Override
    public String fetchUserEvents(String username) throws IOException, InterruptedException {
        if (!userExists(username)) {
            throw new UserNotFoundException(username);
        }

        String url = "https://api.github.com/users/" + username + "/events";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/vnd.github+json")
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new GitHubApiException("Github API returned status code " + response.statusCode());
        }

        return response.body();
    }

    @Override
    public boolean userExists(String username) throws IOException, InterruptedException {

        String url = "https://api.github.com/users/" + username;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/vnd.github+json")
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.statusCode() == 200;

    }
}
