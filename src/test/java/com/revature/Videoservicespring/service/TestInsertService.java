package com.revature.Videoservicespring.service;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import org.junit.jupiter.api.Test;
import com.revature.Videoservicespring.dto.VideoDTO;
import com.revature.Videoservicespring.exception.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;


public class TestInsertService {

	@Autowired
	InsertAllVideoService insertion;
	
	
	
	@Test
	void addVideo() throws ServiceException, SQLException {
		
		boolean value=false;
		
		VideoDTO videodto=new VideoDTO();

//		video.setVideoName("Java Servlet");
//		videodto.setVideo(video);
		videodto.getVideo().setId(7);
		videodto.getVideo().setVideoName("python");
		videodto.getVideo().setDisplayName("python programming");
		videodto.getVideo().setVimeoVideoUrl("vimeo/345678");
		videodto.getVideo().setTags("java");
		videodto.getVideo().setDescription("programming");
		videodto.getVideo().setTranscript("python2345678");
		videodto.getVideo().setLevel_id(2);
		videodto.getVideo().setCategory_id(1);
		
		videodto.getArtifact().setId(3);
		videodto.getArtifact().setName("python");
		videodto.getArtifact().setArtifact("artifact");
		videodto.getArtifact().setDescription("python34567");
		videodto.getArtifact().setVideo_id(7);
		
		videodto.getSampleprogram().setId(3);
		videodto.getSampleprogram().setName("program");
		videodto.getSampleprogram().setArtifact("python");
		videodto.getSampleprogram().setDescription("pythonprogramming");
		videodto.getSampleprogram().setVideo_id(7);
		
		videodto.getUrl().setId(3);
		videodto.getUrl().setName("url");
		videodto.getUrl().setArtifact("javaurl");
		videodto.getUrl().setDescription("java video");
		videodto.getUrl().setType("javatutorial");
		videodto.getUrl().setVideo_id(7);
		
		value=insertion.insertVideo(videodto);
		assertNotNull(value);
		
	}
	
}
