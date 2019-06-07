package com.user.balance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class RequestController {

    @Autowired
    private RequestHelper helper;

    @RequestMapping(path="/insert", method= RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Object insert(@RequestBody UserTransaction request) {
        return this.helper.insert(request);
    }

    @RequestMapping(path="/retrieve", method= RequestMethod.GET)
    @ResponseStatus(HttpStatus.FOUND)
    public Object retrieve(@RequestBody Integer userID) {
        return this.helper.retrieve(userID);
    }
}
