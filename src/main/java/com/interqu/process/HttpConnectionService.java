package com.interqu.process;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONObject;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

@Service
public class HttpConnectionService {
     
    public JSONObject executeGET(String url, HashMap<String, String> params, HashMap<String, String> headers) throws Exception{
        return executeRequest(url, HttpMethod.GET, params,headers);
    }

    public JSONObject executePOST(String url, HashMap<String, String> params, HashMap<String, String> headers) throws Exception{
        return executeRequest(url, HttpMethod.POST, params,headers);
    }

    public JSONObject executeRequest(String url, HttpMethod method, HashMap<String,String> params, HashMap<String, String> headers) throws Exception{
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

            // Send request
            connection.setRequestMethod(method.name());

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
