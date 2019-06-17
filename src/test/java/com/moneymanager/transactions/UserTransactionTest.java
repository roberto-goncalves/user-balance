package com.moneymanager.transactions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.moneymanager.Application;
import com.moneymanager.i18n.Message;
import org.junit.Assert;
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
public class UserTransactionTest {

    UserTransaction ut = new UserTransaction(1, new BigDecimal(100));

    @Test
    public void getUserId() {
        Assert.assertEquals(ut.getUserId(), Integer.valueOf(1));
    }

    @Test
    public void getAmount() {
        Assert.assertEquals(ut.getAmount(), new BigDecimal(100));
    }
}