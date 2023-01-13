package com.nexthink.ContentGenerator;

import java.io.*;
import okhttp3.*;

public class Create {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();

    public String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("Authorization", "Bearer" + GetToken.getPortalToken())
                .build();


        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}