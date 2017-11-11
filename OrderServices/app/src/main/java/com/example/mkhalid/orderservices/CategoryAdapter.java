package com.example.mkhalid.orderservices;

import android.support.v4.app.FragmentPagerAdapter;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * Created by mkhalid on 11/10/17.
 */

public class CategoryAdapter extends FragmentPagerAdapter {

    private Context mContext;

    /**
     * Create a new {@link CategoryAdapter} object.
     *
     * @param context is the context of the app
     * @param fm      is the fragment manager that will keep each fragment's state in the adapter
     *                across swipes.
     */
    public CategoryAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    /**
     * Return the {@link Fragment} that should be displayed for the given page number.
     */
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new ();
        } else if (position == 1) {
            return new GymFragment();
        } else if (position == 2) {
            return new SchoolFragment();
        } else if (position == 3) {
            return new SleepFragment();
        } else {
            return new StudyFragment();
        }
    }

    /**
     * Return the total number of pages.
     */
    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.car);
        } else if (position == 1) {
            return mContext.getString(R.string.gym);
        } else if (position == 2) {
            return mContext.getString(R.string.school);
        } else if (position == 3) {
            return mContext.getString(R.string.sleep);
        } else {
            return mContext.getString(R.string.study);}
    }

}
