package com.githiomi.copture.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.githiomi.copture.R;
import com.githiomi.copture.databinding.FragmentLoginBinding;
import com.githiomi.copture.utils.Animations;

public class LoginFragment extends Fragment {

    LinearLayout toRegisterText, pageHeader;
    RelativeLayout loginButton;
    Animations animations;

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
        this.animations = new Animations(getContext());

        // Bind Views
        bindViews(fragmentLoginBinding);

        // Bind Animations
        bindAnimation();

        this.toRegisterText.setOnClickListener(view ->
                requireActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.FL_authenticationFragmentContainer, new RegisterFragment())
                        .setReorderingAllowed(true)
                        .commit()
        );

        return fragmentLoginBinding.getRoot();
    }

    private void bindAnimation() {
        this.pageHeader.setAnimation(this.animations.getFromLeftAnimation());
        this.loginButton.setAnimation(this.animations.getFromRightAnimation());
    }

    private void bindViews(FragmentLoginBinding root) {
        this.pageHeader = root.LLPageHeader;
        this.toRegisterText = root.LLToRegister;
        this.loginButton = root.RLLoginButton;
    }

}
