package org.abdallah.githubactivity.model;

import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalDateTime;

@Data
public class GithubEvent {

    private Long id;

    private String type;

    private GithubRepo repo;

    private LocalDateTime createdAt;

}
