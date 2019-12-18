package com.revature.Videoservicespring.controller;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;

import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.snippet.Attributes.key;

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
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.Videoservicespring.dto.VideoDTO;
import com.revature.Videoservicespring.model.ReferenceArtifact;
import com.revature.Videoservicespring.model.ReferenceUrl;
import com.revature.Videoservicespring.model.SampleProgram;
import com.revature.Videoservicespring.model.Video;
import com.revature.Videoservicespring.service.DeleteVideoService;
import com.revature.Videoservicespring.service.InsertAllVideoService;
import com.revature.Videoservicespring.service.ListAllVideoService;

@RunWith(MockitoJUnitRunner.class)
public class TestVideoController {

	@Rule
	public final JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");

	private MockMvc mockMvc;
	private ObjectMapper objectmapper;

	@InjectMocks
	private VideoController videoController;

	@Mock
	private ListAllVideoService listAllVideos;

	@Mock
	private InsertAllVideoService insertVideo;

	@Mock
	private DeleteVideoService deleteVideo;

	@Before
	public void setUp() {

		mockMvc = MockMvcBuilders.standaloneSetup(videoController)
				.apply(documentationConfiguration(this.restDocumentation).uris().withScheme("http")
						.withHost("localhost").withPort(9000))
				.build();

		objectmapper = new ObjectMapper();
	}

	@Test
	public void testListAll() throws Exception {
		List<Video> list = new ArrayList<Video>();
		Video video = new Video();
		video.setId(1);
		video.setVideoName("java");
		video.setDisplayName("Java");
		video.setTags("java");
		video.setDescription("core java");
		video.setTranscript("java345678");
		video.setVimeoVideoUrl("vimeo/url/3456789");
		video.setStatus(true);
		video.setCategory_id(1);
		video.setLevel_id(2);
		list.add(video);
		when(listAllVideos.listAll()).thenReturn(list);

		this.mockMvc.perform(get("/videos/list")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andDo(document("videos/list", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())));
	}

	@Test
	public void testActiveVideos() throws Exception {
		List<Video> list = new ArrayList<Video>();
		when(listAllVideos.listActiveVideos(Mockito.anyBoolean())).thenReturn(list);

		this.mockMvc.perform(get("/videos?status=true")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andDo(document("videos/status", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())));
		
	}

	@Test
	public void testInsertVideos() throws Exception {

		VideoDTO videodto = new VideoDTO();

		Video video = new Video();
		video.setId(13);
		video.setVideoName("core java");
		video.setDisplayName("java");
		video.setVimeoVideoUrl("vimeo/url/45674328");
		video.setTags("java");
		video.setDescription("corejava");
		video.setTranscript("video563445678");
		video.setLevel_id(1);
		video.setCategory_id(2);
		video.setStatus(true);
		videodto.setVideo(video);

		ReferenceArtifact artifact = new ReferenceArtifact();
		artifact.setId(7);
		artifact.setName("corejava");
		artifact.setArtifact("artifact1");
		artifact.setDescription("javavideo");
		artifact.setVideo_id(13);
		videodto.setArtifact(artifact);

		SampleProgram pgm = new SampleProgram();
		pgm.setId(7);
		pgm.setName("corejava");
		pgm.setArtifact("program1");
		pgm.setDescription("sampleprogram");
		pgm.setVideo_id(13);
		videodto.setSampleprogram(pgm);

		ReferenceUrl url = new ReferenceUrl();
		url.setId(7);
		url.setName("corejava");
		url.setArtifact("url1");
		url.setDescription("urldescription");
		url.setType("other");
		url.setVideo_id(13);
		videodto.setUrl(url);

		String videoJson = objectmapper.writeValueAsString(videodto);
		when(insertVideo.insertVideo(videodto)).thenReturn(true);
		this.mockMvc.perform(post("/videos").contentType(MediaType.APPLICATION_JSON).content(videoJson))
				
				.andDo(print())
				.andDo(document("videos/new", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()),

						requestFields(
								fieldWithPath("video.id").description("id of video")
										.attributes(key("required").value(true)),
								fieldWithPath("video.videoName").description("Name of the video")
										.attributes(key("required").value(true)),
								fieldWithPath("video.displayName").description("displayName of the video")
										.attributes(key("required").value(true)),
								fieldWithPath("video.tags").description("tag name of the video")
										.attributes(key("required").value(true)),
								fieldWithPath("video.vimeoVideoUrl").description("url of the video")
										.attributes(key("required").value(true)),
								fieldWithPath("video.description").description("description of the video")
										.attributes(key("required").value(true)),
								fieldWithPath("video.transcript").description("transcript of the video")
										.attributes(key("required").value(true)),
								fieldWithPath("video.level_id").description("level of the video")
										.attributes(key("required").value(true)),
								fieldWithPath("video.category_id").description("Category of the video")
										.attributes(key("required").value(true)),
								fieldWithPath("video.status").description("status of the video")
										.attributes(key("required").value(true)),

								fieldWithPath("artifact.id").description("id of the artifact")
										.attributes(key("required").value(true)),
								fieldWithPath("artifact.name").description("Name of the artifact")
										.attributes(key("required").value(true)),
								fieldWithPath("artifact.artifact").description("reference artifact")
										.attributes(key("required").value(true)),
								fieldWithPath("artifact.description").description("description of the artifact")
										.attributes(key("required").value(true)),
								fieldWithPath("artifact.video_id").description("id of the video")
										.attributes(key("required").value(true)),

								fieldWithPath("sampleprogram.id").description("id of the artifact")
										.attributes(key("required").value(true)),
								fieldWithPath("sampleprogram.name").description("name of the program")
										.attributes(key("required").value(true)),
								fieldWithPath("sampleprogram.artifact").description("sample program document")
										.attributes(key("required").value(true)),
								fieldWithPath("sampleprogram.description").description("description of the artifact")
										.attributes(key("required").value(true)),
								fieldWithPath("sampleprogram.video_id").description("id of the video")
										.attributes(key("required").value(true)),

								fieldWithPath("url.id").description("id of the url")
										.attributes(key("required").value(true)),
								fieldWithPath("url.name").description("name of the url")
										.attributes(key("required").value(true)),
								fieldWithPath("url.artifact").description("reference url")
										.attributes(key("required").value(true)),
								fieldWithPath("url.description").description("description of the url")
										.attributes(key("required").value(true)),
								fieldWithPath("url.type").description("type of the url")
										.attributes(key("required").value(true)),
								fieldWithPath("url.video_id").description("id of the video")
										.attributes(key("required").value(true)))));
	}

	@Test
	public void testDeleteVideos() throws Exception {

		when(deleteVideo.deleteVideo(Mockito.anyInt())).thenReturn(true);
		this.mockMvc.perform(delete("/videos/5")).andExpect(status().isOk())
				.andExpect(content().string("video deleted")).andDo(print())
				.andDo(document("videos/delete", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())));
						 parameterWithName("videoId")
			                .description("delete video based on videoId").attributes(key("required").value(true));
	}

}
