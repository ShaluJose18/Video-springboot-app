package com.revature.videoservicespring.dao;

import java.util.List;

import com.revature.videoservicespring.exception.DBException;
import com.revature.videoservicespring.model.Video;

public interface VideoDAO {
	
	
	public List<Video> listVideos() throws DBException;
	public List<Video> listActiveVideos( boolean status ) throws DBException;
	public void updateVideos(int request_id) throws DBException;

}
