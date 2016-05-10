package com.example.kevin.mylistapplication;

/**
 * Created by kevin on 5/10/16.
 */
public interface ItemTouchHelperAdapter {
    boolean onItemMove(int fromPosition, int toPosition);

    boolean onItemDismiss(int position);
}
