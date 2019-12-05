package com.revature.Videoservicespring.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.Videoservicespring.dto.VideoDTO;
import com.revature.Videoservicespring.exception.ServiceException;
import com.revature.Videoservicespring.model.Video;
import com.revature.Videoservicespring.service.VideoService;
import com.revature.Videoservicespring.util.Message;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

//@RestController
//@RequestMapping("/videos")
//public class VideoController {
//	@Autowired
//	private VideoService videoService;
//
//	@PostMapping("")
//	@ApiOperation(value = "Addvideos API")
//	@ApiResponses(value = { @ApiResponse(code = 200, message = "Video created", response=String.class),
//	@ApiResponse(code = 400, message = "Invalid details", response=Message.class) })
//	public ResponseEntity<?> newVideo(@RequestParam("videoName") String videoName, @RequestParam("displayName") String displayName, @RequestParam("tags") String tags, @RequestParam("vimeoVideoUrl") String vimeoVideoUrl, @RequestParam("description") String description, @RequestParam("transcript") String transcript, @RequestParam("level_id") int level_id, @RequestParam("category_id") int category_id){
//		
//		Message message = null;
//		String status = null;
//		String errorMessage = null;
//		boolean result;
//		try {
//			Video video = new Video();
//			video.setVideoName(videoName);
//			video.setDisplayName(displayName);
//			video.setTags(tags);
//			video.setVimeoVideoUrl(vimeoVideoUrl);
//			video.setDescription(description);
//			video.setTranscript(transcript);
//			video.setLevel_id(level_id);
//			video.setCategory_id(category_id);

			//result=videoService.addVideos(video);
			//if(result==true) {
//			status = "Request generated successfully";
//			}
//		} catch (Exception e) {
//			errorMessage = e.getMessage();
//			e.printStackTrace();
//		}
//
//		if (status != null) {
//
//			message = new Message(status);
//			return new ResponseEntity<>(message, HttpStatus.CREATED);
//		} else {
//			message = new Message(errorMessage);
//			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
//		}
	//}
//}

@RestController
@RequestMapping("videos")
public class VideoController {

	@Autowired
	private VideoService videoService;

 /**
 * This method is used to add a new video
 * takes object as parameters
 * returns the success or failure message
 * @throws SQLException 
 * @throws ValidatorException on input error
 * 
 */
	@PostMapping()
	@ApiOperation(value = "videoAPI")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Video created", response = String.class),
			@ApiResponse(code = 400, message = "Invalid video", response = Video.class) })

	public ResponseEntity<?> addCategory(@RequestBody VideoDTO videodto) throws SQLException {

		try {
			videoService.insertVideo(videodto);
			return new ResponseEntity<>(HttpStatus.CREATED);

		} catch (ServiceException e) {
			Message message = new Message(e.getMessage());
			return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
		}

	}
}
