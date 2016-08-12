package com.example.arthurakhnoyan.photochooserdemo.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.arthurakhnoyan.photochooserdemo.listiner.FolderSelectedListener;
import com.example.arthurakhnoyan.photochooserdemo.listiner.PhotoChooserAcitonListener;
import com.example.arthurakhnoyan.photochooserdemo.model.FolderData;
import com.example.arthurakhnoyan.photochooserdemo.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class PhotoChooserFragment extends Fragment implements FolderSelectedListener, View.OnClickListener, PhotoChooserAcitonListener {

	TextView recentView, backView;
	private Animation animation;
	private View folderLayout;
	private FrameLayout photoGridFragmentLayout;
	private boolean isOpened = false;
	private BottomSheetBehavior<FrameLayout> bottomSheetBehavior;
	private static final String TAG = "PhotoChooserFragment";


	public PhotoChooserFragment() {
		// Required empty public constructor
	}

	public static PhotoChooserFragment newInstance() {

		Bundle args = new Bundle();

		PhotoChooserFragment fragment = new PhotoChooserFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_photo_chooser, container, false);

		return view;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		Toolbar actionBar = (Toolbar) view.findViewById(R.id.folder_chooser);
		recentView = (TextView) actionBar.findViewById(R.id.textView);
		backView = (TextView) actionBar.findViewById(R.id.btn_back);
		recentView.setOnClickListener(this);
		backView.setOnClickListener(this);

		folderLayout = view.findViewById(R.id.folders_fragment_layout);

		photoGridFragmentLayout = (FrameLayout) view.findViewById(R.id.photo_grid_fragment_layout);
		bottomSheetBehavior = BottomSheetBehavior.from(photoGridFragmentLayout);
		bottomSheetBehavior.setPeekHeight(800);
		bottomSheetBehavior.setHideable(true);
		bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
			@Override
			public void onStateChanged(@NonNull View bottomSheet, int newState) {
				if (newState == BottomSheetBehavior.STATE_HIDDEN) {
					backView.setVisibility(View.VISIBLE);
				}
			}

			@Override
			public void onSlide(@NonNull View bottomSheet, float slideOffset) {

			}
		});

	}

	@Override
	public void onClick(View v) {
		int id = v.getId();

		switch (id) {
			case R.id.textView:
				if (folderLayout.getVisibility() == View.VISIBLE) {
					hideFoldersFragment();
				} else {
					showFoldersFragment();
				}
				break;
			case R.id.btn_back:
				showPhotoGrid();
				break;
		}

	}

	private void showFoldersFragment() {
		animation = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.appear_from_bottom);
		folderLayout.setVisibility(View.VISIBLE);
		folderLayout.startAnimation(animation);
		isOpened = true;
	}

	private void hideFoldersFragment() {
		animation = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.disappear_from_top);
		folderLayout.startAnimation(animation);
		folderLayout.setVisibility(View.GONE);
		isOpened = false;
	}

	private void showPhotoGrid() {
		bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
		backView.setVisibility(View.GONE);
	}

	@Override
	public void onFolderSelect(FolderData folderData) {
		recentView.setText(folderData.folderName);
		PhotoGridFragment fragment = (PhotoGridFragment) getChildFragmentManager().findFragmentById(R.id.photo_grid_fragment);
		fragment.setFolder(folderData, true);
		hideFoldersFragment();
	}

	@Override
	public void onPermissionGranted(boolean isGranted) {
		if (isGranted) {
			FoldersFragment fragment = (FoldersFragment) getChildFragmentManager().findFragmentById(R.id.folders_fragment);
			fragment.getFoldersOfImages();
		}
	}

	private Context getAppcontext() {
		return getActivity().getApplicationContext();
	}
}
