package com.githiomi.copture.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.githiomi.copture.R;
import com.githiomi.copture.databinding.FragmentRegisterBinding;

public class RegisterFragment extends Fragment {

    LinearLayout toLoginText, pageHeader;
    RelativeLayout registerButton;
    Animation fromRightAnimation, fromLeftAnimation;

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
        this.fromRightAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.right_animation);
        this.fromLeftAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.left_animation);

        // Binding Views
        bindViews(fragmentRegisterBinding);

        // Bind Animations
        bindAnimations();

        // On click listeners
        this.toLoginText.setOnClickListener( view -> {
            requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.FL_authenticationFragmentContainer, new LoginFragment())
                    .setReorderingAllowed(true)
                    .commit();
        });

        return fragmentRegisterBinding.getRoot();
    }

    private void bindViews(FragmentRegisterBinding root){
        this.pageHeader = root.LLPageHeader;
        this.toLoginText = root.LLToLLogin;
        this.registerButton = root.RLRegisterButton;
    }

    private void bindAnimations() {
        this.pageHeader.setAnimation(fromLeftAnimation);
        this.registerButton.setAnimation(fromRightAnimation);
    }
}
