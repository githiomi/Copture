package com.githiomi.copture.views.fragments;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static android.widget.Toast.LENGTH_LONG;
import static com.githiomi.copture.utils.Constants.NATIONALITIES;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
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
import com.githiomi.copture.databinding.FragmentTicketBinding;
import com.githiomi.copture.utils.Animations;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import lombok.NonNull;

public class TicketFragment extends Fragment implements RecyclerViewItemClickListener<ScanItem> {

    // Layouts
    Animations animations;
    TextView errorText;
    LottieAnimationView scanAnimationView;
    RecyclerView createNewTicketRecyclerView;
    AppCompatButton submitTicketButton;
    CardView preliminaryData;
    TextInputLayout driversLicenseNumber, driversName;
    MultiAutoCompleteTextView driversOffenses;
    AutoCompleteTextView driversNationality;

    // Data
    String capturedLicenseNumber = "";
    ActivityResultLauncher<Intent> imageCaptureLauncher;
    List<String> nationalities = new ArrayList<String>(NATIONALITIES);
    List<String> offenses = new ArrayList<String>(
            Arrays.asList("Driving under the influence", "Over Speeding", "Phone while driving")
    );

    public TicketFragment() {
    }

    public static TicketFragment newInstance() {
        return new TicketFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentTicketBinding fragmentTicketBinding = FragmentTicketBinding.inflate(inflater, container, false);

        // Init capture Activity
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
        inflateViews(fragmentTicketBinding);

        // Attach animations
        attachAnimations();

        // set Adapters
        setAdapter();
        setDropDownAdapters();

        // Listeners
        this.submitTicketButton.setOnClickListener(view -> {
            if (!this.submitTicketButton.isEnabled()) {
                System.out.println("From disabled");
                this.errorText.setVisibility(VISIBLE);
                return;
            }

            if (this.submitTicketButton.isEnabled()) this.errorText.setVisibility(GONE);
            Toast.makeText(getContext(), "Submitted Ticket", LENGTH_LONG).show();
        });

        return fragmentTicketBinding.getRoot();
    }

    private void replaceFragment(Bitmap imageBitmap) {
        requireActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.FL_createActivityFragmentContainer, LicenseFragment.newInstance(imageBitmap, capturedLicenseNumber))
                .setReorderingAllowed(true)
                .commit();
    }

    @Override
    public void setOnRecyclerItemClick(int recyclerViewPosition, List<ScanItem> recyclerViewItems, View itemView) {

        // Get the driver's license number
        if (capturedLicenseNumber.isEmpty()) {
            this.capturedLicenseNumber = Objects.requireNonNull(this.driversLicenseNumber.getEditText()).getText().toString().toLowerCase();

            // Check for valid license number
            if (!isLicenceNumberValid(capturedLicenseNumber)) {
                Toast.makeText(requireContext(), "Please enter a valid license number!", LENGTH_LONG).show();

                // Set Input Field Error
                this.driversLicenseNumber.setErrorEnabled(true);
                this.driversLicenseNumber.setError("Not a valid license number");
                return;
            }
        }

        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.CAMERA}, 101);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        try {
            if (intent.resolveActivity(requireActivity().getPackageManager()) != null) {
                imageCaptureLauncher.launch(intent);
            }
        } catch (ActivityNotFoundException e) {
            System.out.println("Image Capture Error: " + e.getLocalizedMessage());
            Toast.makeText(getContext(), "Could not open camera. Please enable and try again", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isLicenceNumberValid(String licenseNumber) {
        return licenseNumber.length() > 6;
    }

    private void setAdapter() {
        ScanAdapter scanAdapter = new ScanAdapter(getContext(), this);
        this.createNewTicketRecyclerView.setAdapter(scanAdapter);
        this.createNewTicketRecyclerView.setHasFixedSize(true);
        this.createNewTicketRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void setDropDownAdapters() {
        this.driversOffenses.setAdapter(new ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, this.offenses));
        this.driversOffenses.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        this.driversNationality.setAdapter(new ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, this.nationalities));
    }

    private void inflateViews(FragmentTicketBinding root) {
        this.scanAnimationView = root.LAScanAnimation;
        this.errorText = root.TVErrorRequiredFields;
        this.submitTicketButton = root.CBCreateNewTicket;
        this.preliminaryData = root.CVDriverPreliminaryData;
        this.createNewTicketRecyclerView = root.RVCreateNewTicket;
        this.driversLicenseNumber = root.ILLicenseNumber;
        this.driversOffenses = root.MACTVOffences;
        this.driversNationality = root.MACTVNationality;
    }

    private void attachAnimations() {
        this.scanAnimationView.setAnimation(this.animations.getFromTopAnimation());
        this.submitTicketButton.setAnimation(this.animations.getFromRightAnimation());
        this.preliminaryData.setAnimation(this.animations.getFromBottomAnimation());
    }
}
