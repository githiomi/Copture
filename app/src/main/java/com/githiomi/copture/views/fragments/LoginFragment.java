package com.githiomi.copture.views.fragments;

import static android.content.Context.MODE_PRIVATE;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static android.widget.Toast.LENGTH_LONG;
import static com.githiomi.copture.utils.CommonMethods.extractText;
import static com.githiomi.copture.utils.CommonMethods.setIsRequiredField;
import static com.githiomi.copture.utils.CommonMethods.setNotValidField;
import static com.githiomi.copture.utils.Constants.ACCOUNTS;
import static com.githiomi.copture.utils.Constants.LOGGED_IN_USER;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.githiomi.copture.R;
import com.githiomi.copture.data.models.TrafficOfficer;
import com.githiomi.copture.databinding.FragmentLoginBinding;
import com.githiomi.copture.utils.Animations;
import com.githiomi.copture.utils.Constants;
import com.githiomi.copture.views.activities.MainActivity;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Optional;

public class LoginFragment extends Fragment {

    LinearLayout toRegisterText, pageHeader;
    RelativeLayout loginButton;
    ProgressBar progressBar;
    AppCompatButton loginCompatButton;
    TextInputLayout badgeNumberInputField, passwordInputField;
    Animations animations;

    // Shared Preferences
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedPreferencesEditor;

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

        // Init shared preferences
        this.sharedPreferences = requireContext().getSharedPreferences(Constants.AUTH_SHARED_PREFERENCES, MODE_PRIVATE);
        this.sharedPreferencesEditor = this.sharedPreferences.edit();
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
            new Handler().postDelayed(this::performLogin, 1000);
        });

        return fragmentLoginBinding.getRoot();
    }

    private void performLogin() {

        // Remove errors
        if (this.badgeNumberInputField.isErrorEnabled())
            this.badgeNumberInputField.setErrorEnabled(false);
        if (this.passwordInputField.isErrorEnabled())
            this.passwordInputField.setErrorEnabled(false);

        // Extract data from text input field
        String badgeNumber = extractText(this.badgeNumberInputField).toUpperCase();
        String password = extractText(this.passwordInputField);

        System.out.println("Badge Number: " + badgeNumber);
        System.out.println("Password: " + password);

        if (badgeNumber.isEmpty()) {
            setIsRequiredField(this.badgeNumberInputField);
            toggleLoadingStop();
        }
        if (password.isEmpty()) {
            setIsRequiredField(this.passwordInputField);
            toggleLoadingStop();
        }

        if (badgeNumber.length() < 6) {
            setNotValidField(this.badgeNumberInputField);
            toggleLoadingStop();
        }
        if (password.length() < 6) {
            setNotValidField(this.passwordInputField);
            toggleLoadingStop();
        }

        validateCredentials(badgeNumber, password);
    }

    private void validateCredentials(String badgeNumber, String password) {

        // Check if user exists
        Optional<TrafficOfficer> account = ACCOUNTS.stream().filter(acc -> acc.getOfficerBadgeNumber().equals(badgeNumber)).findFirst();

        if (!account.isPresent()) {
            Toast.makeText(requireContext(), "No user exists for badge number " + badgeNumber, LENGTH_LONG).show();
            toggleLoadingStop();
            return;
        }

        // Check if password is correct
        if (!account.get().getPassword().equals(password)) {
            Toast.makeText(requireContext(), "Incorrect password. Please try again", LENGTH_LONG).show();
            toggleLoadingStop();
            return;
        }

        // Save logged in user in shared preferences
        this.sharedPreferencesEditor.putString(LOGGED_IN_USER, account.get().getOfficerBadgeNumber()).apply();

        // Stop loading state
        toggleLoadingStop();

        // Navigate to Home Fragment
        requireActivity().startActivity(new Intent(requireContext(), MainActivity.class));

    }

    private void bindViews(FragmentLoginBinding root) {
        this.pageHeader = root.LLPageHeader;
        this.badgeNumberInputField = root.ILBadgeNumber;
        this.passwordInputField = root.ILLoginPassword;
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
