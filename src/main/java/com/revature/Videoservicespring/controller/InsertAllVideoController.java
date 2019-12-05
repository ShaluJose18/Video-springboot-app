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
import com.revature.Videoservicespring.service.InsertAllVideoService;
import com.revature.Videoservicespring.util.Message;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("Allvideos")
public class InsertAllVideoController {

	@Autowired
	private InsertAllVideoService videoService;

	/**
	 * This method is used to add a new video takes object as parameters returns the
	 * success or failure message
	 * 
	 * @throws SQLException
	 * @throws ValidatorException on input error
	 * 
	 */
	@PostMapping()
	@ApiOperation(value = "AllvideoAPI")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Video created", response = String.class),
			@ApiResponse(code = 400, message = "Invalid video", response = Video.class) })

	public ResponseEntity<?> addCategory(@RequestBody VideoDTO videodto) throws SQLException {

		try {
			videoService.insertVideo(videodto);
			return new ResponseEntity<>(HttpStatus.CREATED);

		} catch (ServiceException e) {
			Message message = new Message(e.getMessage());
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
		}

	}
}
