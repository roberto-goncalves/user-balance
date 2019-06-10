package com.user.balance;

import org.springframework.stereotype.Service;
import sun.awt.image.BufferedImageGraphicsConfig;

import java.math.BigDecimal;
import java.util.HashMap;

@Service
public class RequestHelper {

    private HashMap<Integer, BigDecimal> userMap = new HashMap<>();

    public Output insert(UserTransaction request){
        if(userMap.containsKey(request.getUserId())){
            BigDecimal balance = userMap.get(request.getUserId());
            if(checkBalance(balance, request.getAmount())){
                userMap.put(request.getUserId(), addToBalance(balance, request.getAmount()));
                return new Output(Message.SUCCESS, new UserTransaction(request.getUserId(), userMap.get(request.getUserId())));
            }else{
                return new Output(Message.FAILURE_ZERO, null);
            }
        }else{
            if(checkBalance(BigDecimal.ZERO, request.getAmount())){
                userMap.put(request.getUserId(), request.getAmount());
                return new Output(Message.SUCCESS, request);
            }else{
                return new Output(Message.FAILURE_ZERO, null);
            }
        }
    }

    public Output retrieve(Integer userID){
        if(userMap.containsKey(userID)){
            return new Output(Message.SUCCESS, new UserTransaction(userID, userMap.get(userID)));
        }else{
            return new Output(Message.FAILURE_USER, null);
        }
    }

    public BigDecimal addToBalance(BigDecimal balance, BigDecimal value){
        return balance.add(value);
    }

    public boolean checkBalance(BigDecimal balance ,BigDecimal value){
        if(addToBalance(balance, value).compareTo(BigDecimal.ZERO) >= 0){
            return true;
        }else{
            return false;
        }
    }
}
