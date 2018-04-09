package com.ignacio.motivationalquotes.Data;

import com.ignacio.motivationalquotes.Model.Quote;

import java.util.ArrayList;

/**
 * Created by Ignacio on 08/04/2018.
 */

public interface QuoteListAsyncResponse {
    void processFinish(ArrayList<Quote> quotes);
}
