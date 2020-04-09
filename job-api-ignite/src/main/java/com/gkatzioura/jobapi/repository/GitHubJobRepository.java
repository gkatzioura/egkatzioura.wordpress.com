package com.gkatzioura.jobapi.repository;

import java.util.ArrayList;
import java.util.List;

import com.gkatzioura.jobapi.model.Job;
import lombok.Data;
import org.apache.ignite.Ignite;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class GitHubJobRepository {

	private static final String JOB_API_CONSTANST = "https://jobs.github.com/positions.json?page={page}";
	public static final String GITHUBJOB_CACHE = "githubjob";

	private final RestTemplate restTemplate;
	private final Ignite ignite;

	GitHubJobRepository(Ignite ignite) {
		this.restTemplate = new RestTemplate();
		this.ignite = ignite;
	}

	@Cacheable(value = GITHUBJOB_CACHE)
	public List<Job> getJob(int page) {
		return restTemplate.getForObject(JOB_API_CONSTANST,JobList.class,page);
	}

	public List<Job> fetchFromIgnite(int page) {
		for(String cache: ignite.cacheNames()) {
			if(cache.equals(GITHUBJOB_CACHE)) {
				return (List<Job>) ignite.getOrCreateCache(cache).get(1);
			}
		}

		return new ArrayList<>();
	}

	@Data
	private static class JobList  extends ArrayList<Job> {
	}
}
