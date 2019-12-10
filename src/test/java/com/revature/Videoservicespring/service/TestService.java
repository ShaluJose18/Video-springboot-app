package com.revature.Videoservicespring.service;

//import org.junit.Test;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertNotNull;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.revature.Videoservicespring.exception.ServiceException;
import com.revature.Videoservicespring.model.Video;

@SpringBootTest
class TestService {
	@Autowired
	ListAllVideos videos;
	

	@Test
	void listVideos() throws ServiceException, SQLException {
		List<Video> video=null;
		video=videos.listAll();
		assertNotNull(video);
	}
	
	@Test
	void listActiveVideo() throws ServiceException {
		boolean status=true;
		List<Video> video=null;
		video=videos.listActiveVideos(status);
		assertNotNull(video);
	}
	
	
	@Test
	void listDeactiveVideo() throws ServiceException {
		boolean status=false;
		List<Video> video=null;
		video=videos.listActiveVideos(status);
		assertNotNull(video);
	}

	
}
