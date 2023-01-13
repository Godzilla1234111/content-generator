package com.nexthink.ContentGenerator;

import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.nexthink.ContentGenerator.GetToken.getPortalToken;
import static com.nexthink.ContentGenerator.SysConfig.getTenantUUID;

public class HttpClient<T> {

        private static OkHttpClient client;
        private static ObjectMapper mapper;
        public static final String TENANT_UUID_HEADER = "Tenant-UUID";
        private static final String AUTHORIZATION_HEADER = "Authorization";

        public static Response doPostRequest(String url, String json) throws IOException {

            RequestBody body = RequestBody.create(json, MediaType.parse("application/json; charset=utf-8"));
            Request request = new Request.Builder().url(url).post(body)
                    .addHeader(AUTHORIZATION_HEADER, "Bearer" + getPortalToken())
                    .addHeader(TENANT_UUID_HEADER, getTenantUUID())
                    .build();

            return doRequest(request);
        }

    public static Response doSimplePostRequest(String url) {

        Request request = new Request.Builder().url(url)
                .build();

        return doRequest(request);
    }

        public static Response doPutRequest(String url, String json) throws IOException {

            RequestBody body = RequestBody.create(json, MediaType.parse("application/json; charset=utf-8"));
            Request request = new Request.Builder().url(url).put(body)
                    .addHeader(AUTHORIZATION_HEADER, "Bearer " +  getPortalToken())
                    .addHeader(TENANT_UUID_HEADER, getTenantUUID())
                    .build();

            return doRequest(request);
        }

        /**
         * Method to perform a POST request with username and password as the body. Mostly used to obtain the portal token.
         *
         * @param url      Target for the request.
         * @param username Portal username to be used.
         * @param password Portal password to be used.
         * @see HttpClient#doRequest(Request) (Request)
         */
        public static Response doPortalRequest(String url, String username, String password) {
            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded,application/x-www-form-urlencoded");
            RequestBody body = RequestBody.create("username=" + username + "&password=" + password, mediaType);
            Request request = new Request.Builder()
                    .url(url)
                    .method("POST", body)
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .build();
            return doRequest(request);
        }

        /**
         * This method does not inject authentication headers as this is expected to be part of the passed headers.
         */
        public static Response doGetRequestWithHeaders(String url, Map<String, String> headers) {
            Request.Builder requestBuilder = new Request.Builder().url(url);

            // Set headers
            headers.keySet().forEach(headerKey -> requestBuilder.addHeader(headerKey, headers.get(headerKey)));

            return doRequest(requestBuilder.build());
        }

        public static Response doGetRequest(String url, String token, String tenantUuid) {
            Request request = new Request.Builder().url(url)
                    .addHeader(AUTHORIZATION_HEADER, "Bearer" + token)
                    .addHeader(TENANT_UUID_HEADER, tenantUuid)
                    .build();

            return doRequest(request);
        }

        public static Response doGetRequest(String url) {
            Request request = new Request.Builder().url(url).build();

            return doRequest(request);
        }

        public static Response doDeleteRequest(String url) throws IOException {
            Request request = new Request.Builder().url(url).delete()
                    .addHeader(AUTHORIZATION_HEADER, "Bearer " + getPortalToken())
                    .addHeader(TENANT_UUID_HEADER, getTenantUUID())
                    .build();

            return doRequest(request);
        }

        /**
         * @param request to be sent.
         * @return Request response.
         */
        private static Response doRequest(Request request) {


            try {
                return getOrCreateClient().newCall(request).execute();
            } catch (IOException e) {

                throw new RuntimeException(e);
            } finally {

            }
        }

        private static OkHttpClient getOrCreateClient() {
            if (client == null) {
                client = new OkHttpClient.Builder()
                        .connectTimeout(30, TimeUnit.SECONDS)
                        .readTimeout(30, TimeUnit.SECONDS)
                        .writeTimeout(30, TimeUnit.SECONDS)
                        .build();
            }

            return client;
        }
}