package com.revature.Videoservicespring.service;

import java.sql.SQLException;
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
		
		public Boolean insertVideo(VideoDTO videodto) throws ServiceException, SQLException
		{
			Video video=new Video(); 
			Boolean result;
			video.setVideoName(videodto.getVideo().getVideoName());
			video.setDisplayName(videodto.getVideo().getDisplayName());
			video.setVimeoVideoUrl(videodto.getVideo().getVimeoVideoUrl());
			video.setTags(videodto.getVideo().getTags());
			video.setDescription(videodto.getVideo().getDescription());
			video.setTranscript(videodto.getVideo().getTranscript());
			video.setLevel_id(videodto.getVideo().getLevel_id());
			video.setCategory_id(videodto.getVideo().getCategory_id());
			video.setStatus(true);
			
			ReferenceArtifact artifact=new ReferenceArtifact();
			artifact.setId(videodto.getArtifact().getId());
			artifact.setName(videodto.getArtifact().getName());
			artifact.setArtifact(videodto.getArtifact().getArtifact());
			artifact.setDescription(videodto.getArtifact().getDescription());
			artifact.setVideo_id(videodto.getArtifact().getVideo_id());
			
			SampleProgram program=new SampleProgram();
			program.setId(videodto.getSampleprogram().getId());
			program.setName(videodto.getSampleprogram().getName());
			program.setArtifact(videodto.getSampleprogram().getArtifact());
			program.setDescription(videodto.getSampleprogram().getDescription());
			program.setVideo_id(videodto.getSampleprogram().getVideo_id());
			
			ReferenceUrl url=new ReferenceUrl();
			url.setId(videodto.getUrl().getId());
			url.setName(videodto.getUrl().getName());
			url.setArtifact(videodto.getUrl().getArtifact());
			url.setDescription(videodto.getUrl().getDescription());
			url.setType(videodto.getUrl().getType());
			url.setVideo_id(videodto.getUrl().getVideo_id());
			
			try {
				result=videoRepository.newVideo(videodto);
			} catch (DBException e) {
				e.printStackTrace();
				throw new ServiceException(MessageConstants.video_failed);
			}
			return result;
		}

	}


