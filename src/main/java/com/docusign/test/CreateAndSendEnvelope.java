package com.docusign.test;



import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.docusign.esign.api.EnvelopesApi;
import com.docusign.esign.client.ApiClient;
import com.docusign.esign.client.ApiException;
import com.docusign.esign.model.Document;
import com.docusign.esign.model.EnvelopeDefinition;
import com.docusign.esign.model.EnvelopeSummary;
import com.docusign.esign.model.Recipients;
import com.docusign.esign.model.SignHere;
import com.docusign.esign.model.Signer;
import com.docusign.esign.model.Tabs;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Abhishek.Gautam
 */
public class CreateAndSendEnvelope {
    
    
    public static String sentEnvelope(String accessToken) throws ClientProtocolException, IOException {
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("https://demo.docusign.net/restapi//v2.1/accounts/fb30f9f4-4f1b-42ee-8448-67b166f79d81/envelopes");

        String signerEmail="abhishekgautam8260@gmail.com";
        String signerName= "Abhishek Gautam";        
        
        String json = new Gson().toJson(makeEnvelope(signerEmail,signerName));

        StringEntity entity = new StringEntity(json);
        httpPost.setEntity(entity);
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Authorization", "Bearer "+ accessToken);

        HttpResponse httpResponse = httpClient.execute(httpPost);
        HttpEntity responseEntity = httpResponse.getEntity();
        if(responseEntity.equals(null))
        {
            return "Failed sent";
        }
        else{
            return "Envelope is successfully sent to the given email";
        }
        

    }
    
    
     private static EnvelopeDefinition makeEnvelope(String signerEmail, String signerName) {
        // Create a signer recipient to sign the document, identified by name and email
        // We set the clientUserId to enable embedded signing for the recipient
        Signer signer = new Signer();
        signer.setEmail(signerEmail);
        signer.setName(signerName);
        // signer.clientUserId("1000"); # will not receive docuSign email to sign if client user id is present
        signer.recipientId("1");

        SignHere signHere = new SignHere();
        signHere.setAnchorString("/sn1/");
        signHere.setAnchorUnits("pixels");
        signHere.setAnchorYOffset("20");
        signHere.setAnchorXOffset("10");

        Tabs signerTabs = new Tabs();
        signerTabs.setSignHereTabs(Arrays.asList(signHere));

        // Add the recipient to the envelope object
        Recipients recipients = new Recipients();
        recipients.setSigners(Arrays.asList(signer));

        EnvelopeDefinition envelopeDefinition = new EnvelopeDefinition();
        envelopeDefinition.setEmailSubject("Please sign this document");
        envelopeDefinition.setRecipients(recipients);

        Path path = Paths.get("C:/TestCase/dummy.pdf");

        byte[] data = null;
        try {
            data = Files.readAllBytes(path);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Document document = new Document();
        document.setDocumentBase64(Base64.getEncoder().encodeToString(data));
        document.setName("Hello Doc");
        document.setFileExtension("pdf");
        document.setDocumentId("3");

        envelopeDefinition.setDocuments(Arrays.asList(document));
        // Request that the envelope be sent by setting |status| to "sent".
        // To request that the envelope be created as a draft, set to "created"
        envelopeDefinition.setStatus("sent");

        return envelopeDefinition;
    }        
    
}
