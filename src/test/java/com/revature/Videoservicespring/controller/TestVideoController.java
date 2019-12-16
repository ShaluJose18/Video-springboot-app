package com.revature.Videoservicespring.controller;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.revature.Videoservicespring.model.Video;
import com.revature.Videoservicespring.service.ListAllVideoService;

@RunWith(MockitoJUnitRunner.class)
public class TestVideoController {

	@Rule
	  public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("RESTDOC");
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private VideoController videoController;
	
	@Mock
	private ListAllVideoService listAllVideos;
	
	@Before
	public void setUp() {
		
		mockMvc=MockMvcBuilders.standaloneSetup(videoController).apply(documentationConfiguration(this.restDocumentation).uris()
				.withScheme("https").withHost("revature.com").withPort(443)).build();
		
	}
	
	@Test
	public void testListAll() throws Exception {
		List<Video> list=new ArrayList<Video>();
		when(listAllVideos.listAll()).thenReturn(list);
		
		this.mockMvc.perform(get("/videos/list"))
		.andExpect(status().isOk())
		.andExpect(content().contentType("application/json"))
		.andDo(document("videos"));
	}
	
	@Test
	public void testActiveVideos() throws Exception {
		List<Video> list=new ArrayList<Video>();
		when(listAllVideos.listActiveVideos(Mockito.anyBoolean())).thenReturn(list);
		
		this.mockMvc.perform(get("/videos/?Status=true"))
		.andExpect(status().isOk())
		.andExpect(content().contentType("application/json"))
		.andDo(document("videos"));
	}

}
