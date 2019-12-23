package com.revature.videoservicespring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.revature.videoservicespring.dao.VideoDAOImp;
import com.revature.videoservicespring.exception.DBException;

@RunWith(MockitoJUnitRunner.class)
public class TestDAOMockito {

	@InjectMocks
	private VideoDAOImp videodao;
	
	@Mock
	private DataSource connection;
	
	@Mock
	private PreparedStatement pst;
	
	@Mock
	private ResultSet rs;
	
	@Mock
	private Connection con;
	
	@Before
	public void setUp() throws SQLException {
		MockitoAnnotations.initMocks(this);
		
		Mockito.lenient().when(connection.getConnection()).thenReturn(con);
		Mockito.lenient().when(con.prepareStatement(Mockito.anyString())).thenReturn(pst);
		Mockito.lenient().when(pst.executeQuery()).thenReturn(rs);
		Mockito.lenient().when(rs.next()).thenReturn(Boolean.TRUE);
	}
	
	@Test
	public void listVideos() throws DBException {
		videodao.listVideos();
	}
	
	@Test
	public void listActiveVideos() throws DBException {
		videodao.listActiveVideos(true);
	}
	

}
