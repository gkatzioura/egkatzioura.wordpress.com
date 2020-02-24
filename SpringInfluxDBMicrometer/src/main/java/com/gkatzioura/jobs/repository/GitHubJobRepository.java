package com.gkatzioura.jobs.repository;

import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

import com.gkatzioura.jobs.model.Job;

import reactor.core.publisher.Mono;

@Repository
public class GitHubJobRepository {

    private WebClient githubClient;

    public GitHubJobRepository() {
        this.githubClient = WebClient.create("https://jobs.github.com");
    }

    public Mono<List<Job>> getJobsFromPage(int page) {

        return githubClient.method(HttpMethod.GET)
                           .uri("/positions.json?page=" + page)
                           .retrieve()
                           .bodyToFlux(Job.class)
                           .collectList();
    }

}
