package com.baciu;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.baciu.controller.TeacherController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeacherControllerTest {
	
	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mockMvc;

	@InjectMocks
	private TeacherController teacherController;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void testGetTeacher() throws Exception {
		String getTeacher = new StringBuilder()
				.append("/teachers")
				.append("/{id}")
				.toString();
		
		long id = 1;
		
		mockMvc.perform(get(getTeacher, id))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(content().json("{\"id\":1,\"name\":\"Adam\",\"surname\":\"Adamowski\",\"salary\":2500,\"lectures\":[{\"id\":1,\"classNumber\":211,\"date\":1511190316000}]}", true))
		.andDo(print())
		.andReturn();
		
	}
	
}
