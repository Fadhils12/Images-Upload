package com.example.imageupload.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imageupload.R;
import com.example.imageupload.StorageActivity;
import com.example.imageupload.model.Upload;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder>{
    private Context mContext;
    private List<Upload> mUploads;
    private OnItemClickListener mListener;

    public ImageAdapter(Context context, List<Upload> uploads) {
        this.mContext = context;
        this.mUploads = uploads;
        this.mListener = (StorageActivity) context;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.row_item, parent, false);
        ImageViewHolder viewHolder = new ImageViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Upload uploadCurrent = mUploads.get(position);
        holder.tvTitle.setText(uploadCurrent.getName());
        holder.tvDesc.setText(uploadCurrent.getDesc());

        holder.imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null){
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext, R.style.CustomAlertDialog);
                    builder.setMessage("Kau akan kehilangan satu koleksimu");
                    builder.setPositiveButton("Ya, tidak mengapa", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mListener.onDeleteClick(position);
                        }
                    });
                    builder.setNegativeButton("Tidak, aku tak ingin kehilangannya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });
            Picasso.with(mContext)
                    .load(uploadCurrent.getImageUrl())
                    .fit()
                    .centerCrop()
                    .into(holder.imgView);

    }


    @Override
    public int getItemCount() {
        return mUploads.size();
    }



    public class ImageViewHolder extends RecyclerView.ViewHolder{
        public TextView tvTitle;
        public TextView tvDesc;
        public ImageView imgView;

        public ImageView imgDel;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDesc = itemView.findViewById(R.id.tv_desc);
            imgView = itemView.findViewById(R.id.img_view_upload);

            imgDel = itemView.findViewById(R.id.btn_deleted_image);

//            itemView.setOnClickListener(this);
//            itemView.setOnCreateContextMenuListener(this);
        }

//        @Override
//        public void onClick(View v) {
//            if (mListener != null){
//                int position = getAdapterPosition();
//                if (position != RecyclerView.NO_POSITION){
//                    mListener.onDeleteClick(position);
//                }
//            }
//        }

//        @Override
//        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//            menu.setHeaderTitle("Select Action");
//            MenuItem doWhatever = menu.add(Menu.NONE, 1, 1, "Do whatever");
//            MenuItem delete = menu.add(Menu.NONE, 2, 2, "Delete");
//
//            doWhatever.setOnMenuItemClickListener(this);
//            delete.setOnMenuItemClickListener(this);
//        }

//        @Override
//        public boolean onMenuItemClick(MenuItem item) {
//            if (mListener != null) {
//                int position = getAdapterPosition();
//                if (position != RecyclerView.NO_POSITION) {
//                    switch (item.getItemId()) {
//                        case 1:
//                            mListener.onWhatEverClick(position);
//                            return true;
//                        case 2:
//                            mListener.onDeleteClick(position);
//                            return true;
//                    }
//                }
//            }
//            return false;
//        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
        void onWhatEverClick(int position);
        void onDeleteClick(int position);

    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }
}