package com.revature.Videoservicespring.dto;

import lombok.Data;

@Data
public class VideoaddDTO {
	
	private int id;
	private String videoName;
	private String displayName;
	private String tags;
	private String vimeoVideoUrl;
	private String description;
	private String transcript;
	private int level_id;
	private int category_id;
	private boolean status;

}
