package com.example.arthurakhnoyan.photochooserdemo.fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.arthurakhnoyan.photochooserdemo.R;
import com.example.arthurakhnoyan.photochooserdemo.SourceParam;
import com.example.arthurakhnoyan.photochooserdemo.adapter.ImageAdapter;
import com.example.arthurakhnoyan.photochooserdemo.callback.ImageRetrieverCallback;
import com.example.arthurakhnoyan.photochooserdemo.callback.RecyclerViewPaginationCallback;
import com.example.arthurakhnoyan.photochooserdemo.controller.BaseImageRequestController;
import com.example.arthurakhnoyan.photochooserdemo.controller.ImageRequestControllerFactory;
import com.example.arthurakhnoyan.photochooserdemo.listiner.ImageItemClickListener;
import com.example.arthurakhnoyan.photochooserdemo.listiner.PhotoChooserAcitonListener;
import com.example.arthurakhnoyan.photochooserdemo.model.FolderData;
import com.example.arthurakhnoyan.photochooserdemo.model.FolderType;
import com.example.arthurakhnoyan.photochooserdemo.model.ImageData;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhotoGridFragment extends Fragment implements ImageItemClickListener {

	//	private PhoneImagesRetriever retriever;
	private BaseImageRequestController controller;
	private ImageAdapter adapter;
	private RecyclerView recyclerView;
	private View permissionButton;
	private boolean isLoading = false;
	private static final int REQUEST_EXTERNAL_STORAGE = 1;
	PhotoChooserAcitonListener photoChooserAcitonListener;
	private static final String TAG = "PhotoGridFragment";
	private GridLayoutManager layoutManager;
	private FolderData folderData = null;


	public PhotoGridFragment() {
		// Required empty public constructor
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_photo_grid, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		controller = ImageRequestControllerFactory.getImageRequestController(FolderType.LOCAL, getAppcontext());


		Display display = getActivity().getWindowManager().getDefaultDisplay();
		int gridColumnCount = getActivity().getApplicationContext().getResources().getInteger(R.integer.photo_chooser_grid_column_count);
		Point size = new Point();
		display.getSize(size);
		int imageSize = size.x / gridColumnCount;

		adapter = new ImageAdapter(getActivity().getApplicationContext(), imageSize);
		adapter.setItemClickListener(this);

		permissionButton = view.findViewById(R.id.permission_button);
		recyclerView = (RecyclerView) view.findViewById(R.id.image_list);
		layoutManager = new GridLayoutManager(getAppcontext(), gridColumnCount);
		recyclerView.setLayoutManager(layoutManager);
		recyclerView.setAdapter(adapter);

		adapter.setPaginationCallback(new RecyclerViewPaginationCallback() {
			@Override
			public void onNextPageReached() {
				if (!isLoading) {
					isLoading = true;
					getImages(folderData, adapter.getItemCount(), 50);
				}
			}
		});
//		retriever = PhoneImagesRetriever.getInstance(getActivity().getSupportLoaderManager(), getActivity().getApplicationContext());
		if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),
				Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
			getImages(null, 0, 50);
		} else {
			permissionButton.setVisibility(View.VISIBLE);
			permissionButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_EXTERNAL_STORAGE);
				}
			});
		}
	}

	public void getImages(FolderData folder, int offset, int limit) {
		controller.getImages(folder, offset, limit, new ImageRetrieverCallback() {
			@Override
			public void onSuccess(List<ImageData> images) {
				adapter.append(images, 0, 50);
				isLoading = false;
				Log.d(TAG, "onSuccess: Succeed");
			}

			@Override
			public void onFailure() {
				Log.d(TAG, "onFailure: Failed");
			}
		});
	}


	public void setFolder(FolderData folderData, boolean scrollToTop) {
		this.folderData = folderData;
		adapter.clear();
		if (folderData.folderName.equals("RECENT")) {
			this.folderData = null;
			getImages(null, 0, 50);
		} else {
			Log.d(TAG, "setFolder: " + folderData.folderPath);
			controller.getImages(folderData, 0, 50, new ImageRetrieverCallback() {
				@Override
				public void onSuccess(List<ImageData> images) {
					adapter.append(images, 0, 50);
					isLoading = false;
				}

				@Override
				public void onFailure() {

				}
			});
		}
		if (scrollToTop) {
			recyclerView.scrollToPosition(0);
		}
	}
//
//	public void getImages() {
//		addCameraIcon();
//		retriever.retrieveImages(new ImagesRetrievedListener() {
//			@Override
//			public void onFinish() {
//
//			}
//
//			@Override
//			public void onImagesRetrievedProgress(ImageData data) {
//				Activity activity = getActivity();
//				if (activity == null || activity.isFinishing()) {
//					return;
//				}
//				adapter.addItem(data);
//				List<ImageData> dataToAdd = new ArrayList<>();
//				dataToAdd.add(data);
//			}
//		});
//	}

	private void addCameraIcon() {
		ImageData cameraItem = new ImageData();
		cameraItem.setSource(SourceParam.CAMERA);
//		cameraItem.setImagePath(ImagePickerConstants.ITEM_CAMERA);
//		cameraItem.setFolderPath(ImagePickerConstants.FOLDER_RECENT);
		adapter.addItem(cameraItem);
	}

	@Override
	public void onItemClick(ImageData imageData, int position) {
		if (position == 1) {
			Log.d(TAG, "onItemClick: Camera");
		} else {
			Log.d(TAG, "onItemClick: Photo");
		}
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		photoChooserAcitonListener = (PhotoChooserAcitonListener) getParentFragment();
		switch (requestCode) {
			case REQUEST_EXTERNAL_STORAGE:
				if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//					getImages();
					permissionButton.setVisibility(View.GONE);
					photoChooserAcitonListener.onPermissionGranted(true);
				} else {
					photoChooserAcitonListener.onPermissionGranted(false);
				}
				break;
		}
	}

	private Context getAppcontext() {
		return getActivity().getApplicationContext();
	}
}
