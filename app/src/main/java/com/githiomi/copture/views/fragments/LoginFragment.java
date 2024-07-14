package com.githiomi.copture.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.githiomi.copture.R;
import com.githiomi.copture.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {

    LinearLayout toRegisterText, pageHeader;
    RelativeLayout loginButton;
    Animation fromRightAnimation, fromLeftAnimation;

    public LoginFragment() {
    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
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
        FragmentLoginBinding fragmentLoginBinding = FragmentLoginBinding.inflate(inflater, container, false);

        // Init animations
        this.fromRightAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.right_animation);
        this.fromLeftAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.left_animation);

        // Bind Views
        bindViews(fragmentLoginBinding);

        // Bind Animations
        bindAnimation();

        this.toRegisterText.setOnClickListener( view ->
                requireActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.FL_authenticationFragmentContainer, new RegisterFragment())
                        .setReorderingAllowed(true)
                        .commit()
        );

        return fragmentLoginBinding.getRoot();
    }

    private void bindAnimation(){
        this.pageHeader.setAnimation(this.fromLeftAnimation);
        this.loginButton.setAnimation(this.fromRightAnimation);
    }

    private void bindViews(FragmentLoginBinding root) {
        this.pageHeader = root.LLPageHeader;
        this.toRegisterText = root.LLToRegister;
        this.loginButton = root.RLLoginButton;
    }

}
