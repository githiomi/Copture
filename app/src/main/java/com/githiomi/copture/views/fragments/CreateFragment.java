package com.githiomi.copture.views.fragments;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.githiomi.copture.R;
import com.githiomi.copture.data.adapters.ScanAdapter;
import com.githiomi.copture.data.interfaces.RecyclerViewItemClickListener;
import com.githiomi.copture.data.models.ScanItem;
import com.githiomi.copture.databinding.FragmentCreateBinding;
import com.githiomi.copture.utils.Animations;

import java.util.List;

import lombok.NonNull;

public class CreateFragment extends Fragment implements RecyclerViewItemClickListener<ScanItem> {

    // Layouts
    Animations animations;
    TextView errorText;
    LottieAnimationView scanAnimationView;
    RecyclerView createNewTicketRecyclerView;
    AppCompatButton submitTicketButton;

    // Data
    ActivityResultLauncher<Intent> imageCaptureLauncher;

    public CreateFragment() {
    }

    public static CreateFragment newInstance() {
        return new CreateFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentCreateBinding fragmentCreateBinding = FragmentCreateBinding.inflate(inflater, container, false);

        // Init activity
        this.imageCaptureLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // Handle the image result here
                        assert result.getData() != null;
                        Bundle extras = result.getData().getExtras();
                        assert extras != null;
                        Bitmap imageBitmap = (Bitmap) extras.get("data");
                        // Redirect to a new fragment and pass the image
                        replaceFragment(imageBitmap);
                    } else {
                        Toast.makeText(getContext(), "Image capture failed", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        // Init animations
        this.animations = new Animations(getContext());

        // Inflate views
        inflateViews(fragmentCreateBinding);

        // Attach animations
        attachAnimations();

        // setAdapter
        setAdapter();

        // Listeners
        this.submitTicketButton.setOnClickListener(view -> {
            if (!this.submitTicketButton.isEnabled()) {
                System.out.println("From disabled");
                this.errorText.setVisibility(VISIBLE);
                return;
            }

            if (this.submitTicketButton.isEnabled()) this.errorText.setVisibility(GONE);
            Toast.makeText(getContext(), "Submitted Ticket", Toast.LENGTH_LONG).show();
        });

        return fragmentCreateBinding.getRoot();
    }

    private void replaceFragment(Bitmap imageBitmap) {
        new ImageFragment();
        requireActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.FL_mainActivityFragmentContainer, ImageFragment.newInstance(imageBitmap))
                .setReorderingAllowed(true)
                .commit();
    }

    @Override
    public void setOnRecyclerItemClick(int recyclerViewPosition, List<ScanItem> recyclerViewItems, View itemView) {
//        Intent intent = new Intent().setType("image/*").setAction(Intent.ACTION_GET_CONTENT);

        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.CAMERA}, 101);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        try {
            if (intent.resolveActivity(requireActivity().getPackageManager()) != null) {
                imageCaptureLauncher.launch(intent);
            }
        } catch (ActivityNotFoundException e) {
            System.out.println("Image Capture Error: " + e.getLocalizedMessage());
            Toast.makeText(getContext(), "Could not open camera", Toast.LENGTH_SHORT).show();
        }


//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//
//        try {
//            if (intent.resolveActivity(requireActivity().getPackageManager()) != null) {
//                startActivity(intent);
//            }
//            requireActivity().startActivity(intent);
//        } catch (ActivityNotFoundException e) {
//            System.out.println("Could not open camera");
//            Toast.makeText(getContext(), "Could not open camera", Toast.LENGTH_SHORT).show();
//        }
    }

    private void setAdapter() {
        ScanAdapter scanAdapter = new ScanAdapter(getContext(), this);
        this.createNewTicketRecyclerView.setAdapter(scanAdapter);
        this.createNewTicketRecyclerView.setHasFixedSize(true);
        this.createNewTicketRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void inflateViews(FragmentCreateBinding root) {
        this.scanAnimationView = root.LAScanAnimation;
        this.errorText = root.TVErrorRequiredFields;
        this.submitTicketButton = root.CBCreateNewTicket;
        this.createNewTicketRecyclerView = root.RVCreateNewTicket;
    }

    private void attachAnimations() {
        this.scanAnimationView.setAnimation(this.animations.getFromTopAnimation());
        this.submitTicketButton.setAnimation(this.animations.getFromRightAnimation());
        this.createNewTicketRecyclerView.setAnimation(this.animations.getFromBottomAnimation());
    }
}
