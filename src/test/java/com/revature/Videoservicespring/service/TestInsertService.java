package com.revature.Videoservicespring.service;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.revature.Videoservicespring.dto.VideoDTO;
import com.revature.Videoservicespring.exception.ServiceException;
import com.revature.Videoservicespring.model.ReferenceArtifact;
import com.revature.Videoservicespring.model.ReferenceUrl;
import com.revature.Videoservicespring.model.SampleProgram;
import com.revature.Videoservicespring.model.Video;

import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest
public class TestInsertService {

	@Autowired
	InsertAllVideoService insertion;
	
	@Test
	void addVideo() throws ServiceException, SQLException {
		
		boolean value=false;
		Video video=new Video();
		ReferenceArtifact artifact=new ReferenceArtifact();
		SampleProgram program=new SampleProgram();
		ReferenceUrl url=new ReferenceUrl();
		
		VideoDTO videodto=new VideoDTO();
		video.setCategory_id(1);
		video.setDescription("core java");
		video.setDisplayName("core java");
		video.setId(8);
		video.setLevel_id(2);
		video.setStatus(true);
		video.setTags("java");
		video.setTranscript("corejava/56789");
		video.setVideoName("Java Servlet");
		videodto.setVideo(video);
		
		artifact.setId(5);
		artifact.setName("java");
		artifact.setArtifact("java");
		artifact.setDescription("java");
		artifact.setVideo_id(8);
		videodto.setArtifact(artifact);
		
		program.setId(5);
		program.setName("java");
		program.setArtifact("java");
		program.setDescription("java");
		program.setVideo_id(8);
		videodto.setSampleprogram(program);
		
		url.setId(5);
		url.setName("java");
		url.setArtifact("java");
		url.setDescription("java");
		url.setType("tutorial");
		url.setVideo_id(8);
		videodto.setUrl(url);
		
		value=insertion.insertVideo(videodto);
		assertTrue(value);
		
	}
	
}
