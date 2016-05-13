package com.example.kevin.mylistapplication;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;


/**
 * Created by kevin on 5/9/16.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> implements IItemTouchHelperAdapter {

    private EntryStore mDataSet;
    public IRecyclerClickHandler clickHandler;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView mTitleTextView;
        public TextView mCountTextView;
        public TextView mSectionTitleTextView;
        IRecyclerClickHandler clickHandler;

        public ViewHolder(View v, IRecyclerClickHandler handler) {
            super(v);

            clickHandler = handler;

            if (v.getId() == R.id.section_text) {
                mSectionTitleTextView = (TextView) v.findViewById(R.id.section_text);
            } else {
                v.setClickable(true);
                v.setOnClickListener(this);

                mTitleTextView = (TextView) v.findViewById(R.id.info_text);
                mCountTextView = (TextView) v.findViewById(R.id.count_text);
            }

        }

        @Override
        public void onClick(View v) {
            clickHandler.onClick(getAdapterPosition());
        }
    }


    public RecyclerAdapter(EntryStore myDataSet, IRecyclerClickHandler recyclerClickHandler) {
        mDataSet = myDataSet;
        clickHandler = recyclerClickHandler;
    }

    @Override
    public int getItemViewType(int position) {
        Entry entry = mDataSet.getItemAtIndex(position);
        return entry.isSection ? 1 : 0;
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        View v;
        if (viewType == 0) {
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.my_text_view, parent, false);
            v.setBackgroundColor(Color.BLUE);
        } else {
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.section, parent, false);
            v.setBackgroundColor(Color.GREEN);
        }

        RecyclerAdapter.ViewHolder vh = new ViewHolder(v, clickHandler);
        return vh;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Entry entry = mDataSet.getItemAtIndex(position);
        if (entry.isSection) {
            holder.mSectionTitleTextView.setText(entry.name);
        } else {
            holder.mTitleTextView.setText(entry.name);
            holder.mCountTextView.setText(String.valueOf(entry.amount));
        }
    }

    @Override
    public int getItemCount() {
        return mDataSet.items().size();
    }

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
