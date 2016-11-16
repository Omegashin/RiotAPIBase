package com.omegashin.riotapibase;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by gdesi on 15-Oct-16.
 */

class JsonHelper {

    private final String MY_API_KEY_CHIP = "api_key=RGAPI-6ebb8a6d-caa9-4bc2-92e3-02ff23679b40";
    private final String requestHeader = "https://na.api.pvp.net";
    private getRequestCallbacks getRequestCallbacks;
    private Context context;
    private RequestQueue requestQueue;

    JsonHelper(getRequestCallbacks getRequestCallbacks, final Context context) {
        this.getRequestCallbacks = getRequestCallbacks;
        this.context = context;
        requestQueue = Volley.newRequestQueue(context);
    }

    void testRequest() {

        final String testRequest = requestHeader + "/api/lol/na/v1.4/summoner/by-name/omegashin07?" + MY_API_KEY_CHIP;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, testRequest,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        getRequestCallbacks.showResponse(context, response.toString());
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
        requestQueue.add(jsonObjectRequest);
    }

    void getChampionVisual() {

        final String testRequest = requestHeader + "/api/lol/static-data/NA/v1.2/champion?champData=image&" + MY_API_KEY_CHIP;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, testRequest,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject championDataJSO = response.getJSONObject("data");
                            Log.e("data", "UPTO " );
                            JSONObject championName = championDataJSO.getJSONObject("Aatrox");

                            String image = "http://ddragon.leagueoflegends.com/cdn/6.22.1/img/champion/Aatrox.png";

                            String visualUrl = "http://ddragon.leagueoflegends.com/cdn/img/champion/splash/"+championName.getJSONObject("name").getString("full");


                            ImageRequest request = new ImageRequest(visualUrl,
                                    new Response.Listener<Bitmap>() {
                                        @Override
                                        public void onResponse(Bitmap bitmap) {
                                            getRequestCallbacks.returnVisual(context, bitmap);
                                        }
                                    }, 0, 0, null,
                                    new Response.ErrorListener() {
                                        public void onErrorResponse(VolleyError error) {
                                            Log.e("fd", "image load error ");
                                        }
                                    });

                            requestQueue.add(request);


                        } catch (Exception e) {
                            Log.e("Volley", e.toString());
                        }
                        //getRequestCallbacks.showResponse(context, );
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
        requestQueue.add(jsonObjectRequest);
    }
}

