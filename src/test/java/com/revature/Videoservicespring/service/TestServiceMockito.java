package com.revature.Videoservicespring.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import com.revature.Videoservicespring.dao.VideoDAOImp;
import com.revature.Videoservicespring.exception.DBException;
import com.revature.Videoservicespring.exception.ServiceException;
import com.revature.Videoservicespring.model.Video;

@RunWith(MockitoJUnitRunner.class)
class TestServiceMockito {

	@InjectMocks
	ListAllVideoService listallVideos;

	@Mock
	VideoDAOImp videodao;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testListvideos() {

		List<Video> listService = new ArrayList<Video>(); // Service
		List<Video> listDAO = new ArrayList<Video>(); // DAO
		try {

			Video videoObj = new Video();
			videoObj.setId(1);
			videoObj.setDisplayName("corejava");
			listDAO.add(videoObj);

			listService = listallVideos.listAll();
			when(videodao.listVideos()).thenReturn(listDAO);
			when(listallVideos.listAll()).thenReturn(listService);
			assertEquals(listService, listallVideos.listAll());
			assertNotNull(listService);
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DBException e) {
			e.printStackTrace();
		}

	}

}
