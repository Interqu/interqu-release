package com.interqu.process;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONObject;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import com.mongodb.internal.connection.Connection;

@Service
public class HttpConnectionService {
     
    public JSONObject executeGET(String url, Map<String, String> params, Map<String, String> headers) throws Exception{
        return executeRequest(url, HttpMethod.GET, params,headers,null);
    }

    public JSONObject executePOST(String url, Map<String, String> params, Map<String, String> headers, Map<String, Object> requestBody) throws Exception{
        return executeRequest(url, HttpMethod.POST, params,headers,requestBody);
    }

    public JSONObject executeRequest(String url, HttpMethod method, Map<String,String> params, Map<String, String> headers, Map<String, Object> requestBody) throws Exception{
            //Set Params
            for(String key : params.keySet()){
                url += "?" + URLEncoder.encode(key, StandardCharsets.UTF_8.toString()) + "=" + URLEncoder.encode(params.get(key), StandardCharsets.UTF_8.toString()) ;
            }
            URL urlCon = new URL(url);
            // Open a connection to the URL
            HttpsURLConnection connection = (HttpsURLConnection) urlCon.openConnection();

            //Add request headers
            for(String key : headers.keySet()){
                connection.setRequestProperty(key, headers.get(key));
            }
            // Handle GET request
            if (method.equals(HttpMethod.GET)) {
                // Enable input
                connection.setDoInput(true);
            }
            // Handle POST request
            else if (method.equals(HttpMethod.POST)) {
                // Enable output and set request body if provided
                connection.setDoOutput(true);
                if (requestBody != null) {
                    connection.setRequestProperty("Content-Type", "application/json");
                    String jsonBody = new JSONObject(requestBody).toString(); // Convert the request body to JSON string
                    OutputStream outputStream = connection.getOutputStream();
                    outputStream.write(jsonBody.getBytes());
                    outputStream.flush();
                }
            }
            connection.setRequestMethod(method.name());

            // Send request
            int responseCode = connection.getResponseCode();

            if(responseCode != 200){
                throw new Exception(responseCode + " - Unexpected Error in connection to " + url);
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            return new JSONObject(response.toString());
    }

}
