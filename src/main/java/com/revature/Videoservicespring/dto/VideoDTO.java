package com.revature.Videoservicespring.dto;

import com.revature.Videoservicespring.model.ReferenceArtifact;
import com.revature.Videoservicespring.model.ReferenceUrl;
import com.revature.Videoservicespring.model.SampleProgram;
import com.revature.Videoservicespring.model.Video;

import lombok.Data;

@Data
public class VideoDTO {

	private Video video;
	private ReferenceArtifact artifact;
	private SampleProgram sampleprogram;
	private ReferenceUrl url;
	
}
