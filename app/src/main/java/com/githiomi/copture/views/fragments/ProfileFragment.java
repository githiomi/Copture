package com.githiomi.copture.views.fragments;

import static android.content.Context.MODE_PRIVATE;
import static com.githiomi.copture.utils.Constants.AUTH_SHARED_PREFERENCES;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.githiomi.copture.databinding.FragmentProfileBinding;
import com.githiomi.copture.utils.Animations;
import com.githiomi.copture.utils.Constants;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {

    // Layout
    Animations animations;
    CircleImageView profilePicture;
    LinearLayout badgeCards;
    RelativeLayout logoutButton;

    // Shared Preferences
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedPreferencesEditor;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(String param1, String param2) {
        return new ProfileFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.sharedPreferences = requireActivity().getSharedPreferences(AUTH_SHARED_PREFERENCES, MODE_PRIVATE);
        this.sharedPreferencesEditor = this.sharedPreferences.edit();
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

        // On Click Listener
        this.profilePicture.setOnClickListener(view -> {
            logout();
        });

        return fragmentProfileBinding.getRoot();
    }

    private void logout() {
        // Remove data from shared preferences
        this.sharedPreferencesEditor.remove(Constants.LOGGED_IN_USER).apply();

        // Redirect to login page
        requireActivity().finish();
        requireActivity().startActivity(requireActivity().getIntent());
    }

    /**
     * Method to attach animations to the layout
     *
     * @param root The FragmentBinding ref to the root layout
     */
    private void inflateViews(FragmentProfileBinding root) {
        this.profilePicture = root.IVProfilePicture;
        this.badgeCards = root.LLBadgeCards;
        this.logoutButton = root.RLLogoutButton;
    }

    private void attachAnimations() {
        this.profilePicture.setAnimation(this.animations.getFromTopAnimation());
        this.badgeCards.setAnimation(this.animations.getFromBottomAnimation());
    }
}
