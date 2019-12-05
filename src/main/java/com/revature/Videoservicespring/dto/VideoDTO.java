package com.revature.Videoservicespring.dto;

import lombok.Data;

@Data
public class VideoDTO {

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
	
	private int idA;
	private String nameA;
	private String artifactA;
	private String descriptionA;
	private int videoIdA;
	
	private int idP;
	private String nameP;
	private String artifactP;
	private String descriptionP;
	private int videoIdoP;
	
	private int idU;
	private String nameU;
	private String artifactU;
	private String descriptionU;
	private String typeU;
	private int videoIdU; 
	
}
