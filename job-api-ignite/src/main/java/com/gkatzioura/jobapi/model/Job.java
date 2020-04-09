package com.gkatzioura.jobapi.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Job implements Serializable {

	private String id;
	private String type;
	private String url;
	private String createdAt;
	private String company;
	private String companyUrl;
	private String location;
	private String title;
	private String description;

}
