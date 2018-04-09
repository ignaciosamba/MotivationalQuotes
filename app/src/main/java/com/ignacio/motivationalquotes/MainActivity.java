package com.ignacio.motivationalquotes;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.ignacio.motivationalquotes.Data.QuoteData;
import com.ignacio.motivationalquotes.Data.QuoteListAsyncResponse;
import com.ignacio.motivationalquotes.Data.QuoteViewPagerAdapter;
import com.ignacio.motivationalquotes.Model.Quote;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private QuoteViewPagerAdapter quoteViewPagerAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quoteViewPagerAdapter = new QuoteViewPagerAdapter(getSupportFragmentManager(),
                getFragment());
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(quoteViewPagerAdapter);
//        new QuoteData().getQuotes();
    }

    private List<Fragment> getFragment() {
        final List<Fragment> fragmentList = new ArrayList<>();

        new QuoteData().getQuotes(new QuoteListAsyncResponse() {
            @Override
            public void processFinish(ArrayList<Quote> quotes) {
                for (Quote quote: quotes) {
                    QuoteFragment quoteFragment = QuoteFragment.newInstance(quote.getQuote(), quote.getAuthor());
                    fragmentList.add(quoteFragment);
                }
                //Tengo que avisar que hay cambios en el adapter.
                quoteViewPagerAdapter.notifyDataSetChanged();
            }
        });

        return fragmentList;
    }
}
