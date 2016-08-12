package com.example.arthurakhnoyan.photochooserdemo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.arthurakhnoyan.photochooserdemo.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class BackgroundChooserFragment extends Fragment {


	public BackgroundChooserFragment() {
		// Required empty public constructor
	}

	public static BackgroundChooserFragment newInstance() {

		Bundle args = new Bundle();

		BackgroundChooserFragment fragment = new BackgroundChooserFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_background_chooser, container, false);
	}

}
