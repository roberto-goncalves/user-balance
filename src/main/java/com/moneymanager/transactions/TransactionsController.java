package com.moneymanager.transactions;

import com.moneymanager.i18n.Message;
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

@RestController
@CrossOrigin
@RequestMapping(path="/api/transaction", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TransactionsController {

    @Autowired
    private TransactionsService transactionsService;

    @Autowired
    Message message;

    @RequestMapping(path="/", method= RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Object insert(@RequestBody UserTransaction request) {
        try {
        	this.transactionsService.insert(request);
        	return new ResponseBean<UserTransaction>(message.get("success"), request);
        } catch (MoneyManagerException ex) {
        	return new ResponseBean<UserTransaction>(ex.getMessage(), request);
        }
    }
}
