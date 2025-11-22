package org.abdallah.githubactivity.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String username) {
        super("GithubActivity user with name " + username + " not found");
    }

}
