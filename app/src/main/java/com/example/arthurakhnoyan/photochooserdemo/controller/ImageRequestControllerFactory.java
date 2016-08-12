package com.example.arthurakhnoyan.photochooserdemo.controller;

import android.content.Context;

import com.example.arthurakhnoyan.photochooserdemo.model.FolderType;

/**
 * Created by arthurakhnoyan on 8/12/16.
 */
public class ImageRequestControllerFactory {
	public static BaseImageRequestController getImageRequestController(FolderType type,Context context) {
		switch (type) {
			case LOCAL:
				return new LocalImageRequestController(context);
		}
		return null;
	}
}
