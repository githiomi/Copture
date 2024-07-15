package com.githiomi.copture;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.githiomi.copture.databinding.ActivityMainBinding;
import com.githiomi.copture.utils.Animations;
import com.githiomi.copture.views.activities.AuthActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    CircleImageView officerProfilePicture;
    Animations animations;
    CardView navigationCardView;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        ActivityMainBinding activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inflate vies
        inflateViews(activityMainBinding);

        // Init animations
        animations = new Animations(this);

        // Set animations
        setAnimations();

        // Set OnClick Listeners
        this.officerProfilePicture.setOnClickListener( view -> startActivity(new Intent(MainActivity.this, AuthActivity.class)));
    }

    private void inflateViews(ActivityMainBinding root) {
        this.officerProfilePicture = root.IVProfilePicture;
        this.navigationCardView = root.CVBottomNavigation;
        this.bottomNavigationView = root.NVBottomVanigationView;
    }

    private void setAnimations() {
        this.officerProfilePicture.setAnimation(this.animations.getFromBottomAnimation());
        this.navigationCardView.setAnimation(this.animations.getFromTopAnimation());
    }

}
