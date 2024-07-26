package com.githiomi.copture.views.activities;

import static com.githiomi.copture.R.id.FL_mainActivityFragmentContainer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import com.githiomi.copture.R;
import com.githiomi.copture.data.interfaces.ApiService;
import com.githiomi.copture.data.models.Offence;
import com.githiomi.copture.databinding.ActivityMainBinding;
import com.githiomi.copture.utils.Animations;
import com.githiomi.copture.views.fragments.HistoryFragment;
import com.githiomi.copture.views.fragments.HomeFragment;
import com.githiomi.copture.views.fragments.HotlineFragment;
import com.githiomi.copture.views.fragments.ProfileFragment;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    // Layout
    FrameLayout mainActivityFragmentContainer;
    BottomAppBar bottomAppBar;
    BottomNavigationView bottomNavigationView;
    FloatingActionButton createFab;
    Animations animations;

    // Data
    ApiService apiService;

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

        // Get data from AWS DynamoDB
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://5pxwfv65c6.execute-api.us-east-1.amazonaws.com/production/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
        getDynamoDBData();

        // Attach views
        getSupportFragmentManager()
                .beginTransaction()
                .replace(FL_mainActivityFragmentContainer, new HomeFragment())
                .commit();

        //  Listeners
        this.createFab.setOnClickListener(view -> startActivity(new Intent(this, CreateActivity.class)));

        this.bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.bottom_home)
                replaceFragment(new HomeFragment());
            else if (itemId == R.id.bottom_history)
                replaceFragment(new HistoryFragment());
            else if (itemId == R.id.bottom_call)
                replaceFragment(new HotlineFragment());
            else if (itemId == R.id.bottom_profile)
                replaceFragment(new ProfileFragment());
            else
                return false;

            return true;
        });
    }

    private void getDynamoDBData(){

        System.out.println("Getting Data");

        Call<List<Offence>> call = this.apiService.getOffences();
        call.enqueue(new Callback<List<Offence>>() {
            @Override
            public void onResponse(Call<List<Offence>> call, Response<List<Offence>> response) {
                System.out.println(response);
                if (response.isSuccessful()) {
                    List<Offence> offences = response.body();
                    System.out.println("Offences -> " + offences);
                } else {
                    // Handle the error
                    System.out.println("Error finding offences! " + response.message() + " Call -> " + call);
                }
            }

            @Override
            public void onFailure(Call<List<Offence>> call, Throwable t) {
                System.out.println("error completely -> " + t.getLocalizedMessage());
            }
        });

    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(FL_mainActivityFragmentContainer, fragment)
                .setReorderingAllowed(true)
                .commit();
    }

    private void inflateViews(ActivityMainBinding root) {
        this.mainActivityFragmentContainer = root.FLMainActivityFragmentContainer;
        this.bottomAppBar = root.BBBottomAppBar;
        this.bottomNavigationView = root.NVBottomNavigationView;
        this.createFab = root.FABCreate;
    }

    private void attachAnimations() {
        this.mainActivityFragmentContainer.setAnimation(this.animations.getFromTopAnimation());
        this.bottomAppBar.setAnimation(this.animations.getFromBottomAnimation());
    }

}
