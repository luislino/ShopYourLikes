package com.sylconnexity.spring18;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
/**
 * Tests for the application.
 */

// Ensures the spring application context will load
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class Spring18ApplicationTests {

	@Test
	public void contextLoads() throws Exception{
	}
	@Autowired
	private MockMvc mockMVC;
	@Test
	public void userNameParamReturn() throws Exception {
		this.mockMVC.perform(get("/?name=John Smith")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("John Smith")));
	}
}
