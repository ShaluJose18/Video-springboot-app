package com.revature.videoservicespring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

import com.revature.videoservicespring.exception.DBException;
import com.revature.videoservicespring.model.Video;
import com.revature.videoservicespring.util.ConnectionUtil;
import com.revature.videoservicespring.util.MessageConstants;

@Repository
public class VideoDAOImp implements VideoDAO {
	
	Connection con = null;
	PreparedStatement pst = null;
	Boolean result = false;


	// Select All videos from Videos

	public List<Video> listVideos() throws DBException {
		ResultSet rs = null;
		List<Video> list = null;
		
		try {
			con = ConnectionUtil.getConnection();
			String sql = "select id,name,display_name,vimeo_video_url,status from videos limit 20";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			list = new ArrayList<Video>();
			while (rs.next()) {
				Video video = setVideos(rs);
				list.add(video);
				System.out.println("Videos" + list);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(MessageConstants.video_list);
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;

	}

	private Video setVideos(ResultSet rs) throws SQLException {
		Video videos = null;
		
		int videoId = rs.getInt("id");
		String videoName = rs.getString("name");
		String displayName = rs.getString("display_name");
		String vimeoVideoUrl = rs.getString("vimeo_video_url");
		boolean status = rs.getBoolean("status");
		videos = new Video();
		
		videos.setId(videoId);
		videos.setVideoName(videoName);
		videos.setDisplayName(displayName);
		videos.setVimeoVideoUrl(vimeoVideoUrl);
		videos.setStatus(status);
		return videos;
	}
	
	
	public List<Video> listActiveVideos( boolean status ) throws DBException {
		ResultSet rs = null;
		List<Video> list = null;
		
		try {
			con = ConnectionUtil.getConnection();
			String sql = "select id,name,display_name,vimeo_video_url,status from videos where status=?";
			pst = con.prepareStatement(sql);
			pst.setBoolean(1, status);
			rs = pst.executeQuery();
			
			list = new ArrayList<Video>();
			while (rs.next()) {
				Video video = setActiveVideos(rs);
				list.add(video);
				System.out.println("Videos" + list);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(MessageConstants.video_list);
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;

	}
	
	//List active or inactive videos
	
	private Video setActiveVideos(ResultSet rs) throws SQLException {
		Video videos = null;
		boolean status=rs.getBoolean("status");
		int videoId = rs.getInt("id");
		String videoName = rs.getString("name");
		String displayName = rs.getString("display_name");
		String vimeoVideoUrl = rs.getString("vimeo_video_url");
		videos = new Video();
		
		videos.setStatus(status);
		videos.setId(videoId);
		videos.setVideoName(videoName);
		videos.setDisplayName(displayName);
		videos.setVimeoVideoUrl(vimeoVideoUrl);
		return videos;
	}
	
	public void updateVideos(int request_id) throws DBException { 

		Connection con = ConnectionUtil.getConnection();
		String sql = "update videos set status=? where id=?";
		try {

			pst = con.prepareStatement(sql);
			pst.setInt(1, request_id);

			pst.executeUpdate();
			System.out.println("\nYour Request is Closed!!! ");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Unable to close request", e);
		} finally {
			ConnectionUtil.close(con, pst);
		}

	}
	
}
