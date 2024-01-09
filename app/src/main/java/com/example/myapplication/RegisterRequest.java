package com.example.myapplication;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

public class RegisterRequest extends JsonObjectRequest {

    // Server URL
    final static private String URL = "http://143.248.218.164:3000/register";

    // JSON data to be sent to the server
    private JSONObject jsonBody;


    public RegisterRequest(String userID, String userPassword, String userName, int userAge, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(Method.POST, URL, null, listener, null);

        // Create JSON object
        jsonBody = new JSONObject();
        try {
            jsonBody.put("userID", userID);
            jsonBody.put("userPassword", userPassword);
            jsonBody.put("userName", userName);
            jsonBody.put("userAge", userAge);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public byte[] getBody() {
        return jsonBody.toString().getBytes();
    }

    @Override
    public String getBodyContentType() {
        return "application/json";
    }
}