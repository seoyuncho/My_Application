package com.example.myapplication;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends JsonObjectRequest {

    // Server URL
    final static private String URL = "http://192.168.56.1:3000/login";

    // JSON data to be sent to the server
    private JSONObject jsonBody;


    public LoginRequest(String userID, String userPassword, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(Method.POST, URL, null, listener, errorListener);

        // Create JSON object
        jsonBody = new JSONObject();
        try {
            jsonBody.put("userID", userID);
            jsonBody.put("userPassword", userPassword);
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