package com.githiomi.copture.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.githiomi.copture.data.adapters.HistoryAdapter;
import com.githiomi.copture.data.models.CitationTicket;
import com.githiomi.copture.databinding.FragmentHistoryBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HistoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HistoryFragment extends Fragment {

    // Layout
    RecyclerView historyRecyclerView;

    public HistoryFragment() {
        // Required empty public constructor
    }

    public static HistoryFragment newInstance() {
        return new HistoryFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout
        FragmentHistoryBinding fragmentHistoryBinding = FragmentHistoryBinding.inflate(inflater, container, false);

        // Init views
        initiateViews(fragmentHistoryBinding);

        // Set Adapter
        setupAdapter();


        return fragmentHistoryBinding.getRoot();
    }

    private void setupAdapter() {
        List<CitationTicket> tickets = new ArrayList<CitationTicket>(
                Collections.singletonList(
                        new CitationTicket(
                                "CT001",
                                "38583672",
                                new ArrayList<String>(
                                        Arrays.asList("DUI", "No Seat Belt")
                                ),
                                "MTP2001",
                                3500
                        )
                )
        );
        HistoryAdapter historyAdapter = new HistoryAdapter(requireContext(), tickets);
        this.historyRecyclerView.setAdapter(historyAdapter);
        this.historyRecyclerView.setHasFixedSize(true);
        this.historyRecyclerView.setLayoutManager(new androidx.recyclerview.widget.LinearLayoutManager(requireContext()));
    }

    private void initiateViews(FragmentHistoryBinding root) {
        historyRecyclerView = root.RVHistory;
    }
}
