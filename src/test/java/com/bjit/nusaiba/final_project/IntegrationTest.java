package com.bjit.nusaiba.final_project;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.jayway.jsonpath.JsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) 
class IntegrationTest {

	@Autowired
    private MockMvc mockMvc;
	
	@Test
    public void checkTrainees() throws Exception{
		MvcResult result =this.mockMvc.perform(post("/authenticate")
    			.content("{\"username\":\"saleehin@gmail.com\",\"password\":\"1234\"}")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful())
    			.andReturn();
		
		
		String token= JsonPath.read(result.getResponse().getContentAsString(), "$.accessToken");
		
		this.mockMvc.perform(get("/api/trainees?id=2").header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
	   	 .andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.name").value("Nipa Hawladar"));
    }
	
	@Test
    public void checkTrainers() throws Exception{
		MvcResult result =this.mockMvc.perform(post("/authenticate")
    			.content("{\"username\":\"saleehin@gmail.com\",\"password\":\"1234\"}")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful())
    			.andReturn();
		
		
		String token= JsonPath.read(result.getResponse().getContentAsString(), "$.accessToken");
		
		this.mockMvc.perform(get("/api/trainers?id=6").header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
	   	 .andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.name").value("Saleehin"));
    }
	
	
	@Test
    public void checkBatch() throws Exception{
		MvcResult result =this.mockMvc.perform(post("/authenticate")
    			.content("{\"username\":\"saleehin@gmail.com\",\"password\":\"1234\"}")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful())
    			.andReturn();
		
		
		String token= JsonPath.read(result.getResponse().getContentAsString(), "$.accessToken");
		
		this.mockMvc.perform(get("/api/batch?id=1").header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
	   	 .andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.name").value("Batch-01 Java"));
    }
	
	
	@Test
    public void checkCourse() throws Exception{
		MvcResult result =this.mockMvc.perform(post("/authenticate")
    			.content("{\"username\":\"saleehin@gmail.com\",\"password\":\"1234\"}")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful())
    			.andReturn();
		
		
		String token= JsonPath.read(result.getResponse().getContentAsString(), "$.accessToken");
		
		this.mockMvc.perform(get("/api/course?id=10").header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
	   	 .andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.name").value("Japanese Language"));
    }
	

	@Test
    public void checkAssignment() throws Exception{
		MvcResult result =this.mockMvc.perform(post("/authenticate")
    			.content("{\"username\":\"saleehin@gmail.com\",\"password\":\"1234\"}")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful())
    			.andReturn();
		
		
		String token= JsonPath.read(result.getResponse().getContentAsString(), "$.accessToken");
		
		this.mockMvc.perform(get("/api/assignment?id=3").header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
	   	 .andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.name").value("Hibernate"));
    }
	

	@Test
    public void checkAnswerCopy() throws Exception{
		MvcResult result =this.mockMvc.perform(post("/authenticate")
    			.content("{\"username\":\"saleehin@gmail.com\",\"password\":\"1234\"}")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful())
    			.andReturn();
		
		
		String token= JsonPath.read(result.getResponse().getContentAsString(), "$.accessToken");
		
		this.mockMvc.perform(get("/api/answer_copy?id=3").header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
	   	 .andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.grantedMark").value(10));
    }	
	
	
	@Test
    public void checkTrainingRecord() throws Exception{
		MvcResult result =this.mockMvc.perform(post("/authenticate")
    			.content("{\"username\":\"saleehin@gmail.com\",\"password\":\"1234\"}")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful())
    			.andReturn();
		
		
		String token= JsonPath.read(result.getResponse().getContentAsString(), "$.accessToken");
		
		this.mockMvc.perform(get("/api/record?id=1").header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
	   	 .andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.name").value("Java Batch 01 2022"));
    }	
	
	
	
	@Test
    public void checkTrainingSession() throws Exception{
		MvcResult result =this.mockMvc.perform(post("/authenticate")
    			.content("{\"username\":\"saleehin@gmail.com\",\"password\":\"1234\"}")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful())
    			.andReturn();
		
		
		String token= JsonPath.read(result.getResponse().getContentAsString(), "$.accessToken");
		
		this.mockMvc.perform(get("/api/session?id=3").header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
	   	 .andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.name").value("Hibernate"));
    }
	
}
