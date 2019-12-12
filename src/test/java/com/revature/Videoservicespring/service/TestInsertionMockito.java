package com.revature.Videoservicespring.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.revature.Videoservicespring.dao.InsertAllVideoDAO;
import com.revature.Videoservicespring.dto.VideoDTO;
import com.revature.Videoservicespring.exception.DBException;
import com.revature.Videoservicespring.exception.ServiceException;
import com.revature.Videoservicespring.model.ReferenceArtifact;
import com.revature.Videoservicespring.model.ReferenceUrl;
import com.revature.Videoservicespring.model.SampleProgram;
import com.revature.Videoservicespring.model.Video;

@RunWith(MockitoJUnitRunner.class)
public class TestInsertionMockito {

	@InjectMocks
	InsertAllVideoService insertVideo;
	
	@Mock
	InsertAllVideoDAO insertdao;
	
	
	@Test
	public void testValidInsertion() throws SQLException, DBException {
		
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
		
		
		when(insertdao.newVideo(videodto)).thenReturn(true);
		try {
			check=insertVideo.insertVideo(videodto);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		assertTrue(check);
		
		
	}
	
	@Test(expected=ServiceException.class)
	public void testInvalidInsertion() throws SQLException, DBException, ServiceException {
		
		VideoDTO videodto=new VideoDTO();
		
		Video video=new Video();
		video.setId(13);
		video.setVideoName("Java");
		video.setDisplayName("java");
		video.setLevel_id(1);
		video.setCategory_id(2);
		video.setStatus(true);
		videodto.setVideo(video);
		
		ReferenceArtifact artifact=new ReferenceArtifact();
		artifact.setId(7);
		artifact.setName("java");
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
		
		
		when(insertdao.newVideo(videodto)).thenThrow(DBException.class);
		insertVideo.insertVideo(videodto);
		assertTrue(true);
		
	}
	

}
