package com.example.arthurakhnoyan.photochooserdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.arthurakhnoyan.photochooserdemo.R;
import com.example.arthurakhnoyan.photochooserdemo.callback.RecyclerViewPaginationCallback;
import com.example.arthurakhnoyan.photochooserdemo.listiner.ImageItemClickListener;
import com.example.arthurakhnoyan.photochooserdemo.model.ImageData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arthurakhnoyan on 8/2/16.
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

	private List<ImageData> items;
	private Context context;
	private int imageSize;
	private ImageItemClickListener imageItemClickListener;
	private static final String TAG = "ImageAdapter";
	private RecyclerViewPaginationCallback paginationCallback;
	private int nextPageLoadPositionOffset = 20;

	public ImageAdapter(Context context, int imageSize) {
		this.context = context;
		this.imageSize = imageSize;
		items = new ArrayList<>();
	}

	@Override
	public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//		Log.d(TAG, "onCreateViewHolder: ");
		View itemView = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.image_row_item, parent, false);

		itemView.getLayoutParams().width = imageSize;
		itemView.getLayoutParams().height = imageSize;

		return new ImageViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(ImageViewHolder holder, final int position) {
		if (items.size() > 0) {
			final ImageData imageData = items.get(position);
			holder.imageView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					imageItemClickListener.onItemClick(imageData, position);
				}
			});
			Glide.with(context).load((imageData.isFromLocalFile() && !imageData.getImagePath().startsWith("http") ? "file://" : "") + imageData.getThumbnailPath()).into(holder.imageView);

			if (position >= getItemCount() - nextPageLoadPositionOffset && paginationCallback != null) {
				paginationCallback.onNextPageReached();
			}
		}
	}

	public void setItemClickListener(ImageItemClickListener imageItemClickListener) {
		this.imageItemClickListener = imageItemClickListener;
	}

	@Override
	public int getItemCount() {
		return items.size();
	}

	public void addItem(ImageData imageData) {
		items.add(imageData);
		if (imageData.getImagePath().equals("camera")) {
			notifyDataSetChanged();
		}
		notifyItemInserted(items.size() - 1);
	}

	public void setPaginationCallback(RecyclerViewPaginationCallback paginationCallback) {
		this.paginationCallback = paginationCallback;
	}

	public void clear() {
		items.clear();
		Log.d(TAG, "clear: yes");
	}

	public void append(List<ImageData> items, int offset, int limit) {
		this.items.addAll(items);
		notifyItemRangeChanged(offset,limit);
	}

	public class ImageViewHolder extends RecyclerView.ViewHolder {
		public ImageView imageView;

		public ImageViewHolder(View view) {
			super(view);
			imageView = (ImageView) view.findViewById(R.id.image);
		}
	}
}
