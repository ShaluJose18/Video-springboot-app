package com.revature.Videoservicespring.service;

import static org.junit.jupiter.api.Assertions.*;

//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.Test;

import com.revature.Videoservicespring.dao.InsertAllVideoDAO;
import com.revature.Videoservicespring.dao.VideoDAOImp;
import com.revature.Videoservicespring.dto.VideoDTO;
import com.revature.Videoservicespring.exception.ServiceException;
import com.revature.Videoservicespring.model.ReferenceArtifact;
import com.revature.Videoservicespring.model.ReferenceUrl;
import com.revature.Videoservicespring.model.SampleProgram;
import com.revature.Videoservicespring.model.Video;
import com.revature.Videoservicespring.util.ConnectionUtil;

class TestJunit {

	ListAllVideoService listAllVideos = new ListAllVideoService(new VideoDAOImp(new ConnectionUtil()));
	InsertAllVideoService insertVideo=new InsertAllVideoService(new InsertAllVideoDAO(new ConnectionUtil()));

	@Test
	void listVideos() throws ServiceException, SQLException {
		List<Video> video = null;
		video = listAllVideos.listAll();
		assertNotNull(video);
	}

	@Test
	void listActiveVideo() throws ServiceException {
		boolean status = true;
		List<Video> video = null;
		video = listAllVideos.listActiveVideos(status);
		assertNotNull(video);
	}

	@Test
	void listDeactiveVideo() throws ServiceException {
		boolean status = false;
		List<Video> video = null;
		video = listAllVideos.listActiveVideos(status);
		assertNotNull(video);
	}
	
	@Test
	void insertVideoTest() {
		
		boolean check=false;
		VideoDTO videodto=new VideoDTO();
		
		Video video=new Video();
		video.setId(13);
		video.setVideoName("Java");
		video.setDisplayName("java");
		video.setVimeoVideoUrl("vimeo/url/45678");
		video.setTags("java");
		video.setDescription("java,css");
		video.setTranscript("video56345678");
		video.setLevel_id(1);
		video.setCategory_id(2);
		video.setStatus(true);
		videodto.setVideo(video);
		
		ReferenceArtifact artifact=new ReferenceArtifact();
		artifact.setId(7);
		artifact.setName("java");
		artifact.setArtifact("artifact");
		artifact.setDescription("htmlvideo");
		artifact.setVideo_id(13);
		videodto.setArtifact(artifact);
		
		SampleProgram pgm=new SampleProgram();
		pgm.setId(7);
		pgm.setName("java");
		pgm.setArtifact("program");
		pgm.setDescription("sampleprogram");
		pgm.setVideo_id(13);
		videodto.setSampleprogram(pgm);
		
		ReferenceUrl url=new ReferenceUrl();
		url.setId(7);
		url.setName("java");
		url.setArtifact("url");
		url.setDescription("urldescription");
		url.setType("other");
		url.setVideo_id(13);
		videodto.setUrl(url);
		
		
		try {
			check=insertVideo.insertVideo(videodto);
		} catch (ServiceException | SQLException e) {
			e.printStackTrace();
		}
		assertTrue(check);
		
	}
	
	@Test//(expected = ServiceException.class)
	void insertVideoInvalidTest() throws ServiceException, SQLException {
		
		boolean check=false;
		VideoDTO videodto=new VideoDTO();
		
		Video video=new Video();
		video.setId(14);
		video.setVideoName("java");
		video.setDisplayName("java");
		video.setVimeoVideoUrl("vimeo/url/452365478");
		video.setTags("java");
		video.setDescription("html,css");
		video.setTranscript("video345678");
		video.setLevel_id(1);
		video.setCategory_id(2);
		video.setStatus(true);
		videodto.setVideo(video);
		
		ReferenceArtifact artifact=new ReferenceArtifact();
		artifact.setId(8);
		artifact.setName("javas");
		artifact.setArtifact("artifact");
		artifact.setDescription("htmlvideo");
		artifact.setVideo_id(14);
		videodto.setArtifact(artifact);
		
		SampleProgram pgm=new SampleProgram();
		pgm.setId(8);
		pgm.setName("javas");
		pgm.setArtifact("program");
		pgm.setDescription("sampleprogram");
		pgm.setVideo_id(14);
		videodto.setSampleprogram(pgm);
		
		ReferenceUrl url=new ReferenceUrl();
		url.setId(8);
		url.setName("javas");
		url.setArtifact("url");
		url.setDescription("urldescription");
		url.setType("other");
		url.setVideo_id(14);
		videodto.setUrl(url);
		
			check=insertVideo.insertVideo(videodto);
			assertTrue(check);
		
	}


}
