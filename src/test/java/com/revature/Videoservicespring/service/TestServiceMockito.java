package com.revature.Videoservicespring.service;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.revature.Videoservicespring.dao.VideoDAOImp;
import com.revature.Videoservicespring.exception.DBException;
import com.revature.Videoservicespring.exception.ServiceException;
import com.revature.Videoservicespring.model.Video;

@RunWith(MockitoJUnitRunner.class)
public class TestServiceMockito {

	@InjectMocks
	ListAllVideoService listallVideos;

	@Mock
	VideoDAOImp videodao;

	

	@Test
	public void testListvideos() {

		List<Video> listService = new ArrayList<Video>(); 	// Service
		List<Video> listDAO = new ArrayList<Video>(); 		// DAO
		try {

			Video videoObj = new Video();
			videoObj.setId(1);
			videoObj.setVideoName(".Net");
			videoObj.setDisplayName("corejava");
			videoObj.setTags(".net");
			videoObj.setCategory_id(1);
			videoObj.setLevel_id(2);
			listDAO.add(videoObj);
			
			System.out.println(listDAO);
			when(videodao.listVideos()).thenReturn(listDAO);
			listService=listallVideos.listAll();
			assertEquals(listService,listDAO);
			
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DBException e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void testActiveList() {
		
		List<Video> listService = new ArrayList<Video>(); // Service
		List<Video> listDAO = new ArrayList<Video>(); // DAO
		try {

			Video videoObj = new Video();
			videoObj.setId(1);
			videoObj.setVideoName(".Net");
			videoObj.setDisplayName("corejava");
			videoObj.setStatus(false);
			listDAO.add(videoObj);
			
			System.out.println(listDAO);
			when(videodao.listVideos()).thenReturn(listDAO);
			listService=listallVideos.listAll();
			assertEquals(listService,listDAO);
			
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DBException e) {
			e.printStackTrace();
		}

	}
	

}
