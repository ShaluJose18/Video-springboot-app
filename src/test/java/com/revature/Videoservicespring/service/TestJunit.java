package com.revature.Videoservicespring.service;

import static org.junit.Assert.assertNotNull;
import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.Test;

import com.revature.Videoservicespring.dao.VideoDAOImp;
import com.revature.Videoservicespring.exception.ServiceException;
import com.revature.Videoservicespring.model.Video;
import com.revature.Videoservicespring.util.ConnectionUtil;

class TestJunit {

	ListAllVideoService listAllVideos=new ListAllVideoService(new VideoDAOImp(new ConnectionUtil()));
	
	@Test
	void listVideos() throws ServiceException, SQLException {
		List<Video> video=null;
		video=listAllVideos.listAll();
		assertNotNull(video);
	}
	
	@Test
	void listActiveVideo() throws ServiceException {
		boolean status=true;
		List<Video> video=null;
		video=listAllVideos
				
				
				.listActiveVideos(status);
		assertNotNull(video);
	}
	
	
	@Test
	void listDeactiveVideo() throws ServiceException {
		boolean status=false;
		List<Video> video=null;
		video=listAllVideos.listActiveVideos(status);
		assertNotNull(video);
	}


}
