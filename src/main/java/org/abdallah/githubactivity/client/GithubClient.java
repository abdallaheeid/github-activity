package org.abdallah.githubactivity.client;

import java.io.IOException;

public interface GithubClient {

    String fetchUserEvents(String username) throws IOException, InterruptedException;

    boolean userExists(String username) throws IOException, InterruptedException;
}
