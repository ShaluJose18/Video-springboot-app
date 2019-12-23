package com.revature.videoservicespring.model;

import lombok.Data;

@Data

public class ReferenceArtifact {

	
	private int id;
	private String name;
	private String artifact;
	private String description;
	private int video_id;
}
