package com.githiomi.copture.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.githiomi.copture.databinding.FragmentProfileBinding;
import com.githiomi.copture.utils.Animations;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {

    // Layout
    Animations animations;
    CircleImageView profilePicture;
    LinearLayout badgeCards;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(String param1, String param2) {
        return new ProfileFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentProfileBinding fragmentProfileBinding = FragmentProfileBinding.inflate(inflater, container, false);

        // Init animations
        this.animations = new Animations(getContext());

        // Inflate views
        inflateViews(fragmentProfileBinding);

        // Attach animations
        attachAnimations();

        return fragmentProfileBinding.getRoot();
    }

    /**
     * Method to attach animations to the layout
     *
     * @param root The FragmentBinding ref to the root layout
     */
    private void inflateViews(FragmentProfileBinding root) {
        this.profilePicture = root.IVProfilePicture;
        this.badgeCards = root.LLBadgeCards;
    }

    private void attachAnimations() {
        this.profilePicture.setAnimation(this.animations.getFromTopAnimation());
        this.badgeCards.setAnimation(this.animations.getFromBottomAnimation());
    }
}
