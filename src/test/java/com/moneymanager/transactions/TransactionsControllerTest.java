package com.moneymanager.transactions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.moneymanager.Application;
import com.moneymanager.i18n.Message;
import org.junit.Test;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = Application.class)
public class TransactionsControllerTest {

    Gson gson = new GsonBuilder().enableComplexMapKeySerialization()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
            .setLenient()
            .setPrettyPrinting().create();
    String pattern = "yyyyMMdd";
    DateFormat df = new SimpleDateFormat(pattern);

    @Autowired
    private MockMvc mvc;

    @Autowired
    Message message;

    @Test
    public void testRequestInsert() throws Exception {
        UserTransaction transaction = new UserTransaction(9, new BigDecimal(150));
        String json = gson.toJson(transaction);
        this.mvc.perform(post("/api/transaction/").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(content().json("{'message':'"+message.get("success")+"','data':{'userId':9,'amount':150}}"))
                .andDo(print());
    }

    @Test
    public void testRequestInsertLessThanZero() throws Exception {
        UserTransaction transaction = new UserTransaction(10, new BigDecimal(-200));
        String json = gson.toJson(transaction);
        this.mvc.perform(post("/api/transaction/").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(content().json("{'message':'"+message.get("failure.zeroBalance")+"', 'data':{'userId':10,'amount':-200}}"))
                .andDo(print());
    }

}