///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.docusign.test;
//
//
//import com.google.gson.Gson;
//import com.google.gson.JsonObject;
//import java.io.IOException;
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.util.EntityUtils;
//
//
//public class AuthorizationCode {
//    
//    
//    
//    public static void main(String... args){
//        getAccessToken(authUrl, clientId,redirecUri )
//    }
//            
//    
//    
//    public static String getAccessToken(String code) throws ClientProtocolException, IOException {
//        HttpClient httpClient = HttpClients.createDefault();
//        HttpPost httpPost = new HttpPost(authUrl);
//
//        String json = new Gson().toJson(new DocuSignAccessToken.TokenRequest(clientId, clientSecret, redirectUri, code));
//
//        StringEntity entity = new StringEntity(json);
//        httpPost.setEntity(entity);
//        httpPost.setHeader("Content-Type", "application/json");
//
//        HttpResponse httpResponse = httpClient.execute(httpPost);
//        HttpEntity responseEntity = httpResponse.getEntity();
//
//        if (responseEntity != null) {
//            String responseString = EntityUtils.toString(responseEntity);
//            JsonObject jsonObject = new Gson().fromJson(responseString, JsonObject.class);
//            return jsonObject.get("access_token").getAsString();
//        } else {
//            System.err.println("Failed to retrieve access token.");
//            return null;
//        }
//    }
//}
