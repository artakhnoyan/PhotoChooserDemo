package com.example.arthurakhnoyan.photochooserdemo;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.arthurakhnoyan.photochooserdemo.adapter.FragmentAdapter;

public class MainActivity extends AppCompatActivity {

	private boolean isOpened;
	private FragmentPagerAdapter adapterViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ViewPager vpPager = (ViewPager) findViewById(R.id.view_pager);
		adapterViewPager = new FragmentAdapter(getSupportFragmentManager());
		assert vpPager != null;

		vpPager.setAdapter(adapterViewPager);





		if (savedInstanceState != null) {
			if (savedInstanceState.getBoolean("folder")) {
//				folderLayout.setVisibility(View.VISIBLE);
//				folderLayout.setTranslationY(0);
				isOpened = true;
			}
		}

	}


	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putBoolean("folder", isOpened);
	}
}
