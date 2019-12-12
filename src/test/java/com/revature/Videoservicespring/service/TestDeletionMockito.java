package com.revature.Videoservicespring.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.revature.Videoservicespring.dao.InsertAllVideoDAO;
import com.revature.Videoservicespring.exception.DBException;
import com.revature.Videoservicespring.exception.ServiceException;

@RunWith(MockitoJUnitRunner.class)
public class TestDeletionMockito {

	@InjectMocks
	DeleteVideoService deleteVideo;
	
	@Mock
	InsertAllVideoDAO deletedao;
	
	@Test
	public void testValidDelete() throws DBException, ServiceException {
		
		boolean check=false;
		when(deletedao.deleteVideo(Mockito.anyInt())).thenReturn(true);
		check=deleteVideo.deleteVideo(Mockito.anyInt());
		assertTrue(check);
	}
	
	@Test(expected=ServiceException.class)
	public void testInvalidDelete() throws DBException, ServiceException {
		when(deletedao.deleteVideo(Mockito.anyInt())).thenThrow(DBException.class);
		deleteVideo.deleteVideo(Mockito.anyInt());
	}

}
