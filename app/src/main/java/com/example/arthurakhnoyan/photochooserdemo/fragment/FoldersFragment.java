package com.example.arthurakhnoyan.photochooserdemo.fragment;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.arthurakhnoyan.photochooserdemo.R;
import com.example.arthurakhnoyan.photochooserdemo.adapter.FoldersAdapter;
import com.example.arthurakhnoyan.photochooserdemo.callback.FolderRetrieverCallback;
import com.example.arthurakhnoyan.photochooserdemo.controller.BaseImageRequestController;
import com.example.arthurakhnoyan.photochooserdemo.controller.ImageRequestControllerFactory;
import com.example.arthurakhnoyan.photochooserdemo.listiner.FolderClickListener;
import com.example.arthurakhnoyan.photochooserdemo.listiner.FolderSelectedListener;
import com.example.arthurakhnoyan.photochooserdemo.model.FolderData;
import com.example.arthurakhnoyan.photochooserdemo.model.FolderType;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FoldersFragment extends Fragment {

	private FolderSelectedListener folderSelectedListener;
	private List<FolderData> folderList;
	private FoldersAdapter adapter;
	private RecyclerView recyclerView;
	private BaseImageRequestController controller;
//	private PhoneImagesRetriever retriever;

	public FoldersFragment() {
		// Required empty public constructor
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_folders, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		controller = ImageRequestControllerFactory.getImageRequestController(FolderType.LOCAL, getAppcontext());

//		retriever = PhoneImagesRetriever.getInstance(getLoaderManager(), getActivity().getApplicationContext());
		if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),
				Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
			getFoldersOfImages();
		}


	}

	public void getFoldersOfImages() {
		controller.getFolders(new FolderRetrieverCallback() {
			@Override
			public void onSuccess(List<FolderData> foldersData) {
				folderList = foldersData;
				folderSelectedListener = (FolderSelectedListener) getParentFragment();
				adapter = new FoldersAdapter(getActivity(), folderList);
				recyclerView = (RecyclerView) getView().findViewById(R.id.folders_list);
				recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
				recyclerView.setAdapter(adapter);
				adapter.setFolderClickListener(new FolderClickListener() {
					@Override
					public void onItemClicked(FolderData item, int position) {
						folderSelectedListener.onFolderSelect(item);
					}
				});
			}

			@Override
			public void onFailure() {

			}


		});
	}

	private Context getAppcontext() {
		return getActivity().getApplicationContext();
	}
}

