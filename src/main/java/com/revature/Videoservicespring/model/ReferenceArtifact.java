package com.revature.videoservicespring.model;

import lombok.Data;

@Data

public class ReferenceArtifact {

	
	private Integer id;
	private String name;
	private String artifact;
	private String description;
	private Integer video_id;
}
