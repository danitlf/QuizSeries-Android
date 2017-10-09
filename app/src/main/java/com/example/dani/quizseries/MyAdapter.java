package com.example.dani.quizseries;

import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dani.quizseries.models.Serie;

import java.util.List;

/**
 * Created by dani on 20/08/17.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Serie> mDataset;



    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public RelativeLayout row;
        public ImageView icon;
        public View ourView;
        public ViewHolder(View v) {
            super(v);
            ourView = v;
            mTextView = (TextView) v.findViewById(R.id.serieName);
            icon = (ImageView) v.findViewById(R.id.iconSerieList);
            row = v.findViewById(R.id.rowSerie);

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<Serie> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.serie_row, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(mDataset.get(position).getName());


////      Glide.with(holder.ourView).load("https://firebasestorage.googleapis.com/v0/b/quiz-series-b1cd4.appspot.com/o/friends.png?alt=media&token=0dbf2fd0-17b6-4f1e-9bd0-9026c3b658c1").into(holder.icon);
//
        Glide.with(holder.ourView).load(mDataset.get(position).getIcon_url()).into(holder.icon);
//        Glide.with(holder.ourView).load("https://firebasestorage.googleapis.com/v0/b/quiz-series-b1cd4.appspot.com/o/friends.png?alt=media&token=0dbf2fd0-17b6-4f1e-9bd0-9026c3b658c1").into(holder.icon);



        holder.row.setBackgroundColor(Color.parseColor(mDataset.get(position).getCor()));

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}


