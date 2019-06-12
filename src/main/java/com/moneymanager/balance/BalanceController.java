package com.moneymanager.balance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.moneymanager.beans.ResponseBean;
import com.moneymanager.exception.MoneyManagerException;
import com.moneymanager.i18n.Messages;

@RestController
@CrossOrigin
@RequestMapping(path="/api/balances", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class BalanceController {

    @Autowired
    private BalanceService balanceService;

    @RequestMapping(path="/retrieve", method= RequestMethod.GET)
    @ResponseStatus(HttpStatus.FOUND)
    public Object retrieve(@RequestBody Integer userID) {
    	try {
        	final UserBalance balance = this.balanceService.retrieve(userID);
        	return new ResponseBean<UserBalance>(Messages.SUCCESS, balance);
        } catch (MoneyManagerException ex) {
        	return new ResponseBean<Integer>(ex.getMessage(), userID);
        }
    }
}
