package com.moneymanager.balance;

import com.moneymanager.Application;
import com.moneymanager.balance.exception.NotEnoughBalanceException;
import com.moneymanager.balance.exception.UserNotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = Application.class)
public class BalanceServiceTest {

    @Autowired
    private BalanceService service;

    @Test
    public void testRetrieveSuccess(){
        UserBalance user = service.retrieve(1);
        Assert.assertEquals(1, (int)user.getUserId());
    }

    @Test(expected = UserNotFoundException.class)
    public void testRetrieveFailure(){
        UserBalance user = service.retrieve(40);
        Assert.assertNull(user);
    }

    @Test
    public void testPerformTransactionPositive(){
        BigDecimal balance = service.performTransaction(1, new BigDecimal(150));
        Assert.assertEquals(balance, new BigDecimal(150));
    }

    @Test
    public void testPerformTransactionNegative(){
        service.performTransaction(2, new BigDecimal(150));
        BigDecimal balance = service.performTransaction(2, new BigDecimal(-100));
        Assert.assertEquals(balance, new BigDecimal(50));
    }

    @Test(expected = NotEnoughBalanceException.class)
    public void testPerformTransactionNotEnoughBalanceException(){
        service.performTransaction(1, new BigDecimal(-1500));
    }

    @Test(expected = UserNotFoundException.class)
    public void testPerformTransactionUserNotFoundException(){
        service.performTransaction(50, new BigDecimal(-1500));
    }


}