package com.example.arthurakhnoyan.photochooserdemo.callback;

import com.example.arthurakhnoyan.photochooserdemo.model.FolderData;

import java.util.List;

/**
 * Created by arthurakhnoyan on 8/12/16.
 */
public interface FolderRetrieverCallback {
	void onSuccess(List<FolderData> foldersData);
	void onFailure();
}
