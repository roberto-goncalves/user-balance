package com.user.balance;

import org.springframework.stereotype.Service;
import sun.awt.image.BufferedImageGraphicsConfig;

import java.math.BigDecimal;
import java.util.HashMap;

@Service
public class RequestHelper {

    private HashMap<Integer, BigDecimal> userMap = new HashMap<>();

    public Object insert(UserTransaction request){
        if(userMap.containsKey(request.getUserId())){
            BigDecimal balance = userMap.get(request.getUserId());
            if(checkBalance(balance, request.getAmount())){
                userMap.put(request.getUserId(), addToBalance(balance, request.getAmount()));
            }else{
                //não é possivel inserir pq o saldo da menor que 0
            }
        }else{
            userMap.put(request.getUserId(), request.getAmount());
        }
        return userMap;
    }

    public BigDecimal addToBalance(BigDecimal balance, BigDecimal value){
        return balance.add(value);
    }

    public boolean checkBalance(BigDecimal balance ,BigDecimal value){
        if(balance.compareTo(BigDecimal.ZERO) > 0){
            if(addToBalance(balance, value).compareTo(BigDecimal.ZERO) > 0){
                return true;
            }else{
                return false;
            }
        }else{
            if(value.compareTo(BigDecimal.ZERO) > 0){
                return true;
            }else{
                return false;
            }
        }
    }
}
