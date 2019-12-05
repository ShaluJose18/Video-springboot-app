package com.revature.Videoservicespring.service;

import java.sql.SQLException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.revature.Videoservicespring.dao.VideoDAOImp;
import com.revature.Videoservicespring.dto.VideoDTO;
import com.revature.Videoservicespring.exception.DBException;
import com.revature.Videoservicespring.exception.ServiceException;
import com.revature.Videoservicespring.model.Video;
import com.revature.Videoservicespring.util.MessageConstants;

@Service
public class VideoService {

	@Autowired
	private VideoDAOImp videoRepository; 
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
		
		try {
			result=videoRepository.insertVideo(video);
		} catch (DBException e) {
			throw new ServiceException(MessageConstants.video_failed);
		}
		return result;
	}

}
