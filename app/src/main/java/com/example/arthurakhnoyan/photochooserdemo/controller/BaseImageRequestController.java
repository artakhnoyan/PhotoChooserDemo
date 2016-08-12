package com.example.arthurakhnoyan.photochooserdemo.controller;

import com.example.arthurakhnoyan.photochooserdemo.callback.FolderRetrieverCallback;
import com.example.arthurakhnoyan.photochooserdemo.callback.ImageRetrieverCallback;
import com.example.arthurakhnoyan.photochooserdemo.model.FolderData;

/**
 * Created by arthurakhnoyan on 8/12/16.
 */
abstract public class BaseImageRequestController {

	public abstract void getImages(FolderData folder, int offset, int limit, ImageRetrieverCallback callback);
	public abstract void getFolders(FolderRetrieverCallback callback);
}
