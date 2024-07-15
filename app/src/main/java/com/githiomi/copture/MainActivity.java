package com.githiomi.copture;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.githiomi.copture.databinding.ActivityMainBinding;
import com.githiomi.copture.utils.Animations;
import com.githiomi.copture.views.activities.AuthActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    // Layout
    RelativeLayout mainContent, mainData;
    Animations animations;
    CircleImageView officerProfilePicture;
    RecyclerView recyclerView;
    LinearLayout loadingLayout, newEntry;
    CardView navigationCardView;
    BottomNavigationView bottomNavigationView;

    // Data
    List<String> entries;

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
        setAnimations();

        // Set OnClick Listeners
        this.officerProfilePicture.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, AuthActivity.class)));

        // Data
        this.entries = new ArrayList<>();

        new Handler().postDelayed(this::toggleData, 2000);

        if (entries.isEmpty()) showNewEntryLayout();
    }

    private void inflateViews(ActivityMainBinding root) {
        this.mainContent = root.RLMainContent;
        this.mainData = root.RLMainData;
        this.loadingLayout = root.LLLoadingLayout;
        this.officerProfilePicture = root.IVProfilePicture;
        this.recyclerView = root.RVEntries;
        this.newEntry = root.LLCreateNewTicket;
        this.navigationCardView = root.CVBottomNavigation;
        this.bottomNavigationView = root.NVBottomVanigationView;
    }

    private void setAnimations() {
        this.mainContent.setAnimation(this.animations.getFromRightAnimation());
        this.officerProfilePicture.setAnimation(this.animations.getFromBottomAnimation());
        this.navigationCardView.setAnimation(this.animations.getFromTopAnimation());
    }

    private void showNewEntryLayout() {
        this.recyclerView.setVisibility(GONE);
        this.newEntry.setVisibility(VISIBLE);
    }

    private void toggleData() {
        this.loadingLayout.setVisibility(GONE);
        this.mainData.setVisibility(VISIBLE);
    }

}
