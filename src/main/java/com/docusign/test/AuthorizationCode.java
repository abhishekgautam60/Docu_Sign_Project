/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.docusign.test;


import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.awt.Desktop;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class AuthorizationCode {
    
    
    String accountId="";
    
    public static void main(String[] args) throws IOException, Exception {
        String url = "https://account-d.docusign.com/oauth/auth?response_type=code&scope=scope&client_id=7f0d3a73-74a3-4a8d-89ee-447dbb008014&redirect_uri=https://www.example.com/callback";
//        String url="https://account-d.docusign.com/username?prompt=login&response_type=code&scope=scope&client_id=7f0d3a73-74a3-4a8d-89ee-447dbb008014&redirect_uri=https%3A%2F%2Fwww.example.com%2Fcallback";
        

         System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

        // Open Chrome browser in headless mode
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        WebDriver driver = new ChromeDriver(options);

        try {
            // URL to open in the browser
//            String url = "https://account-d.docusign.com/oauth/auth?response_type=code&scope=scope&client_id=7f0d3a73-74a3-4a8d-89ee-447dbb008014&redirect_uri=https://www.example.com/callback";

            // Open the URL in the browser
            driver.get(url);

            // Wait for user interaction
            // Here you might want to implement some waiting logic
            // For simplicity, we'll just wait for 10 seconds
            Thread.sleep(10000);

            // Get the current URL from the browser
            String currentUrl = driver.getCurrentUrl();
            System.out.println("Current URL: " + currentUrl);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser
            driver.quit();
        }
//        openInBrowser(url);
    }
//    public static void openInBrowser(String url) throws Exception {
//        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
//            Desktop.getDesktop().browse(new URI(url));
//            
//            System.err.println("Desktop executed");
//            System.out.println(url);            
//        } else {
//            // If Desktop API is not supported, you can handle the case accordingly
//            System.out.println("Desktop API is not supported, cannot open browser.");
//        }
//    }
}

    
    
//    public static String getCodeFromUrl() throws UnsupportedEncodingException{
//            URL url = new URL(urlString);
//
//            // Get the query part of the URL
//            String query = url.getQuery();
//
//            // Create a map to store query parameters
//            Map<String, String> queryParams = new HashMap<>();
//
//            // Split query parameters
//            String[] pairs = query.split("&");
//
//            // Iterate over each parameter
//            for (String pair : pairs) {
//                int idx = pair.indexOf("=");
//                String key = URLDecoder.decode(pair.substring(0, idx), "UTF-8");
//                String value = URLDecoder.decode(pair.substring(idx + 1), "UTF-8");
//                queryParams.put(key, value);
//            }
//
//            // Retrieve the value of the "code" parameter
//            String code = queryParams.get("code");
//
//            // Print the value of the "code" parameter
//            System.out.println("Code: " + code);
//    }

