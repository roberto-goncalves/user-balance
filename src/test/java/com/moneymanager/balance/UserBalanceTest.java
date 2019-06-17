package com.moneymanager.balance;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.moneymanager.Application;
import com.moneymanager.i18n.Message;
import com.moneymanager.transactions.TransactionsService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = Application.class)
public class UserBalanceTest {

    UserBalance ub = new UserBalance(1);
    @Test
    public void getUserId() {
        Assert.assertEquals(ub.getUserId(), Integer.valueOf(1));
    }

    @Test
    public void getBalance() {
        ub.setBalance(new BigDecimal(100));
        Assert.assertEquals(ub.getBalance(), new BigDecimal(100));
    }

    @Test
    public void setBalance() {
        ub.setBalance(new BigDecimal(200));
        Assert.assertEquals(ub.getBalance(), new BigDecimal(200));
    }
}