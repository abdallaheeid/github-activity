package org.abdallah.githubactivity;

import lombok.extern.slf4j.Slf4j;
import org.abdallah.githubactivity.cli.CommandProcessor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class GithubActivityApplication implements CommandLineRunner {

    private final CommandProcessor commandProcessor;

    public GithubActivityApplication(CommandProcessor commandProcessor) {
        this.commandProcessor = commandProcessor;
    }

	public static void main(String[] args) {
		SpringApplication.run(GithubActivityApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        commandProcessor.process(args);
    }
}
