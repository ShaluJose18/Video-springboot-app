package com.revature.videoservicespring.service;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.revature.videoservicespring.dao.VideoDAOImp;
import com.revature.videoservicespring.exception.DBException;
import com.revature.videoservicespring.exception.ServiceException;
import com.revature.videoservicespring.model.Video;


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
		} catch (DBException e) {
			e.printStackTrace();
		}

	}
	

}
