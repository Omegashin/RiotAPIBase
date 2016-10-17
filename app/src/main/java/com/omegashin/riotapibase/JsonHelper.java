package com.omegashin.riotapibase;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

/**
 * Created by gdesi on 15-Oct-16.
 */

class JsonHelper {

    private final String MY_API_KEY_CHIP = "?api_key=RGAPI-6ebb8a6d-caa9-4bc2-92e3-02ff23679b40";
    private final String requestHeader = "https://na.api.pvp.net";
    private getRequestCallback getRequestCallback;
    private Context context;
    private RequestQueue requestQueue;

    JsonHelper(com.omegashin.riotapibase.getRequestCallback getRequestCallback, final Context context) {
        this.getRequestCallback = getRequestCallback;
        this.context=context;
        requestQueue = Volley.newRequestQueue(context);
    }

    void testRequest() {

        final String testRequest = requestHeader + "/api/lol/na/v1.4/summoner/by-name/omegashin07" + MY_API_KEY_CHIP;

        JsonObjectRequest obreq = new JsonObjectRequest(Request.Method.GET, testRequest,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        getRequestCallback.showResponse(context,response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley", "Error");
                    }
                }
        );

        //Add to request Queue
        requestQueue.add(obreq);
    }
}

