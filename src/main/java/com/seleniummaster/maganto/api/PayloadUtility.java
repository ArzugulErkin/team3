package com.seleniummaster.maganto.api;

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

    //Post,Get and Put one category
    public static String getCategoryPayload(String categoryName){
        long timeStamp = System.currentTimeMillis();
        String payload= null;
        CategoryPayload categoryPayload = new CategoryPayload(categoryName+timeStamp);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            payload = objectMapper.writeValueAsString(categoryPayload);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return payload;
    }

    //Post one product
    public static String getProductPayload(int entityTypeId, int attributeSetId,String typeId,String sku){
        String payload= null;
        long timeStamp = System.currentTimeMillis();
        ProductPayload productPayload=new ProductPayload(entityTypeId,attributeSetId,typeId,sku+timeStamp);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            payload = objectMapper.writeValueAsString(productPayload);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return payload;

}
}
