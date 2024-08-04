package com.githiomi.copture.data.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.githiomi.copture.R;
import com.githiomi.copture.data.models.CitationTicket;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    // Adapter Fields
    Context context;
    List<CitationTicket> citationTicketList;

    public HistoryAdapter(Context context, List<CitationTicket> citationTicketList) {
        this.context = context;
        this.citationTicketList = citationTicketList;
    }

    @NonNull
    @Override
    public HistoryAdapter.HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HistoryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.history_recycler_view_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.HistoryViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return this.citationTicketList.size();
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder {

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
