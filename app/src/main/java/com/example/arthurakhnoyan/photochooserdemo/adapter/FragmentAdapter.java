package com.example.arthurakhnoyan.photochooserdemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.arthurakhnoyan.photochooserdemo.fragment.BackgroundChooserFragment;
import com.example.arthurakhnoyan.photochooserdemo.fragment.PhotoChooserFragment;

/**
 * Created by arthurakhnoyan on 8/12/16.
 */

public class FragmentAdapter extends FragmentPagerAdapter {

	private static int NUM_ITEMS = 2;

	public FragmentAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		switch (position) {
			case 0:
				return PhotoChooserFragment.newInstance();
			case 1:
				return BackgroundChooserFragment.newInstance();
		}
		return null;
	}

	@Override
	public int getCount() {
		return NUM_ITEMS;
	}
}
