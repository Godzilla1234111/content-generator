package com.nexthink.ContentGenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class GetToken {

    public static String getPortalToken() throws IOException {

        URL url = new URL("https://myportalurl.com");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod("POST");

        int responseCode = con.getResponseCode();
        System.out.println("Response code: " + responseCode);

        InputStreamReader inputStreamReader = null;
        if (responseCode >= 200 && responseCode < 400) {
            inputStreamReader = new InputStreamReader(con.getInputStream());
        } else {
            inputStreamReader = new InputStreamReader(con.getErrorStream());
        }
        BufferedReader in = new BufferedReader(inputStreamReader);
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //System.out.println(response.toString());
        return response.toString();
    }

}