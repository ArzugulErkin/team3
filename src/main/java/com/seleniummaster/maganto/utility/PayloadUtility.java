package com.seleniummaster.maganto.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PayloadUtility {

    public static String getCustomerGroupPayload(String customerGroupName){
        long timeStamp = System.currentTimeMillis();
        String payload= null;
        CustomerGroupPayload customerGroupPayload = new CustomerGroupPayload(customerGroupName+timeStamp);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            payload = objectMapper.writeValueAsString(customerGroupPayload);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return payload;
    }




}