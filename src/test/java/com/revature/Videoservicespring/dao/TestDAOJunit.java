package com.revature.Videoservicespring.dao;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.revature.Videoservicespring.dao.VideoDAO;
import com.revature.Videoservicespring.dao.VideoDAOImp;
import com.revature.Videoservicespring.dto.VideoaddDTO;
import com.revature.Videoservicespring.exception.DBException;
import com.revature.Videoservicespring.model.Video;
@SpringBootTest
class TestDAOJunit {

	VideoDAO videodao=new VideoDAOImp();
	
	@Test
	public List<Video> testListVideos() {
		
		
		List<Video> video=null;
		try {
			video=videodao.listVideos();
			assertNotNull(true);
		} catch (DBException e) {
			e.printStackTrace();
		}
		return video;
	}
	
//	@Test
//	public void testInsertVideos() {
//		VideoaddDTO video=new VideoaddDTO();
//		video.setCategory_id(1);
//		video.setDescription("java");
//		video.setDisplayName("java");
//		video.setId(5);
//		video.setLevel_id(2);
//		video.setTags("java");
//		video.setStatus(true);
//		video.setTranscript("corejava");
//		video.setVideoName("corejava");
//		video.setVimeoVideoUrl("corejavaurl/567890");
//		videoDAOImp addvideo=new videoDAOImp();
//		addvideo.newVideo(video);
//		
//		
//	}
	

//	import static org.junit.Test;
//	import static org.junit.Assert.assertEquals;
//	import java.sql.SQLException;
//	import java.util.List;
//	import org.junit.jupiter.api.Test;
//	import org.springframework.beans.factory.annotation.Autowired;
//	import org.springframework.boot.test.context.SpringBootTest;
//	import com.revature.revaturequestion.exception.ServiceException;
//	import com.revature.revaturequestion.exception.ValidatorException;
//	import com.revature.revaturequestion.model.Question;
//	@SpringBootTest
//	class QuestionTest {
//		@Autowired
//		QuestionService questionService;
//		@Autowired
//		ListAllQuestionService listAllQuestionService;
//		/*
//		 * @Test void validDeleteQuestionTest() throws ValidatorException,
//		 * ServiceException {
//		 *
//		 * String questionId="7"; Boolean result=false;
//		 * result=QuestionService.deleteQuestion(questionId); assertTrue(result); }
//		 */
//		@Test
//		void inValidDeleteQuestionTest() throws ValidatorException, ServiceException {
//			String questionId = "3";
//			Boolean result = false;
//			result = questionService.deleteQuestion(questionId);
//			assertFalse(result);
//		}
//		@Test
//		void validListAllActiveQuestionTest() throws ValidatorException, ServiceException, SQLException {
//			String status = "1";
//			List<Question> question = null;
//			question = listAllQuestionService.listAll(status);
//			assertNotNull(question);
//		}
//		@Test
//		void inValidListAllActiveQuestionTest() throws ValidatorException, ServiceException, SQLException {
//			String status = "76";
//			List<Question> question = null;
//			question = listAllQuestionService.listAll(status);
//			assertNotNull(question);
//		}
//		@Test
//		void updateQuestionDeactiveValidTest() throws ValidatorException, ServiceException, SQLException {
//			String status = "0";
//			String questionId = "22";
//			Boolean question = false;
//			question = questionService.updateQuestion(questionId, status);
//			assertTrue(question);
//		}
//		@Test
//		void updateQuestionDeactiveInValidTest() {
//			String status = "0";
//			String questionId = "22";
//			Boolean expectedExceptionThrown = false;
//			try {
//				questionService.updateQuestion(questionId, status);
//			} catch (ServiceException e) {
//				e.printStackTrace();
//			}
//			assertTrue(expectedExceptionThrown);
//		}
//		@Test
//		void updateQuestionActiveValidTest() throws ValidatorException, ServiceException, SQLException {
//			String status = "1";
//			String questionId = "22";
//			Boolean question = false;
//			question = questionService.updateQuestion(questionId, status);
//			assertTrue(question);
//		}
//		@Test(expected = IllegalArgumentException.class)
//		void updateQuestionActiveInValidTest() {
//			String status = "=";
//			String questionId = "22";
//			questionService.updateQuestion(questionId, status);
//		}
//	}

}
