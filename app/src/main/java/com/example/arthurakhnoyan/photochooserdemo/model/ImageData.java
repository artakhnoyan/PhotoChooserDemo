package com.example.arthurakhnoyan.photochooserdemo.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.example.arthurakhnoyan.photochooserdemo.SourceParam;

import java.io.File;

/**
 * Created by arthurakhnoyan on 8/2/16.
 */
public class ImageData implements Parcelable {
	private String id;
	private String originPath;
	private String imagePath;
	private String thumbnailPath;
	private int orientation;
	private String folderPath;
	private boolean selected = false;
	private int selectionsCount = 0;
	private boolean fromLocalFile = false;
	private boolean isPicsartRecent = false;
	private long lastModified = 0;
	private SourceParam source = SourceParam.GALLERY;
	private boolean inDeleteMode = false;
//	private ImageItem picsartImageItem = null;

	private boolean fromCamera = false;

	public int getSelectionsCount() {
		return selectionsCount;
	}

	public void incrementSelectionsCount() {
		selectionsCount++;
	}

	public void decrementSelectionsCount() {
		selectionsCount--;
	}

	public ImageData() {
	}

	public void setSelectionsCount(int selectionsCount) {
		this.selectionsCount = selectionsCount;
	}

	public void setFromCamera(boolean fromCamera) {
		this.fromCamera = fromCamera;
	}

	public boolean isFromCamera() {
		return fromCamera;
	}

	protected ImageData(Parcel in) {
		id = in.readString();
		originPath = in.readString();
		imagePath = in.readString();
		thumbnailPath = in.readString();
		orientation = in.readInt();
		folderPath = in.readString();
		selected = in.readInt() == 1;
		fromLocalFile = in.readInt() == 1;
		isPicsartRecent = in.readInt() == 1;
		lastModified = in.readLong();
		String sourceStr = in.readString();
		if (!TextUtils.isEmpty(sourceStr)) {
			source = SourceParam.getValue(sourceStr);
		}
//		picsartImageItem = in.readParcelable(ImageItem.class.getClassLoader());
	}

	public static final Creator<ImageData> CREATOR = new Creator<ImageData>() {
		@Override
		public ImageData createFromParcel(Parcel in) {
			return new ImageData(in);
		}

		@Override
		public ImageData[] newArray(int size) {
			return new ImageData[size];
		}
	};

	public String getImagePath() {
		return imagePath;
	}

	public void setInDeleteMode(boolean inDeleteMode) {
		this.inDeleteMode = inDeleteMode;
	}

	public boolean isInDeleteMode() {
		return inDeleteMode;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
		this.folderPath = null;
		if (!TextUtils.isEmpty(imagePath)) {
			if (imagePath.contains(File.separator)) {
				setFolderPath(imagePath.substring(0, imagePath.lastIndexOf(File.separator)));
			}
		}
	}

	public String getThumbnailPath() {
		return thumbnailPath;
	}

	public void setThumbnailPath(String thumbnailPath) {
		this.thumbnailPath = thumbnailPath;
	}

	public int getOrientation() {
		return orientation;
	}

	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}

	public String getFolderPath() {
		return folderPath;
	}

	public void setFolderPath(String folderPath) {
		this.folderPath = folderPath;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public boolean isSelected() {
		return selected;
	}

	public boolean isFromLocalFile() {
		return fromLocalFile;
	}

	public void setFromLocalFile(boolean fromLocalFile) {
		this.fromLocalFile = fromLocalFile;
	}

	public long getLastModified() {
		return lastModified;
	}

	public void setLastModified(long lastModified) {
		this.lastModified = lastModified;
	}

	/**
	 * should be set to true, if image taken from picsarts recent folder
	 *
	 * @param isPicsartRecent
	 */
	public void setIsPicsartRecent(boolean isPicsartRecent) {
		this.isPicsartRecent = isPicsartRecent;
	}


	/**
	 * @return the value which been set with method {@link ImageData#setIsPicsartRecent(boolean)} . Returns false by default.
	 */
	public boolean isPicsartRecent() {
		return isPicsartRecent;
	}

	public void setSource(SourceParam source) {
		this.source = source;
	}

	public SourceParam getSource() {
		return source;
	}

	public boolean isFromByteBuffer() {
		return isPicsartRecent && imagePath.contains("_w") && imagePath.contains("_h");
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOriginPath() {

		if (TextUtils.isEmpty(originPath)) {
			return imagePath;
		}
		return originPath;
	}

	public void setOriginPath(String originPath) {
		this.originPath = originPath;
	}

	@Override
	public int describeContents() {
		return 0;
	}

//	public void setPicsArtImageItem(ImageItem picsartImageItem) {
//		this.picsartImageItem = picsartImageItem;
//	}

//	public ImageItem getPicsArtImageItem() {
//		return picsartImageItem;
//	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(id);
		dest.writeString(originPath);
		dest.writeString(imagePath);
		dest.writeString(thumbnailPath);
		dest.writeInt(orientation);
		dest.writeString(folderPath);
		dest.writeInt(selected ? 1 : 0);
		dest.writeInt(fromLocalFile ? 1 : 0);
		dest.writeInt(isPicsartRecent ? 1 : 0);
		dest.writeLong(lastModified);
		dest.writeString(source == null ? "" : source.name());
//		dest.writeParcelable(picsartImageItem, flags);
	}

}

