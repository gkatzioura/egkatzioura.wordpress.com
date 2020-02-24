package com.gkatzioura.jobs.model;

import lombok.Data;

@Data
public class Job {

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
