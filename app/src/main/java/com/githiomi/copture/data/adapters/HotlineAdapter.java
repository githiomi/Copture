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
import com.githiomi.copture.data.models.Hotline;
import com.githiomi.copture.data.enums.HotlineContacts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HotlineAdapter extends RecyclerView.Adapter<HotlineAdapter.HotlineViewHolder> {

    // Adapter Dependencies
    Context context;
    List<Hotline> hotlines;

    public HotlineAdapter(Context context) {
        this.hotlines = new ArrayList<>(Arrays.asList(
                HotlineContacts.C_CARE.getHotline(),
                HotlineContacts.SAMU.getHotline(),
                HotlineContacts.SSR.getHotline(),
                HotlineContacts.FIRE_SERVICES_1.getHotline(),
                HotlineContacts.FIRE_SERVICES_2.getHotline(),
                HotlineContacts.RAPID_RESPONSE.getHotline()
        ));
        this.context = context;
    }

    @NonNull
    @Override
    public HotlineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HotlineViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.hotline_recycler_view_item, parent, false));
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

        public HotlineViewHolder(@NonNull View itemView) {
            super(itemView);
            this.hotlineDescription = itemView.findViewById(R.id.TV_HotlineItemText);
            this.hotlineCallIcon = itemView.findViewById(R.id.IB_HotlineItemButton);
            this.itemSeparator = itemView.findViewById(R.id.V_itemSeparator);
        }
    }
}
