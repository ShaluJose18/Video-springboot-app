package com.revature.Videoservicespring.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.revature.Videoservicespring.dao.VideoDAO;
import com.revature.Videoservicespring.dao.VideoDAOImp;
import com.revature.Videoservicespring.exception.DBException;
import com.revature.Videoservicespring.model.Video;

class TestDAOJunit {

	VideoDAO videodao=new VideoDAOImp();
	
	@Test
	public List<Video> testListVideos() {
		
		
		List<Video> video=null;
		try {
			video=videodao.listVideos();
			assertNotNull(true);
		} catch (DBException e) {
			e.printStackTrace();
		}
		return video;
	}

}
