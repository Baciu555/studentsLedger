package com.baciu;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeacherControllerTest {
	
	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mockMvc;
	
//	private HttpMessageConverter mappingJackson2HttpMessageConverter;
//	
//	@Autowired
//    void setConverters(HttpMessageConverter<?>[] converters) {
//
//        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
//            .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
//            .findAny()
//            .orElse(null);
//
//        assertNotNull("the JSON message converter must not be null",
//                this.mappingJackson2HttpMessageConverter);
//    }
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void testGetTeacher() throws Exception {
		long id = 1;
		
		mockMvc.perform(get("/teachers/" + id))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$.id", is(1)))
		.andExpect(jsonPath("$.name", is("Adam")))
		.andExpect(jsonPath("$.surname", is("Adamowski")))
		.andExpect(jsonPath("$.salary", is(2500.00)));
		
	}
	
	@Test
	public void testGetAllTeachers() throws Exception {
		mockMvc.perform(get("/teachers"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$", hasSize(2)))
		.andExpect(jsonPath("$[0].id", is(1)))
		.andExpect(jsonPath("$[0].name", is("Adam")))
		.andExpect(jsonPath("$[0].surname", is("Adamowski")))
		.andExpect(jsonPath("$[0].salary", is(2500.00)))
		.andExpect(jsonPath("$[1].id", is(3)))
		.andExpect(jsonPath("$[1].name", is("Ewa")))
		.andExpect(jsonPath("$[1].surname", is("Wisniowska")))
		.andExpect(jsonPath("$[1].salary", is(3400.5)));
	}
	
//	@Test
//	public void testAddTeacher() throws Exception {
//		Teacher teacher = new Teacher();
//		teacher.setName("Wojtek");
//		teacher.setSurname("Bac");
//		teacher.setSalary(2800.50);
//		String teacherJson = json(teacher);
//		
//		mockMvc.perform(post("/teachers")
//		.contentType(MediaType.APPLICATION_JSON_UTF8)
//		.content(teacherJson))
//		.andExpect(status().isCreated());
//	}
//	
//	protected String json(Object o) throws IOException {
//        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
//        this.mappingJackson2HttpMessageConverter.write(
//                o, MediaType.APPLICATION_JSON_UTF8, mockHttpOutputMessage);
//        return mockHttpOutputMessage.getBodyAsString();
//    }
	
}
