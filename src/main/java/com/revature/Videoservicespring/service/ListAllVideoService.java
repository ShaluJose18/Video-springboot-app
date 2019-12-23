package com.revature.videoservicespring.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.videoservicespring.dao.VideoDAOImp;
import com.revature.videoservicespring.exception.DBException;
import com.revature.videoservicespring.exception.ServiceException;
import com.revature.videoservicespring.model.Video;

@Service
public class ListAllVideoService {

	@Autowired
	VideoDAOImp listAllVideos;
	
	public ListAllVideoService(VideoDAOImp videoDao) {
		this.listAllVideos=videoDao;
	}
	

	@Transactional
	public List<Video> listAll() throws ServiceException
	{
		List<Video> videos=null;
		try {
			videos=listAllVideos.listVideos();
		} catch (DBException e) {
			e.printStackTrace();
			throw new ServiceException("Unable to list Videos");
		}
		System.out.println("list"+videos);
		return videos;
	}
	
	@Transactional
	public List<Video> listActiveVideos(boolean status) throws ServiceException{
		List<Video> videos=null;
		try {
			videos=listAllVideos.listActiveVideos(status);
		} catch (DBException e) {
			e.printStackTrace();
			throw new ServiceException("Unable to list Videos");
		
	}
		System.out.println("Active List"+videos);
		return videos;
}
}
