package com.githiomi.copture.views.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.githiomi.copture.R;
import com.githiomi.copture.databinding.ActivityAuthBinding;
import com.githiomi.copture.utils.Animations;
import com.githiomi.copture.utils.Constants;
import com.githiomi.copture.views.fragments.LoginFragment;

public class AuthActivity extends AppCompatActivity {

    Animations animations;
    FrameLayout authenticationFragmentContainer;

    // Shared preferences
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedPreferencesEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        ActivityAuthBinding activityAuthBinding = ActivityAuthBinding.inflate(getLayoutInflater());
        setContentView(activityAuthBinding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Init shared preferences
        this.sharedPreferences = getSharedPreferences(Constants.AUTH_SHARED_PREFERENCES, MODE_PRIVATE);
        this.sharedPreferencesEditor = this.sharedPreferences.edit();

        // Animations
        this.animations = new Animations(this);

        // View Binding
        bindViews(activityAuthBinding);

        // Animation Binding
        bindAnimations();

        // Inflate
        getSupportFragmentManager()
                .beginTransaction()
                .replace(authenticationFragmentContainer.getId(), new LoginFragment())
                .commit();
    }

    private void bindAnimations() {
        this.authenticationFragmentContainer.setAnimation(this.animations.getFromBottomAnimation());
    }

    private void bindViews(ActivityAuthBinding root) {
        this.authenticationFragmentContainer = root.FLAuthenticationFragmentContainer;
    }

    @Override
    public void onStart() {
        super.onStart();

        String loggedInUserBadge = this.sharedPreferences.getString(Constants.LOGGED_IN_USER, "");

        if (!loggedInUserBadge.isEmpty()) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

}
