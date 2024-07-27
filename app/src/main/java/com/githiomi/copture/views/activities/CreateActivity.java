package com.githiomi.copture.views.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.Callback;
import com.amazonaws.mobile.client.UserStateDetails;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferNetworkLossHandler;
import com.githiomi.copture.R;
import com.githiomi.copture.data.interfaces.ActivityDataPasser;
import com.githiomi.copture.databinding.ActivityCreateBinding;
import com.githiomi.copture.utils.Animations;
import com.githiomi.copture.views.fragments.TicketFragment;

public class CreateActivity extends AppCompatActivity implements ActivityDataPasser {

    // Layouts
    FrameLayout createActivityFragmentContainer;

    // Animations
    Animations animations;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // View Binding
        ActivityCreateBinding activityCreateBinding = ActivityCreateBinding.inflate(getLayoutInflater());
        setContentView(activityCreateBinding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Init animations
        this.animations = new Animations(this);

        // Inflate views
        inflateViews(activityCreateBinding);

        // Set default fragment
        replaceFragmentContainer(new TicketFragment());
    }

    // Override interface method to pass data
    @Override
    public void passData(String data) {
        // Handle data passed from fragments
        System.out.println("Data received -> " + data);
    }

    private void replaceFragmentContainer(Fragment fragment) {
        this.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.FL_createActivityFragmentContainer, fragment)
                .setReorderingAllowed(true)
                .commit();
    }

    private void inflateViews(ActivityCreateBinding root) {
        this.createActivityFragmentContainer = root.FLCreateActivityFragmentContainer;
    }
}
