package com.ignacio.motivationalquotes.Data;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.ignacio.motivationalquotes.Controller.QuotesController;
import com.ignacio.motivationalquotes.Model.Quote;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Ignacio on 05/04/2018.
 * It will be for fetch the json with the quotes.
 */



public class QuoteData {
    ArrayList<Quote> quotesArrayList = new ArrayList<>();

    public void getQuotes(final QuoteListAsyncResponse callBack) {
        String url = "https://raw.githubusercontent.com/pdichone/UIUX-Android-Course/master/Quotes.json%20";
        Log.d("SAMBA", "Data");
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("SAMBA", "Data 2");
                        for (int i = 0; i < response.length(); i++) {
                            Log.d("SAMBA", "I: " + i);
                            try {
                                JSONObject quoteObject = response.getJSONObject(i);
                                Quote quote = new Quote();
                                quote.setAuthor(quoteObject.getString("name"));
                                quote.setQuote(quoteObject.getString("quote"));

                                Log.d("SAMBA", ", Author: " + quoteObject.getString("name"));
                                Log.d("SAMBA", ", Quote: " + quoteObject.getString("quote"));

                                quotesArrayList.add(quote);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        //Ahora pregnto si el callback esta listo entonces llamo el processFinish entonces asi voy
                        // a saber si esta listos ya todos los datos
                        if (null != callBack) {
                            callBack.processFinish(quotesArrayList);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("SAMBA", "El error es: " + error.toString());
                    }
        });
        QuotesController.getInstance().addToRequestQueue(jsonArrayRequest);
    }
}
