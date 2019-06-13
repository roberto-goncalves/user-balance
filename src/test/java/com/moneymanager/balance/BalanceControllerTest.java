package com.moneymanager.balance;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.moneymanager.Application;
import com.moneymanager.i18n.Message;
import com.moneymanager.transactions.TransactionsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = Application.class)
public class BalanceControllerTest {

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

    @Autowired
    Message message;


    @Test
    public void testRequestRetrieveSuccess() throws Exception {
        this.mvc.perform(get("/api/balances/retrieve/11"))
                .andExpect(content().json("{'message':"+message.get("success")+",'data':{'userId':11,'balance':0}}"))
                .andDo(print());
    }

    @Test
    public void testRequestRetrieveFailure() throws Exception {
        this.mvc.perform(get("/api/balances/retrieve/17"))
                .andExpect(content().json("{'message':'"+message.get("failure.userNotFound")+"'}"))
                .andDo(print());
    }

}