package com.revature.videoservicespring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.videoservicespring.dao.InsertAllVideoDAO;
import com.revature.videoservicespring.exception.DBException;
import com.revature.videoservicespring.exception.ServiceException;
import com.revature.videoservicespring.model.Video;
import com.revature.videoservicespring.util.MessageConstants;

@Service
public class DeleteVideoService {

	@Autowired
	private InsertAllVideoDAO videoRepository;
	
	public DeleteVideoService(InsertAllVideoDAO deletion) {
		
		this.videoRepository=deletion;
	}
	
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
