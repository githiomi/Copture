package com.githiomi.copture.views.fragments;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.githiomi.copture.R;
import com.githiomi.copture.databinding.FragmentHomeBinding;
import com.githiomi.copture.utils.Animations;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeFragment extends Fragment {

    // Layouts
    RelativeLayout officerProfileInfo, mainContent;
    RecyclerView recyclerView;
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

        return fragmentHomeBinding.getRoot();
    }

    private void inflateViews(FragmentHomeBinding root){
        this.officerProfileInfo = root.RLOfficerInfo;
        this.loadingLayout = root.LLLoadingLayout;
        this.mainContent = root.RLMainContent;
        this.recyclerView = root.RVEntries;
        this.newEntry = root.LLCreateNewTicket;
    }

    private void attachAnimations(){
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
