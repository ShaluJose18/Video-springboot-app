package com.revature.Videoservicespring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.Videoservicespring.exception.DBException;
import com.revature.Videoservicespring.model.Video;
import com.revature.Videoservicespring.util.MessageConstants;

@Repository
public class VideoDAOImp implements VideoDAO {
	@Autowired
	private DataSource datasource;
	Connection con = null;
	PreparedStatement pst = null;
	Boolean result = false;

	public Boolean insertVideo(Video video) throws DBException, SQLException {
		
		try {
			con = datasource.getConnection();
			String sql = "insert into videos(name, display_name, vimeo_video_url, tags, description, transcript, level_id, category_id)values(?,?,?,?,?,?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, video.getVideoName());
			pst.setString(2, video.getDisplayName());
			pst.setString(3, video.getVimeoVideoUrl());
			pst.setString(4, video.getTags());
			pst.setString(5, video.getDescription());
			pst.setString(6, video.getTranscript());
			pst.setInt(7, video.getLevel_id());
			pst.setInt(8, video.getCategory_id());

			int count = pst.executeUpdate();
			if (count == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(MessageConstants.video_exist);
		} finally {
			pst.close();
			con.close();
		}
		return result;
	}

	// Select All videos from Videos

	public List<Video> listVideos() throws DBException {
		ResultSet rs = null;
		List<Video> list = null;
		
		try {
			con = datasource.getConnection();
			String sql = "select id,name,display_name,vimeo_video_url,tags from videos limit 20";
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
		String tags = rs.getString("tags");
		videos = new Video();
		
		videos.setId(videoId);
		videos.setVideoName(videoName);
		videos.setDisplayName(displayName);
		videos.setVimeoVideoUrl(vimeoVideoUrl);
		videos.setTags(tags);
		return videos;
	}
	
	
}
