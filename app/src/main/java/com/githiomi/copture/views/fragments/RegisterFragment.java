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
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.githiomi.copture.views.activities.MainActivity;
import com.githiomi.copture.R;
import com.githiomi.copture.databinding.FragmentRegisterBinding;
import com.githiomi.copture.utils.Animations;

public class RegisterFragment extends Fragment {

    LinearLayout toLoginText, pageHeader;
    RelativeLayout registerButton;
    AppCompatButton registerCompatButton;
    ProgressBar registerProgressBar;
    Animations animations;

    public RegisterFragment() {
    }

    public static RegisterFragment newInstance() {
        RegisterFragment fragment = new RegisterFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        FragmentRegisterBinding fragmentRegisterBinding = FragmentRegisterBinding.inflate(inflater, container, false);

        // Init Animation
        animations = new Animations(getContext());

        // Binding Views
        bindViews(fragmentRegisterBinding);

        // Bind Animations
        bindAnimations();

        // On click listeners
        this.toLoginText.setOnClickListener(view -> {
            requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.FL_authenticationFragmentContainer, new LoginFragment())
                    .setReorderingAllowed(true)
                    .commit();
        });

        this.registerCompatButton.setOnClickListener( view -> {
            toggleLoadingStart();
            new Handler().postDelayed( () -> requireActivity().startActivity(new Intent(requireActivity(), MainActivity.class)), 2000);
        });

        return fragmentRegisterBinding.getRoot();
    }

    private void bindViews(FragmentRegisterBinding root) {
        this.pageHeader = root.LLPageHeader;
        this.toLoginText = root.LLToLLogin;
        this.registerButton = root.RLRegisterButton;
        this.registerCompatButton = root.BTNRegisterButton;
        this.registerProgressBar = root.PBRegisterProgressBar;
    }

    private void bindAnimations() {
        this.pageHeader.setAnimation(animations.getFromLeftAnimation());
        this.registerButton.setAnimation(animations.getFromRightAnimation());
    }

    /**
     * Method to start button loading start
     */
    private void toggleLoadingStart(){
        this.registerButton.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.primary_loading_button));
        this.registerCompatButton.setVisibility(GONE);
        this.registerProgressBar.setVisibility(VISIBLE);
    }

    /**
     * Method to stop button loading start
     */
    private void toggleLoadingStop(){
        this.registerButton.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.primary_button));
        this.registerCompatButton.setVisibility(VISIBLE);
        this.registerProgressBar.setVisibility(GONE);
    }
}
