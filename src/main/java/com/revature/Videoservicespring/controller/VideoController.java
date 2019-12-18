package com.revature.Videoservicespring.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.revature.Videoservicespring.dto.VideoDTO;
import com.revature.Videoservicespring.exception.ServiceException;
import com.revature.Videoservicespring.model.Video;
import com.revature.Videoservicespring.service.DeleteVideoService;
import com.revature.Videoservicespring.service.InsertAllVideoService;
import com.revature.Videoservicespring.service.ListAllVideoService;
import com.revature.Videoservicespring.util.Message;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/videos")
public class VideoController {

	@Autowired
	private InsertAllVideoService videoService;
	@Autowired
	private ListAllVideoService listAllVideos;
	@Autowired
	private DeleteVideoService deletevideo;

	/**
	 * This method is used to add a new video 
	 * takes object as parameter returns the success or failure message
	 * 
	 * @throws SQLException
	 * @throws ServiceException on input error
	 * 
	 */
	@PostMapping()
	@ApiOperation(value = "videoAPI")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Video created", response = String.class),
			@ApiResponse(code = 400, message = "Invalid video", response = Video.class) })

	public ResponseEntity<?> addVideos(@RequestBody VideoDTO videodto) throws SQLException {

		try {
			videoService.insertVideo(videodto);
			return new ResponseEntity<>(HttpStatus.OK);

		} catch (ServiceException e) {
			Message message = new Message(e.getMessage());
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}

	}
	
	@GetMapping("/list")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Video> listVideos() throws ServiceException, SQLException {
		List<Video> viewResponse =listAllVideos.listAll();
		return viewResponse;
		
	}
	
	@GetMapping()
	@ResponseStatus(code = HttpStatus.OK)
	public List<Video> listActiveVideos(@RequestParam("status") boolean status) throws ServiceException, SQLException {
		List<Video> viewResponse =listAllVideos.listActiveVideos(status);
		return viewResponse;
		
	}
	
	@DeleteMapping("{id}")
	@ApiOperation(value = "videoAPI")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Video deleted", response = String.class),
			@ApiResponse(code = 400, message = "Invalid Video", response = Video.class) })
	
	public ResponseEntity<?> deleteByVideo(@PathVariable("id") Integer videoId) throws ServiceException {

		deletevideo.deleteVideo(videoId);
		return new ResponseEntity<>("video deleted",HttpStatus.OK);

	}
}
