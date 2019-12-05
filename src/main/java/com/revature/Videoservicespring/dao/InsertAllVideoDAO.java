package com.revature.Videoservicespring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.revature.Videoservicespring.exception.DBException;
import com.revature.Videoservicespring.model.ReferenceArtifact;
import com.revature.Videoservicespring.model.ReferenceUrl;
import com.revature.Videoservicespring.model.SampleProgram;
import com.revature.Videoservicespring.model.Video;
import com.revature.Videoservicespring.util.MessageConstants;

@Repository
public class InsertAllVideoDAO {

	@Autowired
	private DataSource datasource;
	Connection con = null;
	PreparedStatement pst = null;
	Boolean result = false;
	Savepoint newVideo = null;

	public boolean newVideo(Video video, ReferenceArtifact artifact, SampleProgram sampleProgram, ReferenceUrl url)
			throws SQLException, DBException {
		
		try {
			con = datasource.getConnection();
			con.setAutoCommit(false);
			newVideo = con.setSavepoint("SAVEPOINT");
			
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
			pst.close();

			String selectVideoId = "select last_insert_id()";
			pst = con.prepareStatement(selectVideoId);
			ResultSet rs = pst.executeQuery();

			int videoId = 0;
			if (rs.next()) {
				videoId = rs.getInt("last_insert_id()");
			}
			System.out.println("Selected Value: "+videoId);
			
			String refArtifact = "insert into artifacts(name,artifact,description,video_id)values(?,?,?,?)";
			pst = con.prepareStatement(refArtifact);
			pst.setString(1, artifact.getName());
			pst.setString(2, artifact.getArtifact());
			pst.setString(3, artifact.getDescription());
			pst.setInt(4, videoId);
			pst.executeUpdate();

			String sqlprogram = "insert into programs(name,artifact,description,video_id)values(?,?,?,?)";
			pst = con.prepareStatement(sqlprogram);
			pst.setString(1, sampleProgram.getName());
			pst.setString(2, sampleProgram.getArtifact());
			pst.setString(3, sampleProgram.getDescription());
			pst.setInt(4, videoId);
			pst.executeUpdate();

			String refUrl = "insert into urls(name,artifact,description,type,video_id)values(?,?,?,?,?)";
			pst = con.prepareStatement(refUrl);
			pst.setString(1, url.getName());
			pst.setString(2, url.getArtifact());
			pst.setString(3, url.getDescription());
			pst.setString(4, url.getType());
			pst.setInt(5, videoId);

			if (count == 1) {
				result = true;
			}
			con.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException(MessageConstants.video_exist);
		} finally {
			try {
				con.close();
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				con.rollback(newVideo);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
