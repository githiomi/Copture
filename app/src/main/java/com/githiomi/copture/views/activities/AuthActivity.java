package com.githiomi.copture.views.activities;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.githiomi.copture.R;
import com.githiomi.copture.databinding.ActivityAuthBinding;
import com.githiomi.copture.views.fragments.LoginFragment;

public class AuthActivity extends AppCompatActivity {

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

        // View Binding
        bindViews(activityAuthBinding);

        // Inflate
        getSupportFragmentManager()
                .beginTransaction()
                .replace(authenticationFragmentContainer.getId(), new LoginFragment())
                .commit();
    }

    private void bindViews(ActivityAuthBinding root){
        this.authenticationFragmentContainer = root.FLAuthenticationFragmentContainer;
    }
}
