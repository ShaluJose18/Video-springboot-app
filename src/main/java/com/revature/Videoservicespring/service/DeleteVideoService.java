package com.revature.Videoservicespring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.Videoservicespring.dao.InsertAllVideoDAO;
import com.revature.Videoservicespring.exception.DBException;
import com.revature.Videoservicespring.exception.ServiceException;
import com.revature.Videoservicespring.model.Video;
import com.revature.Videoservicespring.util.MessageConstants;

@Service
public class DeleteVideoService {

	@Autowired
	private InsertAllVideoDAO videoRepository; 
	
	public boolean deleteVideo(int videoId) throws ServiceException {
		boolean result=false;
		Video video=new Video();
		video.setId(videoId);
		
		
			try {
				result=videoRepository.deleteVideo(videoId);
			} catch (DBException e) {
				e.printStackTrace();
				throw new ServiceException(MessageConstants.delete_video);
			}
		
		return result;
		
	}
	
}
