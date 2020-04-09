package com.gkatzioura.jobapi.controller;

import java.util.List;

import com.gkatzioura.jobapi.model.Job;
import com.gkatzioura.jobapi.repository.GitHubJobRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobs")
public class JobsController {

	private final GitHubJobRepository gitHubJobRepository;

	JobsController(GitHubJobRepository gitHubJobRepository) {
		this.gitHubJobRepository = gitHubJobRepository;
	}

	@GetMapping("/github/{page}")
	public List<Job> gitHub(@PathVariable("page") int page) {
		return this.gitHubJobRepository.getJob(page);
	}

	@GetMapping("/github/ignite/{page}")
	public List<Job> gitHubIgnite(@PathVariable("page") int page) {
		return this.gitHubJobRepository.fetchFromIgnite(page);
	}

}
