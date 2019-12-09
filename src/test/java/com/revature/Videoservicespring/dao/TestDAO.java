package com.revature.Videoservicespring.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.revature.Videoservicespring.dao.VideoDAOImp;
import com.revature.Videoservicespring.exception.DBException;
import com.revature.Videoservicespring.model.Video;

class TestDAO {

	@Mock
	VideoDAOImp videodao=new VideoDAOImp();
	
	@Test
	public List<Video> testSelectVideos() {
		List<Video> video=null;
		assertNotNull(true);
	try {
		//videoDAO=VideoDAOImp mock()
		videodao.listVideos();
		
	} catch (DBException e) {
		e.printStackTrace();
	}
	return video;
	}
}
