package com.example.mkhalid.orderservices;

/**
 * Created by mkhalid on 11/12/17.
 */

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static final String LOG_TAG = Utils.class.getSimpleName();

    public static ArrayList<Order> fetchNewsData(String requestUrl) {
        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error closing input stream", e);
        }

        // Extract relevant fields from the JSON response
        return extractNewsInfoFromJson(jsonResponse);
    }

    /**
     * Returns new URL object from the given string URL.
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error with creating URL ", e);
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest (URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, return early
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // if succeed (request code 200)
            // then read the input stream and parse the response
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }
            else
            {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream( InputStream inputStream ) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    /**
     * Return a {@link Order} object by parsing out information
     * about the books from json response
     */
    private static ArrayList<Order> extractNewsInfoFromJson(String newsJSON) {

        ArrayList<Order> orders = new ArrayList<>();
        if (TextUtils.isEmpty(newsJSON)) {
            return null;
        }

        try {
            JSONObject baseJsonResponse = new JSONObject(newsJSON);
            Log.d("JSOOOOON", baseJsonResponse.toString());
            JSONArray orderArray = baseJsonResponse.getJSONArray("orders");
            Log.d("JSON ARRAY", orderArray.toString());
            for (int i = 0; i < orderArray.length(); i++) {

                JSONObject currentOrder = orderArray.getJSONObject(i);
                Log.d("JSON CURRENT", currentOrder.toString());

                String email = currentOrder.getString("email");
                String item = currentOrder.getString("itemName");
                int quantity = Integer.parseInt(currentOrder.getString("quantity"));
                double cost = Double.parseDouble(currentOrder.getString("cost"));
                String description = currentOrder.getString("description");

                Order orderEntry = new Order(email, item, quantity,cost, description);

                orders.add(orderEntry);
            }

            return orders;
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing JSON", e);
        }
        return null;
    }
}
