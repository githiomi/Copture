package com.githiomi.copture.data.adapters;

import static android.view.View.GONE;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.RecyclerView;

import com.githiomi.copture.R;
import com.githiomi.copture.data.interfaces.RecyclerViewItemClickListener;
import com.githiomi.copture.data.models.Hotline;

import java.util.List;

public class HotlineAdapter extends RecyclerView.Adapter<HotlineAdapter.HotlineViewHolder> {

    // Adapter Dependencies
    Context context;
    List<Hotline> hotlines;
    RecyclerViewItemClickListener<Hotline> recyclerViewItemClickListener;

    public HotlineAdapter(Context context, List<Hotline> hotlines, RecyclerViewItemClickListener<Hotline> recyclerViewItemClickListener) {
        this.context = context;
        this.hotlines = hotlines;
        this.recyclerViewItemClickListener = recyclerViewItemClickListener;
    }

    @NonNull
    @Override
    public HotlineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HotlineViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.hotline_recycler_view_item, parent, false), recyclerViewItemClickListener, this.hotlines);
    }

    @Override
    public void onBindViewHolder(@NonNull HotlineViewHolder holder, int position) {
        // Current Hotline
        Hotline hotline = this.hotlines.get(position);

        // Remove separator from last item
        if (position == hotlines.size() - 1) holder.itemSeparator.setVisibility(GONE);

        // Set data to the view
        holder.hotlineDescription.setText(hotline.getHotlineDescription());
    }

    @Override
    public int getItemCount() {
        return hotlines.size();
    }

    public static class HotlineViewHolder extends RecyclerView.ViewHolder {

        // Layout of the recycler view item to manipulate
        TextView hotlineDescription;
        AppCompatImageButton hotlineCallIcon;
        View itemSeparator;

        public HotlineViewHolder(@NonNull View itemView, RecyclerViewItemClickListener<Hotline> listener, List<Hotline> hotlines) {
            super(itemView);
            this.hotlineDescription = itemView.findViewById(R.id.TV_HotlineItemText);
            this.hotlineCallIcon = itemView.findViewById(R.id.IB_HotlineItemButton);
            this.itemSeparator = itemView.findViewById(R.id.V_itemSeparator);

            // OnClick listener
            itemView.setOnClickListener(view -> {
                if (listener != null)
                    listener.setOnRecyclerItemClick(getAdapterPosition(), hotlines, itemView);
            });
        }
    }
}
