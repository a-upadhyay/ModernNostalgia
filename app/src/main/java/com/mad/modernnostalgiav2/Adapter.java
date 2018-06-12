package com.mad.modernnostalgiav2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private Context mContext;
    private ArrayList<Image> mImages;
    public static final String TAG = "MESSAGE";

    public Adapter(Context context, ArrayList<Image> imageList) {
        this.mContext = context;
        this.mImages = imageList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.photo_cardview_layout, parent, false);

        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // holder.img_placeholder.setScaleType(ImageView.ScaleType.FIT_CENTER);
        //holder.img_placeholder.setImageResource(mImages.get(position).getImageID());

        Image thisList = mImages.get(position);
        holder.setData(thisList, position);

    }


    @Override
    public int getItemCount() {
        return mImages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img_placeholder;
        ImageView addImage;
        ImageView deleteImage;

        private Image imageObject;
        private int position;

        public ViewHolder(View itemView) {
            super(itemView);

            img_placeholder = itemView.findViewById(R.id.image_placeholder);

            addImage = itemView.findViewById(R.id.add_icon);
            addImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "onClick: add works");
                    addImage(position, imageObject);
                }
            });

            deleteImage = itemView.findViewById(R.id.delete_icon);
            deleteImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "onClick: delete works");
                    deleteItem(position);
                }
            });
        }

        public void setData(Image imageObject, int position) {
            //  this.img_placeholder.setScaleType(ImageView.ScaleType.FIT_CENTER);
            this.img_placeholder.setImageResource(imageObject.getImageID());
            this.position = position;
            this.imageObject = imageObject;
        }
    }

    public void addImage(int position, Image currentObject) {
        mImages.add(position, currentObject);
        notifyItemInserted(position);
        notifyItemRangeChanged(position, mImages.size());
        // notifyDataSetChanged();


    }

    public void deleteItem(int position) {
        mImages.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mImages.size());
        // notifyDataSetChanged();
    }
}
