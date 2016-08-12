package com.example.arthurakhnoyan.photochooserdemo.controller;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;

import com.example.arthurakhnoyan.photochooserdemo.callback.FolderRetrieverCallback;
import com.example.arthurakhnoyan.photochooserdemo.callback.ImageRetrieverCallback;
import com.example.arthurakhnoyan.photochooserdemo.model.FolderData;
import com.example.arthurakhnoyan.photochooserdemo.model.FolderType;
import com.example.arthurakhnoyan.photochooserdemo.model.ImageData;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by arthurakhnoyan on 8/12/16.
 */
public class LocalImageRequestController extends BaseImageRequestController {

	private Context context;

	public LocalImageRequestController(Context context) {
		this.context = context;
	}

	@Override
	public void getImages(final FolderData folder, final int offset, final int limit, final ImageRetrieverCallback callback) {

		new AsyncTask<Void, List<ImageData>, List<ImageData>>() {

			@Override
			protected List<ImageData> doInBackground(Void... params) {
				String condition ="";
				if (folder != null) {
					String uri = MediaStore.Images.Media.DATA;
					condition = uri + " like '" + folder.folderPath + "/%' and " + uri + " not like '" + folder.folderPath + "/%/%'";
				}
				String[] columns = {MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID, MediaStore.Images.Thumbnails.DATA, MediaStore.Images.Media.ORIENTATION, MediaStore.Images.Media.DATE_MODIFIED};
				String orderBy = MediaStore.Images.Media.DATE_MODIFIED + " DESC";
				String limitOffset = " LIMIT " + limit + " 	OFFSET " + offset;
				Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, condition, null, orderBy + limitOffset);
				if (cursor != null) {
					boolean isDataPresent = cursor.moveToFirst();
					if (isDataPresent) {
						String path;
						int data = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
						List<ImageData> imageslist = new ArrayList<>();
						do {
							path = cursor.getString(data);
							ImageData imageData = new ImageData();
							imageData.setImagePath(path);
							imageData.setThumbnailPath(path);
							imageslist.add(imageData);
						} while (cursor.moveToNext());
						cursor.close();
						return imageslist;
					}
				}
				return null;
			}

			@Override
			protected void onPostExecute(List<ImageData> imagesData) {
				super.onPostExecute(imagesData);
				if (imagesData != null) {
					callback.onSuccess(imagesData);
				} else {
					callback.onFailure();
				}
			}
		}.execute();

	}

	@Override
	public void getFolders(final FolderRetrieverCallback callback) {
		new AsyncTask<Void, Void, List<FolderData>>() {

			@Override
			protected List<FolderData> doInBackground(Void... params) {
				FolderData recentFolder = new FolderData();
				recentFolder.folderName = "RECENT";
				String[] columns = {MediaStore.Images.Media.DATA};
				String orderBy = MediaStore.Images.Media.DATA;
				Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null, null, orderBy);
				if (cursor != null) {
					String path;
					String previousParent;
					String currentParent;
					String uri = MediaStore.Images.Media.DATA;
					boolean isDataPresent = cursor.moveToFirst();
					if (isDataPresent) {
						List<FolderData> folderList = new ArrayList<>();
						folderList.add(recentFolder);
						FolderData folderData = new FolderData();
						previousParent = new File(cursor.getString(cursor.getColumnIndex(uri))).getParentFile().getPath();
						folderData.folderName = new File(previousParent).getName();
						folderData.folderPath = previousParent;
						folderData.folderType = FolderType.LOCAL;
						folderData.count = 1;
						folderList.add(folderData);
						recentFolder.folderIconPath = new File(cursor.getString(cursor.getColumnIndex(uri))).getPath();
						do {

							recentFolder.count++;
							path = cursor.getString(cursor.getColumnIndex(uri));
							currentParent = new File(path).getParentFile().getPath();
							if (!currentParent.equals(previousParent)) {
								folderData = new FolderData();
								previousParent = currentParent;
								folderData.folderName = new File(previousParent).getName();
								folderData.folderPath = previousParent;
								folderData.count = 1;
								folderList.add(folderData);
							} else {
								folderData.count++;
							}
						} while (cursor.moveToNext());
						return folderList;
					}
					cursor.close();
				}
				return null;
			}

			@Override
			protected void onPostExecute(List<FolderData> foldersData) {
				super.onPostExecute(foldersData);
				new AsyncTask<List<FolderData>, Void, List<FolderData>>() {

					@Override
					protected List<FolderData> doInBackground(List<FolderData>... params) {
						setFolderThumbnailImage(params[0]);
						return params[0];
					}

					@Override
					protected void onPostExecute(List<FolderData> folderData) {
						super.onPostExecute(folderData);
						callback.onSuccess(folderData);
					}
				}.execute(foldersData);


			}
		}.execute();
	}

	private void setFolderThumbnailImage(List<FolderData> folderList) {
		for (FolderData folder: folderList.subList(1, folderList.size())) {
			File dir = new File(folder.folderPath);
			File[] files = dir.listFiles(new FileFilter() {
				@Override
				public boolean accept(File pathname) {
					String type = null;
					String extension = MimeTypeMap.getFileExtensionFromUrl(pathname.getName());
					if (!TextUtils.isEmpty(extension)) {
						type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
					}

					return !TextUtils.isEmpty(type) && type.startsWith("image");
				}
			});
			if (files == null || files.length == 0) {
				return ;
			}

			File lastModifiedFile = files[0];
			for (int i = 1; i < files.length; i++) {
				if (lastModifiedFile.lastModified() < files[i].lastModified()) {
					lastModifiedFile = files[i];
				}
			}

			folder.folderIconPath = lastModifiedFile.getPath();
		}
	}
}
