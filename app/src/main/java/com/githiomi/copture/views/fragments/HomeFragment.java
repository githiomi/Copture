package com.githiomi.copture.views.fragments;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.githiomi.copture.R;
import com.githiomi.copture.databinding.FragmentHomeBinding;
import com.githiomi.copture.utils.Animations;
import com.githiomi.copture.views.activities.CreateActivity;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    // Layouts
    RelativeLayout officerProfileInfo, mainContent;
    RecyclerView recyclerView;
    AppCompatButton createNewTicketButton;
    LinearLayout loadingLayout, newEntry;

    // Data
    Animations animations;
    List<String> entries;

    public HomeFragment() {
        this.entries = new ArrayList<>();
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentHomeBinding fragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false);

        // Inflate views
        inflateViews(fragmentHomeBinding);

        // Init animations
        this.animations = new Animations(getContext());

        // Set animations
        attachAnimations();

        new Handler().postDelayed(this::toggleData, 2000);

        if (entries.isEmpty()) showNewEntryLayout();

        // Listeners
        this.createNewTicketButton.setOnClickListener(view -> requireActivity().startActivity(new Intent(requireContext(), CreateActivity.class)));

        return fragmentHomeBinding.getRoot();
    }

    private void replaceFragment(Fragment fragment) {
        requireActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.FL_mainActivityFragmentContainer, fragment)
                .setReorderingAllowed(true)
                .commit();
    }

    private void inflateViews(FragmentHomeBinding root) {
        this.officerProfileInfo = root.RLOfficerInfo;
        this.loadingLayout = root.LLLoadingLayout;
        this.mainContent = root.RLMainContent;
        this.recyclerView = root.RVEntries;
        this.newEntry = root.LLCreateNewTicket;
        this.createNewTicketButton = root.BTNNewTicket;
    }

    private void attachAnimations() {
        this.officerProfileInfo.setAnimation(this.animations.getFromTopAnimation());
        this.mainContent.setAnimation(animations.getFromRightAnimation());
    }

    private void showNewEntryLayout() {
        this.recyclerView.setVisibility(GONE);
        this.newEntry.setVisibility(VISIBLE);
    }

    private void toggleData() {
        this.loadingLayout.setVisibility(GONE);
        this.mainContent.setVisibility(VISIBLE);
    }

}
