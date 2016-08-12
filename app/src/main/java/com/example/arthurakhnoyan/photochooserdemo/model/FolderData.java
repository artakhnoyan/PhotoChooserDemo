package com.example.arthurakhnoyan.photochooserdemo.model;

/**
 * Created by arthurakhnoyan on 8/12/16.
 */

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.example.arthurakhnoyan.photochooserdemo.SourceParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arthurakhnoyan on 8/2/16.
 */
public class FolderData implements Parcelable {
	public String folderName;
	public String folderPath;
	public String folderIconPath;
	public String folderIconUrl;
	public String keyword;
	public FolderType folderType;
	public int count = 0;
	public List<ImageData> imagePaths = new ArrayList<>();
	private List<FolderData> subFolders = new ArrayList<>();
	public boolean isSocial = false;
	public int iconDrawableId = 0;
	public boolean isSelected = false;
	public boolean isExpanded = false;
	public boolean isLoading = false;
	//	public BaseSocialImagesRquestController socialRequestController = null;
	public int prioInList = Integer.MAX_VALUE;

	public SourceParam source;
	public SourceParam origin;

	public FolderData() {

	}

	protected FolderData(Parcel in) {
		folderName = in.readString();
		folderPath = in.readString();
		folderIconPath = in.readString();
		folderIconUrl = in.readString();
		keyword = in.readString();
		String sourceStr = in.readString();
		if (!TextUtils.isEmpty(sourceStr)) {
			source = SourceParam.getValue(sourceStr);
		}
		String originStr = in.readString();
		if (!TextUtils.isEmpty(originStr)) {
			origin = SourceParam.getValue(originStr);
		}
		isSocial = in.readInt() == 1;
		iconDrawableId = in.readInt();
		isSelected = in.readInt() == 1;
		count = in.readInt();
		in.readTypedList(imagePaths, ImageData.CREATOR);
		in.readTypedList(subFolders, FolderData.CREATOR);
		isExpanded = in.readInt() == 1;
	}

	public static final Creator<FolderData> CREATOR = new Creator<FolderData>() {
		@Override
		public FolderData createFromParcel(Parcel in) {
			return new FolderData(in);
		}

		@Override
		public FolderData[] newArray(int size) {
			return new FolderData[size];
		}
	};

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(folderName);
		dest.writeString(folderPath);
		dest.writeString(folderIconPath);
		dest.writeString(folderIconUrl);
		dest.writeString(keyword);
		dest.writeString(source == null ? "" : source.name());
		dest.writeString(origin == null ? "" : origin.name());
		dest.writeInt(isSocial ? 1 : 0);
		dest.writeInt(iconDrawableId);
		dest.writeInt(isSelected ? 1 : 0);
		dest.writeInt(count);
		dest.writeTypedList(imagePaths);
		dest.writeTypedList(subFolders);
		dest.writeInt(isExpanded ? 1 : 0);
	}

	public List<FolderData> getSubFolders() {
		return subFolders;
	}

	public void setSubFolders(List<FolderData> subFolders) {
		this.subFolders.clear();
		this.subFolders = new ArrayList<>();
		this.subFolders.addAll(subFolders);
	}

	public void addSubFolders(List<FolderData> subFolders) {
		this.subFolders.addAll(subFolders);
	}

//	public boolean isUserSocial() {
//		if(TextUtils.isEmpty(folderPath)){
//			return false;
//		}
//		if (ImagePickerConstants.FOLDER_VK.equals(folderPath) || ImagePickerConstants.FOLDER_DROPBOX.equals(folderPath) || ImagePickerConstants.FOLDER_PICSART.equals(folderPath) || ImagePickerConstants.FOLDER_FLICKR.equals(folderPath) ||
//				ImagePickerConstants.FOLDER_FACEBOOK.equals(folderPath) || folderPath.startsWith(ImagePickerConstants.FOLDER_FACEBOOK_SUB)  || ImagePickerConstants.FOLDER_INSTAGRAM.equals(folderPath)) {
//			return true;
//		}
//		return false;
//	}

	public FolderData getSubitem(String folderPath){
		if(!TextUtils.isEmpty(folderPath)) {
			for (FolderData subItem : subFolders) {
				if (subItem.folderPath.equals(folderPath)) {
					return subItem;
				}
			}
		}
		return null;
	}
}


