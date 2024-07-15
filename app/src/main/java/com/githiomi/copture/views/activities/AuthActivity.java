package com.githiomi.copture.views.activities;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.githiomi.copture.R;
import com.githiomi.copture.databinding.ActivityAuthBinding;
import com.githiomi.copture.utils.Animations;
import com.githiomi.copture.views.fragments.LoginFragment;
import com.githiomi.copture.views.fragments.RegisterFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AuthActivity extends AppCompatActivity {

    Animations animations;
    FrameLayout authenticationFragmentContainer;

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

    private void bindViews(ActivityAuthBinding root){
        this.authenticationFragmentContainer = root.FLAuthenticationFragmentContainer;
    }
}
