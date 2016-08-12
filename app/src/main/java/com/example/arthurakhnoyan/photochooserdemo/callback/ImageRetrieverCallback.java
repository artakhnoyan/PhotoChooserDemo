package com.example.arthurakhnoyan.photochooserdemo.callback;

import com.example.arthurakhnoyan.photochooserdemo.model.ImageData;

import java.util.List;

/**
 * Created by arthurakhnoyan on 8/12/16.
 */
public interface ImageRetrieverCallback {
	void onSuccess(List<ImageData> images);
	void onFailure();
}
