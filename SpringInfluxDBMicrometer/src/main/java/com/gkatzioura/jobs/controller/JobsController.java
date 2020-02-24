package com.gkatzioura.jobs.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gkatzioura.jobs.model.Job;
import com.gkatzioura.jobs.repository.GitHubJobRepository;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/jobs")
public class JobsController {

    private final GitHubJobRepository gitHubJobRepository;

    public JobsController(GitHubJobRepository gitHubJobRepository) {
        this.gitHubJobRepository = gitHubJobRepository;
    }

    @GetMapping("/github/{page}")
    private Mono<List<Job>> getEmployeeById(@PathVariable int page) {
        return gitHubJobRepository.getJobsFromPage(page);
    }

}
