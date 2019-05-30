package com.user.balance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

public class RequestRouter {

    @Autowired
    private RequestHelper helper;

    @RequestMapping(path="/insert", method= RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Object insert(@RequestBody UserInsert request) {
        return this.helper.insert(request);
    }
}
