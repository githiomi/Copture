package com.githiomi.copture.data.interfaces;

import android.view.View;

import java.util.List;

public interface RecyclerViewItemClickListener<T> {

    void setOnRecyclerItemClick(int recyclerViewPosition, List<T> recyclerViewItems, View itemView);

}
