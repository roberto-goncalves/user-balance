package com.moneymanager.balance;

import com.moneymanager.i18n.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.moneymanager.beans.ResponseBean;
import com.moneymanager.exception.MoneyManagerException;

@RestController
@CrossOrigin
@RequestMapping(path="/api/balances", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class BalanceController {

    @Autowired
    private BalanceService balanceService;

    @Autowired
    Message messages;

    @RequestMapping(path="/retrieve/{userID}", method= RequestMethod.GET)
    @ResponseStatus(HttpStatus.FOUND)
    public Object retrieve(@PathVariable(value="userID") Integer userID) {
    	try {
        	final UserBalance balance = this.balanceService.retrieve(userID);
        	return new ResponseBean<UserBalance>(messages.get("success"), balance);
        } catch (MoneyManagerException ex) {
        	return new ResponseBean<Integer>(ex.getMessage(), userID);
        }
    }
}
