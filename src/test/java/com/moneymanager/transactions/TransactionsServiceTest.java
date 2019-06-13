package com.moneymanager.transactions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.moneymanager.Application;
import com.moneymanager.balance.exception.NotEnoughBalanceException;
import com.moneymanager.balance.exception.UserNotFoundException;
import com.moneymanager.i18n.Message;
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

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = Application.class)
public class TransactionsServiceTest {

    Gson gson = new GsonBuilder().enableComplexMapKeySerialization()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
            .setLenient()
            .setPrettyPrinting().create();
    String pattern = "yyyyMMdd";
    DateFormat df = new SimpleDateFormat(pattern);

    @Autowired
    private MockMvc mvc;

    @Autowired
    private TransactionsService service;


    @Test
    public void testInsertServiceSuccess(){
        UserTransaction transaction = new UserTransaction(9, new BigDecimal(150));
        Assert.assertEquals(service.insert(transaction), new BigDecimal(150));
    }

    @Test(expected = UserNotFoundException.class)
    public void testInsertUserNotFound(){
        UserTransaction transaction = new UserTransaction(900, new BigDecimal(150));
        service.insert(transaction);
    }

    @Test(expected = NotEnoughBalanceException.class)
    public void testInsertNotEnoughBalance(){
        UserTransaction transaction = new UserTransaction(1, new BigDecimal(-150));
        service.insert(transaction);
    }

}