package com.ignacio.motivationalquotes.Data;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Ignacio on 08/04/2018.
 */

public class QuoteViewPagerAdapter extends FragmentPagerAdapter {

    //Lista de fragments.
    private List<Fragment> fragmentList;

    public QuoteViewPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragmentList = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return this.fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return this.fragmentList.size();
    }
}
