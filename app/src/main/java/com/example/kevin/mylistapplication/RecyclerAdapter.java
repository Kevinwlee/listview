package com.example.kevin.mylistapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;


/**
 * Created by kevin on 5/9/16.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> implements ItemTouchHelperAdapter {

    private ItemStore mDataSet;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        private final Context context;
        public TextView mTextView;
        public ViewHolder(View v) {
            super(v);

            context = v.getContext();

            v.setClickable(true);
            v.setOnClickListener(this);
            mTextView = (TextView) v.findViewById(R.id.info_text);;
        }

        @Override
        public void onClick(View v) {
            TextView textView = (TextView)v.findViewById(R.id.info_text);
            Log.d("DEBUG", "clicked: " + getAdapterPosition() + " " + textView.getText());

            Intent detail = new Intent(context, ItemDetailActivity.class);
            detail.putExtra("itemIndexKey", getAdapterPosition());
            context.startActivity(detail);

        }

    }


    // Provide a suitable constructor (depends on the kind of dataset)
    public RecyclerAdapter(ItemStore myDataSet) {
        mDataSet = myDataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_text_view, parent, false);
        // set the view's size, margins, padding and layout parameters

        v.setBackgroundColor(Color.GREEN);

        v.findViewById(R.id.info_text);
        RecyclerAdapter.ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(mDataSet.getItemAtIndex(position));

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataSet.items().size();
    }

    //Item Touch Helper
    @Override
    public boolean onItemDismiss(int position) {
        mDataSet.items().remove(position);
        notifyItemRemoved(position);
        return true;
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(mDataSet.items(), i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(mDataSet.items(), i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }
}
