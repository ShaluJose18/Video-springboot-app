package com.revature.Videoservicespring.service;

import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.Videoservicespring.dao.VideoDAOImp;
import com.revature.Videoservicespring.exception.DBException;
import com.revature.Videoservicespring.exception.ServiceException;
import com.revature.Videoservicespring.model.Video;

@Service
public class ListAllVideoService {

	@Autowired
	VideoDAOImp listAllVideos;
	
	public ListAllVideoService(VideoDAOImp videoDao) {
		this.listAllVideos=videoDao;
	}
	

	@Transactional
	public List<Video> listAll() throws ServiceException, SQLException
	{
		List<Video> videos=null;
		try {
			videos=listAllVideos.listVideos();
		} catch (DBException e) {
			e.printStackTrace();
			throw new SQLException("Unable to list Videos");
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
