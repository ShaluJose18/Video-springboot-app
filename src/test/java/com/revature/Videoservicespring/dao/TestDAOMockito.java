package com.revature.Videoservicespring.dao;

import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.revature.Videoservicespring.exception.DBException;

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
		//MockitoAnnotations.initMocks(this);
		when(connection.getConnection()).thenReturn(con);
		when(con.prepareStatement(Mockito.anyString())).thenReturn(pst);
		when(pst.executeQuery()).thenReturn(rs);
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
