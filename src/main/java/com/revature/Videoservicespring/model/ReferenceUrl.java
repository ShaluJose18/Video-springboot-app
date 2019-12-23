package com.revature.videoservicespring.model;

import lombok.Data;

@Data

public class ReferenceUrl {

	
	private Integer id;
	private String name;
	private String artifact;
	private String description;
	private String type;
	private Integer video_id;
}
