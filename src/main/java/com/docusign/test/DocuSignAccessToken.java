
package com.docusign.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

 
public class DocuSignAccessToken {
    
        final static String clientId = "7f0d3a73-74a3-4a8d-89ee-447dbb008014";
        final static String clientSecret = "83f2d737-8e30-4129-af97-78e0ca84a988";
        final static String redirectUri = "https://www.example.com/callback";
        final static String code="code";
        final static String scope="signature";                
        final static String baseUrl="https://account-d.docusign.com/oauth/auth";
        private static final String tokenUrl = "https://account-d.docusign.com/oauth/token";
    
            
        
       
       
    public static void main(String[] args) throws IOException{
                   
        String authorizationCode="eyJ0eXAiOiJNVCIsImFsZyI6IlJTMjU2Iiwia2lkIjoiNjgxODVmZjEtNGU1MS00Y2U5LWFmMWMtNjg5ODEyMjAzMzE3In0.AQoAAAABAAYABwAAKhDsyzLcSAgAALaWM8wy3EgCAOhSM8F_ljNMtBnriWXBbJEVAAEAAAAYAAEAAAAFAAAADQAkAAAAN2YwZDNhNzMtNzRhMy00YThkLTg5ZWUtNDQ3ZGJiMDA4MDE0IgAkAAAAN2YwZDNhNzMtNzRhMy00YThkLTg5ZWUtNDQ3ZGJiMDA4MDE0NwA7CsNm50bsT7qhjA-aC88KMACAro9dszLcSA.ASuwZnHHVeV5axD8TdgUvOxFmyBNOZqCfcJuwuF2AiBGadDcnPBp1gMlXtgCq2Xw-nQd4FB7IonbzPi4mLyEziWFKo4xb29lMIKIH7FioFxaiGxloqbp7sk75gsBV8MiEdf39MfDianmbyJjcI3C0djTGJQAITOWJaMxAIJxPcqfH1svpzrMbc5Xi0be4IRRcAV8KP1yApnYC1YzBsfj6f9lH9XiYhMicVtLRU0fbi53QzdOqTfV_eAyTaf-aFcpd-Nra7smUlhQRbnHEPTanI-xKbXBb1LHP-U1fFnr060M5RGXQ5UXfMsJTzks63qXQeo_VyHTN7H-28Vlknq9lw";
        String accessToken=getAccessToken(authorizationCode);
        System.out.println("Access Token: " + accessToken);
        
        CreateAndSendEnvelope s= new CreateAndSendEnvelope();
        CreateAndSendEnvelope.sentEnvelope(accessToken);                              
        
     }
    
    public static String getAccessToken(String code) throws ClientProtocolException, IOException {
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(tokenUrl);

        String json = new Gson().toJson(new TokenRequest(clientId, clientSecret, redirectUri, code));

        StringEntity entity = new StringEntity(json);
        httpPost.setEntity(entity);
        httpPost.setHeader("Content-Type", "application/json");

        HttpResponse httpResponse = httpClient.execute(httpPost);
        HttpEntity responseEntity = httpResponse.getEntity();

        if (responseEntity != null) {
            String responseString = EntityUtils.toString(responseEntity);
            JsonObject jsonObject = new Gson().fromJson(responseString, JsonObject.class);
            return jsonObject.get("access_token").getAsString();
        } else {
            System.err.println("Failed to retrieve access token.");
            return null;
        }
    }
    
     public static class TokenRequest {
        private String grant_type = "authorization_code";
        private String client_id;
        private String client_secret;
        private String redirect_uri;
        private String code;

        public TokenRequest(String client_id, String client_secret, String redirect_uri, String code) {
            this.client_id = client_id;
            this.client_secret = client_secret;
            this.redirect_uri = redirect_uri;
            this.code = code;
        }
    }
     
     
     
    
}
