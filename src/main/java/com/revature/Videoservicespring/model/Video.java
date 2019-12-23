package com.revature.videoservicespring.model;


import lombok.Data;

@Data

public class Video {
	private Integer id;
	private String videoName;
	private String displayName;
	private String tags;
	private String vimeoVideoUrl;
	private String description;
	private String transcript;
	private Integer level_id;
	private Integer category_id;
	private boolean status;
	

}
