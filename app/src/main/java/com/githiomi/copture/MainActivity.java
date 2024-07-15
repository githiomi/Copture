package com.githiomi.copture;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.githiomi.copture.databinding.ActivityMainBinding;
import com.githiomi.copture.utils.Animations;
import com.githiomi.copture.views.fragments.CreateFragment;
import com.githiomi.copture.views.fragments.HistoryFragment;
import com.githiomi.copture.views.fragments.HomeFragment;
import com.githiomi.copture.views.fragments.HotlineFragment;
import com.githiomi.copture.views.fragments.ProfileFragment;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    // Layout
    FrameLayout mainActivityFragmentContainer;
    BottomAppBar bottomAppBar;
    BottomNavigationView bottomNavigationView;
    Animations animations;

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

        // Inflate views
        inflateViews(activityMainBinding);

        // Init animations
        animations = new Animations(this);

        // Set animations
        attachAnimations();

        // Attach views
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.FL_mainActivityFragmentContainer, new HomeFragment())
                .commit();

        // Navigation Listener
        this.bottomNavigationView.setOnItemSelectedListener( item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.bottom_home)
                replaceFragment(new HomeFragment());
            else if (itemId == R.id.bottom_history)
                replaceFragment(new HistoryFragment());
            else if (itemId == R.id.bottom_create)
                replaceFragment(new CreateFragment());
            else if (itemId == R.id.bottom_call)
                replaceFragment(new HotlineFragment());
            else if (itemId == R.id.bottom_profile)
                replaceFragment(new ProfileFragment());
            else
                return false;

            return true;
        });
    }

    private void replaceFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.FL_mainActivityFragmentContainer, fragment)
                .setReorderingAllowed(true)
                .commit();
    }

    private void inflateViews(ActivityMainBinding root) {
        this.mainActivityFragmentContainer = root.FLMainActivityFragmentContainer;
        this.bottomAppBar = root.BBBottomAppBar;
        this.bottomNavigationView = root.NVBottomNavigationView;
    }

    private void attachAnimations() {
        this.mainActivityFragmentContainer.setAnimation(this.animations.getFromTopAnimation());
        this.bottomAppBar.setAnimation(this.animations.getFromBottomAnimation());
    }

}
