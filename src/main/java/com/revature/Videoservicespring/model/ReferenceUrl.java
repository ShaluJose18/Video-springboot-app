package com.revature.videoservicespring.model;

import lombok.Data;

@Data

public class ReferenceUrl {

	
	private int id;
	private String name;
	private String artifact;
	private String description;
	private String type;
	private int video_id;
}
