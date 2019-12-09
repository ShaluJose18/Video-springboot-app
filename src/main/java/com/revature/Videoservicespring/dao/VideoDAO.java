package com.revature.Videoservicespring.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.Videoservicespring.exception.DBException;
import com.revature.Videoservicespring.model.Video;

public interface VideoDAO {
	
	public Boolean insertVideo(Video video) throws DBException, SQLException;
	public List<Video> listVideos() throws DBException;

}
