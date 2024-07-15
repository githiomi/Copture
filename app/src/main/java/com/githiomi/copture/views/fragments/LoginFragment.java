package com.githiomi.copture.views.fragments;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

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

import com.githiomi.copture.R;
import com.githiomi.copture.databinding.FragmentLoginBinding;
import com.githiomi.copture.utils.Animations;

public class LoginFragment extends Fragment {

    LinearLayout toRegisterText, pageHeader;
    RelativeLayout loginButton;
    ProgressBar progressBar;
    AppCompatButton loginCompatButton;
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

        // On Click Listeners
        this.toRegisterText.setOnClickListener(view ->
                requireActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.FL_authenticationFragmentContainer, new RegisterFragment())
                        .setReorderingAllowed(true)
                        .commit()
        );

        this.loginCompatButton.setOnClickListener(view -> {
            toggleLoadingStart();
            new Handler().postDelayed(this::toggleLoadingStop, 2000);
        });

        return fragmentLoginBinding.getRoot();
    }

    private void bindViews(FragmentLoginBinding root) {
        this.pageHeader = root.LLPageHeader;
        this.loginButton = root.RLLoginButton;
        this.progressBar = root.PBLoginProgressBar;
        this.toRegisterText = root.LLToRegister;
        this.loginCompatButton = root.BTNLoginButton;
    }

    private void bindAnimation() {
        this.pageHeader.setAnimation(this.animations.getFromLeftAnimation());
        this.loginButton.setAnimation(this.animations.getFromRightAnimation());
    }

    /**
     * Method to start the button loading state
     */
    private void toggleLoadingStart() {
        this.loginButton.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.primary_loading_button));
        this.loginCompatButton.setVisibility(GONE);
        this.progressBar.setVisibility(VISIBLE);
    }

    /**
     * Method to stop the button loading state
     */
    private void toggleLoadingStop() {
        this.loginButton.setBackground(ContextCompat.getDrawable(requireContext(), R.drawable.primary_button));
        this.loginCompatButton.setVisibility(VISIBLE);
        this.progressBar.setVisibility(GONE);
    }

}
