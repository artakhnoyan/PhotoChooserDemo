package com.example.arthurakhnoyan.photochooserdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.arthurakhnoyan.photochooserdemo.R;
import com.example.arthurakhnoyan.photochooserdemo.listiner.FolderClickListener;
import com.example.arthurakhnoyan.photochooserdemo.model.FolderData;

import java.util.List;

/**
 * Created by arthurakhnoyan on 8/5/16.
 */
public class FoldersAdapter extends RecyclerView.Adapter<FoldersAdapter.FolderViewHolder> {

	private Context context;
	private List<FolderData> folderList;
	private FolderClickListener folderClickListener;

	public FoldersAdapter(Context context, List<FolderData> folderList) {
		this.context = context;
		this.folderList = folderList;
	}
	@Override
	public FolderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.folder_row_item, parent, false);
		return new FolderViewHolder(view);
	}

	@Override
	public void onBindViewHolder(FolderViewHolder holder, final int position) {
		final FolderData folder = folderList.get(position);

		holder.rowLayout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				folderClickListener.onItemClicked(folder, position);
			}
		});

		Glide.with(context).load((folder.folderIconPath != null && !folder.folderIconPath.startsWith("http") ? "file://" : "") + folder.folderIconPath).into(holder.image);
		holder.text.setText(folder.folderName +" ( "+folder.count+" )");
	}

	@Override
	public int getItemCount() {
		return folderList.size();
	}

	public void setFolderClickListener(FolderClickListener folderClickListener) {
		this.folderClickListener = folderClickListener;
	}

	public class FolderViewHolder extends RecyclerView.ViewHolder {
		public View rowLayout;
		public ImageView image;
		public TextView text;

		public FolderViewHolder(View v) {
			super(v);
			rowLayout = v.findViewById(R.id.row_layout);
			image = (ImageView) v.findViewById(R.id.folder_image);
			text = (TextView) v.findViewById(R.id.folder_name);
		}
	}
}
