package com.revature.Videoservicespring.service;

import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.Videoservicespring.dao.VideoDAOImp;
import com.revature.Videoservicespring.exception.ServiceException;
import com.revature.Videoservicespring.model.Video;

@Service
public class ListAllVideos {

	@Autowired
	VideoDAOImp listAllVideos;
	@Transactional
	public List<Video> listAll() throws ServiceException, SQLException
	{
		List<Video> videos=null;
		try {
			videos=listAllVideos.listVideos();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException("Unable to list Videos");
		}
		System.out.println("list"+videos);
		return videos;
	}
}
