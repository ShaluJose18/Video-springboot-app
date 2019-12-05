package com.revature.Videoservicespring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
		Boolean result=false;
		public Boolean insertVideo(Video video) throws DBException, SQLException {
			try {
				con=datasource.getConnection();
				String sql="insert into videos(name, display_name, vimeo_video_url, tags, description, transcript, level_id, category_id)values(?,?,?,?,?,?,?,?)";
				pst = con.prepareStatement(sql);
				pst.setString(1, video.getVideoName());
				pst.setString(2,video.getDisplayName());
				pst.setString(3, video.getVimeoVideoUrl());
				pst.setString(4, video.getTags());
				pst.setString(5,video.getDescription());
				pst.setString(6,video.getTranscript());
				pst.setInt(7, video.getLevel_id());
				pst.setInt(8, video.getCategory_id());
				
				pst.executeUpdate();
				if(pst.executeUpdate()==1)
				{
					result=true;	
				}
		}catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(MessageConstants.video_exist);
		}
		finally {
			pst.close();
			con.close();
		}
			System.out.println("Called DAO");
		return result;	
	}
		
	}
