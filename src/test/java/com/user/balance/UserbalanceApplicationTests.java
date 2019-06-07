package com.user.balance;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserbalanceApplicationTests {

	 Gson gson = new GsonBuilder().enableComplexMapKeySerialization()
			.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
			.setLenient()
			.setPrettyPrinting().create();
	String pattern = "yyyyMMdd";
	DateFormat df = new SimpleDateFormat(pattern);

	@Autowired
	private MockMvc mvc;

	@Test
	public void testInsert() throws Exception {
		UserTransaction transaction = new UserTransaction(1, new BigDecimal(150));
		String json = gson.toJson(transaction);
		this.mvc.perform(post("/insert").contentType(MediaType.APPLICATION_JSON).content(json))
				//.andExpect(content().json("{'approved':false,'newlimit':500.0,'deniedReasons':['Transactions amount is higher than Account limit']}"))
				.andDo(print());
	}

	@Test
	public void testInsertLessThanZero() throws Exception {
		UserTransaction transaction = new UserTransaction(1, new BigDecimal(-200));
		String json = gson.toJson(transaction);
		this.mvc.perform(post("/insert").contentType(MediaType.APPLICATION_JSON).content(json))
				//.andExpect(content().json("{'approved':false,'newlimit':500.0,'deniedReasons':['Transactions amount is higher than Account limit']}"))
				.andDo(print());
	}

	@Test
	public void testRetrieve() throws Exception {
		String json = gson.toJson(1);
		this.mvc.perform(get("/retrieve").contentType(MediaType.APPLICATION_JSON).content(json))
				//.andExpect(content().json("{'approved':false,'newlimit':500.0,'deniedReasons':['Transactions amount is higher than Account limit']}"))
				.andDo(print());
	}

}
