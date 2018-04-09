package com.ignacio.motivationalquotes;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ignacio.motivationalquotes.Model.Quote;

import java.util.concurrent.ThreadLocalRandom;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuoteFragment extends Fragment implements View.OnClickListener {


    TextView quoteText;
    TextView nameText;
    CardView cardView;
    Button sharebtn;

    public QuoteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View quoteView = inflater.inflate(R.layout.fragment_quote, container, false);

        quoteText = quoteView.findViewById(R.id.quote);
        nameText = quoteView.findViewById(R.id.name);
        cardView = quoteView.findViewById(R.id.cardView);
        sharebtn = quoteView.findViewById(R.id.sharebtn);

        String quote = "\"" + getArguments().getString("quote") + "\"";
        String name = "-" + getArguments().getString("name");

        int colors[] = new int[]{R.color.blue_500, R.color.red_400, R.color.orange_400,
                R.color.green_500, R.color.grey_700, R.color.pink_500, R.color.amber_400};

        quoteText.setText(quote);
        nameText.setText(name);

        cardView.setBackgroundResource(getRandomColor(colors));

        sharebtn.setOnClickListener(this);

        return quoteView;
    }




    public static final QuoteFragment newInstance(String quote, String name){
        QuoteFragment fragment = new QuoteFragment();
        //Para pasar data entre activitys y fragments.
        Bundle bundle = new Bundle();
        bundle.putString("quote", quote);
        bundle.putString("name", name);
        fragment.setArguments(bundle);

        return fragment;
    }

    int getRandomColor(int[] colorArray) {
        int color;
        int qupotesArraylen = colorArray.length;

        int randomNum = ThreadLocalRandom.current().nextInt(qupotesArraylen);
        color = colorArray[randomNum];

        return color;
    }

    @Override
    public void onClick(View view) {
        String completeQoute = quoteText.getText() + " " + nameText.getText();
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, completeQoute);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

}
