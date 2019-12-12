package com.revature.Videoservicespring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;

import org.springframework.stereotype.Repository;
import org.springframework.dao.DataIntegrityViolationException;

import com.revature.Videoservicespring.dto.VideoDTO;
import com.revature.Videoservicespring.exception.DBException;
import com.revature.Videoservicespring.util.ConnectionUtil;
import com.revature.Videoservicespring.util.MessageConstants;

@Repository
public class InsertAllVideoDAO {

//	@Autowired
//	private DataSource datasource;
	Connection con = null;
	PreparedStatement pst = null;
	Boolean result = false;
	Savepoint newVideo = null;
	Savepoint deleteVideo=null;
	
	private ConnectionUtil connection;
	public InsertAllVideoDAO(ConnectionUtil connect) {
		this.connection=connect;
	}

	public boolean newVideo(VideoDTO videodto) throws SQLException, DBException {
		
		try {
			con = ConnectionUtil.getConnection();
			con.setAutoCommit(false);
			newVideo = con.setSavepoint("SAVEPOINT");
			
			String sql = "insert into videos(name, display_name, vimeo_video_url, tags, description, transcript, level_id, category_id)values(?,?,?,?,?,?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, videodto.getVideo().getVideoName());
			pst.setString(2, videodto.getVideo().getDisplayName());
			pst.setString(3, videodto.getVideo().getVimeoVideoUrl());
			pst.setString(4, videodto.getVideo().getTags());
			pst.setString(5, videodto.getVideo().getDescription());
			pst.setString(6, videodto.getVideo().getTranscript());
			pst.setInt(7, videodto.getVideo().getLevel_id());
			pst.setInt(8, videodto.getVideo().getCategory_id());
			int count = pst.executeUpdate();
			pst.close();

			String selectVideoId = "select last_insert_id()";
			pst = con.prepareStatement(selectVideoId);
			ResultSet rs = pst.executeQuery();

			int videoId = 0;
			if (rs.next()) {
				videoId = rs.getInt("last_insert_id()");
			}
			System.out.println("Selected Value: "+videoId);
			
			String refArtifact = "insert into video_artifacts(name,artifact,description,video_id)values(?,?,?,?)";
			pst = con.prepareStatement(refArtifact);
			pst.setString(1, videodto.getArtifact().getName());
			pst.setString(2, videodto.getArtifact().getArtifact());
			pst.setString(3, videodto.getArtifact().getDescription());
			pst.setInt(4, videoId);
			pst.executeUpdate();
			pst.close();
			
			String sqlprogram = "insert into video_programs(name,artifact,description,video_id)values(?,?,?,?)";
			pst = con.prepareStatement(sqlprogram);
			pst.setString(1, videodto.getSampleprogram().getName());
			pst.setString(2, videodto.getSampleprogram().getArtifact());
			pst.setString(3, videodto.getSampleprogram().getDescription());
			pst.setInt(4, videoId);
			pst.executeUpdate();
			pst.close();
			
			String refUrl = "insert into video_urls(name,url,description,type,video_id)values(?,?,?,?,?)";
			pst = con.prepareStatement(refUrl);
			pst.setString(1, videodto.getUrl().getName());
			pst.setString(2, videodto.getUrl().getArtifact());
			pst.setString(3, videodto.getUrl().getDescription());
			pst.setString(4, videodto.getUrl().getType());
			pst.setInt(5, videoId);
			pst.executeUpdate();
			pst.close();
			
			if (count == 1) {
				result = true;
			}
			con.commit();
			
		}
		catch (DataIntegrityViolationException e) {
			
	        System.out.println("Video name already exist");
	    }
		catch (SQLException e) {
			
			e.getMessage().contains("uk_videos");
			String message = e.getMessage();
			System.out.println(message);
			//e.printStackTrace();
			con.rollback(newVideo);
			throw new DataIntegrityViolationException(MessageConstants.video_exist);
		} finally {
			try {
				
				con.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public boolean deleteVideo(int videoId) throws DBException {
		
		try {
			con = ConnectionUtil.getConnection();
			con.setAutoCommit(false);
			deleteVideo = con.setSavepoint("SAVEPOINT");
			
		
			String refArtifact = "delete from video_artifacts where video_id=?";
			pst = con.prepareStatement(refArtifact);
			pst.setInt(1, videoId);
			int count = pst.executeUpdate();
			
			System.out.println("Selected Value: "+videoId);
			pst.close();
			
			String sqlprogram = "delete from video_programs where video_id=?";
			pst = con.prepareStatement(sqlprogram);
			pst.setInt(1, videoId);
			pst.executeUpdate();
			pst.close();
			
			String refUrl = "delete from video_urls where video_id=?";
			pst = con.prepareStatement(refUrl);
			pst.setInt(1, videoId);
			pst.executeUpdate();
			pst.close();
			
			String sql = "delete from videos where id=?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, videoId);
			pst.executeUpdate();
			pst.close();
			
			if (count == 1) {
				result = true;
			}
			con.commit();
		}

		catch (SQLException e) {
			
			e.printStackTrace();
			try {
				con.rollback(deleteVideo);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new DBException(MessageConstants.delete_video);
		} finally {
			try {
				
				con.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
