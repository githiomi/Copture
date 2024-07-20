package com.githiomi.copture.data.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.githiomi.copture.R;
import com.githiomi.copture.data.enums.ScanItems;
import com.githiomi.copture.data.interfaces.RecyclerViewItemClickListener;
import com.githiomi.copture.data.models.ScanItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScanAdapter extends RecyclerView.Adapter<ScanAdapter.ScanAdapterViewHolder> {

    // Adapter properties
    Context context;
    List<ScanItem> recyclerViewItems;
    RecyclerViewItemClickListener<ScanItem> scanItemRecyclerViewItemClickListener;

    public ScanAdapter(Context context, RecyclerViewItemClickListener<ScanItem> listener) {
        this.context = context;
        this.recyclerViewItems = getEnumData();
        this.scanItemRecyclerViewItemClickListener = listener;
    }

    private List<ScanItem> getEnumData() {
        return new ArrayList<ScanItem>(
                Arrays.asList(
                        ScanItems.DRIVERS_LICENSE.getScanItem(),
                        ScanItems.VEHICLE_NUMBER_PLATE.getScanItem(),
                        ScanItems.VEHICLE_INSURANCE.getScanItem(),
                        ScanItems.VEHICLE_FITNESS.getScanItem(),
                        ScanItems.VEHICLE_FRONT.getScanItem(),
                        ScanItems.VEHICLE_BACK.getScanItem(),
                        ScanItems.VEHICLE_SIDE.getScanItem()
                )
        );
    }

    @NonNull
    @Override
    public ScanAdapter.ScanAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ScanAdapterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.new_ticket_recycler_view_item, parent, false), context, scanItemRecyclerViewItemClickListener, recyclerViewItems);
    }

    @Override
    public void onBindViewHolder(@NonNull ScanAdapter.ScanAdapterViewHolder holder, int position) {

        // Get Current Scan Item
        ScanItem scanItem = this.recyclerViewItems.get(position);

        // Set Item Data
        holder.bind(scanItem);
    }

    @Override
    public int getItemCount() {
        return this.recyclerViewItems.size();
    }

    public static class ScanAdapterViewHolder extends RecyclerView.ViewHolder {

        // Recycler View Item Layout
        AppCompatImageButton scanItemIcon, scanStatusIcon;
        TextView scanItemText;

        public ScanAdapterViewHolder(@NonNull View itemView, Context context, RecyclerViewItemClickListener<ScanItem> listener, List<ScanItem> scanItems) {
            super(itemView);
            this.scanItemIcon = itemView.findViewById(R.id.IB_NewTicketItemButton);
            this.scanItemText = itemView.findViewById(R.id.TV_NewTicketItemText);
            this.scanStatusIcon = itemView.findViewById(R.id.IB_NewTicketArrowItemButton);

            // OnClick listener
            itemView.setOnClickListener(view -> {
                this.scanStatusIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_check));
                if (listener != null)
                    listener.setOnRecyclerItemClick(getAdapterPosition(), scanItems, this.scanStatusIcon);
            });
        }

        public void bind(ScanItem scanItem) {
            this.scanItemIcon.setImageResource(scanItem.getScanItemIcon());
            this.scanItemText.setText(scanItem.getScanItemName());
        }

    }
}
