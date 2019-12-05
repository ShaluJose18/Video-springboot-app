package com.revature.Videoservicespring.service;

import java.sql.SQLException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.Videoservicespring.dao.InsertAllVideoDAO;
import com.revature.Videoservicespring.dto.VideoDTO;
import com.revature.Videoservicespring.exception.DBException;
import com.revature.Videoservicespring.exception.ServiceException;
import com.revature.Videoservicespring.model.ReferenceArtifact;
import com.revature.Videoservicespring.model.ReferenceUrl;
import com.revature.Videoservicespring.model.SampleProgram;
import com.revature.Videoservicespring.model.Video;
import com.revature.Videoservicespring.util.MessageConstants;

@Service
public class InsertAllVideoService {
	
		@Autowired
		private InsertAllVideoDAO videoRepository; 
		@Transactional
		public Boolean insertVideo(VideoDTO videodto) throws ServiceException, SQLException
		{
			Video video=new Video(); 
			Boolean result;
			video.setVideoName(videodto.getVideoName());
			video.setDisplayName(videodto.getDisplayName());
			video.setVimeoVideoUrl(videodto.getVimeoVideoUrl());
			video.setTags(videodto.getTags());
			video.setDescription(videodto.getDescription());
			video.setTranscript(video.getTranscript());
			video.setLevel_id(videodto.getLevel_id());
			video.setCategory_id(videodto.getCategory_id());
			video.setStatus(true);
			
			ReferenceArtifact artifact=new ReferenceArtifact();
			artifact.setId(videodto.getIdA());
			artifact.setName(videodto.getNameA());
			artifact.setArtifact(videodto.getArtifactA());
			artifact.setDescription(videodto.getDescriptionA());
			artifact.setVideo_id(videodto.getVideoIdA());
			
			SampleProgram program=new SampleProgram();
			program.setId(videodto.getIdP());
			program.setName(videodto.getNameP());
			program.setArtifact(videodto.getArtifactP());
			program.setDescription(videodto.getDescriptionP());
			program.setVideo_id(videodto.getVideoIdoP());
			
			ReferenceUrl url=new ReferenceUrl();
			url.setId(videodto.getIdU());
			url.setName(videodto.getNameU());
			url.setArtifact(videodto.getArtifactU());
			url.setDescription(videodto.getDescriptionU());
			url.setType(videodto.getTypeU());
			url.setVideo_id(videodto.getVideoIdU());
			
			try {
				result=videoRepository.newVideo(video, artifact, program, url);
			} catch (DBException e) {
				e.printStackTrace();
				throw new ServiceException(MessageConstants.video_failed);
			}
			return result;
		}

	}


