package com.revature.videoservicespring.dto;

import com.revature.videoservicespring.model.ReferenceArtifact;
import com.revature.videoservicespring.model.ReferenceUrl;
import com.revature.videoservicespring.model.SampleProgram;
import com.revature.videoservicespring.model.Video;

import lombok.Data;

@Data
public class VideoDTO {

	private Video video;
	private ReferenceArtifact artifact;
	private SampleProgram sampleprogram;
	private ReferenceUrl url;
	
}
