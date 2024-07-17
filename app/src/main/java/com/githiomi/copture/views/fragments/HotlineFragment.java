package com.githiomi.copture.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.githiomi.copture.databinding.FragmentHotlineBinding;
import com.githiomi.copture.utils.Animations;

public class HotlineFragment extends Fragment {

    // Layout
    Animations animations;
    RelativeLayout policeCard, lawyerCard, medicalCard, fireFighterCard;

    public HotlineFragment() {
        // Required empty public constructor
    }

    public static HotlineFragment newInstance() {
        return new HotlineFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentHotlineBinding fragmentHotlineBinding = FragmentHotlineBinding.inflate(inflater, container, false);

        // Init animations
        this.animations = new Animations(getContext());

        // Bind Views
        bindViews(fragmentHotlineBinding);

        // Attach Animations
        attachAnimations();

        return fragmentHotlineBinding.getRoot();
    }

    /**
     * Method to bind views
     *
     * @param root The root layout
     */
    private void bindViews(FragmentHotlineBinding root) {
        this.policeCard = root.RLPoliceCard;
        this.lawyerCard = root.RLLawyerCard;
        this.medicalCard = root.RLMedicalCard;
        this.fireFighterCard = root.RLFireFighterCard;
    }

    /**
     * Method to attach animations to views
     */
    private void attachAnimations() {
        this.policeCard.setAnimation(this.animations.getFromTopAnimation());
        this.lawyerCard.setAnimation(this.animations.getFromRightAnimation());
        this.medicalCard.setAnimation(this.animations.getFromLeftAnimation());
        this.fireFighterCard.setAnimation(this.animations.getFromBottomAnimation());
    }
}
