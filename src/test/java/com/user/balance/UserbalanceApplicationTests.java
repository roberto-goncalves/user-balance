package com.user.balance;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.moneymanager.transactions.TransactionsService;
import com.moneymanager.transactions.UserTransaction;

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

	@Autowired
	private TransactionsService helper;

	@Test
	public void testRequestInsert() throws Exception {
		UserTransaction transaction = new UserTransaction(9, new BigDecimal(150));
		String json = gson.toJson(transaction);
		this.mvc.perform(post("/insert").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(content().json("{'message':'Success.','userTransaction':{'userId':9,'amount':150}}"))
				.andDo(print());
	}

	@Test
	public void testRequestInsertLessThanZero() throws Exception {
		UserTransaction transaction = new UserTransaction(10, new BigDecimal(-200));
		String json = gson.toJson(transaction);
		this.mvc.perform(post("/insert").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(content().json("{'message':'Failure, balance less than zero.'}"))
				.andDo(print());
	}

	@Test
	public void testRequestRetrieveSuccess() throws Exception {
		UserTransaction transaction = new UserTransaction(11, new BigDecimal(150));
		String json = gson.toJson(transaction);
		this.mvc.perform(post("/insert").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(content().json("{'message':'Success.','userTransaction':{'userId':11,'amount':150}}"))
				.andDo(print());
		json = gson.toJson(11);
		this.mvc.perform(get("/retrieve").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(content().json("{'message':'Success.','userTransaction':{'userId':11,'amount':150}}"))
				.andDo(print());
	}

	@Test
	public void testRequestRetrieveFailure() throws Exception {
		String json = gson.toJson(12);
		this.mvc.perform(get("/retrieve").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(content().json("{'message':'Failure, user does not exist.'}"))
				.andDo(print());
	}

//	@Test
//	public void performInsertMethod() throws Exception {
//		UserTransaction transaction = new UserTransaction(2, new BigDecimal(150));
//		ResponseBean output = helper.insert(transaction);
//		Assert.assertEquals(output.getUserTransaction().getAmount(), new BigDecimal(150));
//		Assert.assertEquals(output.getMessage(), Messages.SUCCESS);
//	}
//
//	@Test
//	public void performInsertSum() throws Exception {
//		UserTransaction transaction = new UserTransaction(3, new BigDecimal(150));
//		helper.insert(transaction);
//		ResponseBean output = helper.insert(transaction);
//		Assert.assertEquals(output.getUserTransaction().getAmount(), new BigDecimal(300));
//		Assert.assertEquals(output.getMessage(), Messages.SUCCESS);
//	}
//
//	@Test
//	public void performInsertNegative() throws Exception {
//		UserTransaction transaction = new UserTransaction(4, new BigDecimal(150));
//		helper.insert(transaction);
//		transaction.setAmount(new BigDecimal(-200));
//		ResponseBean output = helper.insert(transaction);
//		Assert.assertEquals(output.getUserTransaction(), null);
//		Assert.assertEquals(output.getMessage(), Messages.FAILURE_ZERO);
//	}
//
//	@Test
//	public void performInsertNegativeFirstTransaction() throws Exception {
//		UserTransaction transaction = new UserTransaction(5, new BigDecimal(-150));
//		ResponseBean output = helper.insert(transaction);
//		Assert.assertEquals(output.getUserTransaction(), null);
//		Assert.assertEquals(output.getMessage(), Messages.FAILURE_ZERO);
//	}
//
//	@Test
//	public void performRetrieveSuccess() throws Exception {
//		UserTransaction transaction = new UserTransaction(6, new BigDecimal(150));
//		helper.insert(transaction);
//		ResponseBean output = helper.retrieve(6);
//		Assert.assertEquals(output.getMessage(), Messages.SUCCESS);
//		Assert.assertEquals(output.getUserTransaction().getAmount(), transaction.getAmount());
//	}
//
//	@Test
//	public void performRetrieveFailure() throws Exception {
//		ResponseBean output = helper.retrieve(8);
//		Assert.assertEquals(output.getMessage(), Messages.FAILURE_USER);
//		Assert.assertEquals(output.getUserTransaction(), null);
//	}
//
//	@Test
//	public void performAddBalance() throws Exception {
//		Assert.assertEquals(helper.addToBalance(BigDecimal.ZERO, new BigDecimal(250)), new BigDecimal(250));
//		Assert.assertEquals(helper.addToBalance(BigDecimal.ZERO, new BigDecimal(-250)), new BigDecimal(-250));
//	}
//
//	@Test
//	public void performCheckBalance() throws Exception {
//		Assert.assertEquals(helper.checkBalance(BigDecimal.ZERO, new BigDecimal(250)), true);
//		Assert.assertEquals(helper.checkBalance(BigDecimal.ZERO, new BigDecimal(-250)), false);
//	}


}
