package com.githiomi.copture.views.fragments;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.amazonaws.mobileconnectors.s3.transferutility.TransferState.COMPLETED;
import static com.amazonaws.mobileconnectors.s3.transferutility.TransferState.FAILED;
import static com.githiomi.copture.utils.AWS.DRIVERS_LICENSES_S3_BUCKET_PATH;
import static com.githiomi.copture.utils.AWS.S3_BUCKET_NAME;
import static com.githiomi.copture.utils.Constants.ARG_IMAGE_BITMAP;
import static com.githiomi.copture.utils.Constants.ARG_LICENSE_NUMBER;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.services.s3.AmazonS3Client;
import com.githiomi.copture.R;
import com.githiomi.copture.data.interfaces.ActivityDataPasser;
import com.githiomi.copture.databinding.FragmentLicenseBinding;
import com.githiomi.copture.utils.Animations;
import com.google.android.material.textfield.TextInputLayout;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

public class LicenseFragment extends Fragment {

    // Layout
    AppCompatImageView imagePreview;
    LinearLayout loadingLayout, errorLayout, extractedDataLayout;
    AppCompatImageButton retakeButton, errorRetakeButton;
    TextInputLayout driverName, driverLicenseNumber, driverDob;
    AppCompatButton confirmButton;


    // Data
    Animations animations;
    private Bitmap imageBitmap;
    private String licenseNumber, driverNames;
    ActivityResultLauncher<Intent> imageCaptureIntent;

    // Interfaces
    ActivityDataPasser activityDataPasser;

    public LicenseFragment() {
        // Required empty public constructor
    }

    public static LicenseFragment newInstance(Bitmap imageBitmap, String licenseNumber) {
        LicenseFragment licenseFragment = new LicenseFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_IMAGE_BITMAP, imageBitmap);
        args.putString(ARG_LICENSE_NUMBER, licenseNumber);
        licenseFragment.setArguments(args);
        return licenseFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            imageBitmap = getArguments().getParcelable(ARG_IMAGE_BITMAP);
            licenseNumber = getArguments().getString(ARG_LICENSE_NUMBER);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentLicenseBinding fragmentLicenseBinding = FragmentLicenseBinding.inflate(inflater, container, false);

        // Init animations
        animations = new Animations(getContext());

        // Bind Views
        inflateViews(fragmentLicenseBinding);

        // attach animations
        attachAnimations();

        // Set the image in preview
        setAndUploadImage();

        // set up data passer
        this.activityDataPasser = (ActivityDataPasser) requireContext();

        // Init activity for result intent
        this.imageCaptureIntent = registerForActivityResult(
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
                        Toast.makeText(getContext(), "Image recapture failed", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        // Click Listeners
        this.retakeButton.setOnClickListener(view -> {
            this.toggleLoadingState();
            System.out.println("Clicked the retake button");
        });
        this.errorRetakeButton.setOnClickListener(view -> {
            this.toggleLoadingState();
            System.out.println("Clicked the error retake button");

            // redo the image capture and upload process
            retakeImage();
        });
        this.confirmButton.setOnClickListener(view -> {
            String name = "yovin poorun ";
            this.sendDataBackToParent(name);
        });

        return fragmentLicenseBinding.getRoot();
    }

    private void retakeImage(){

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        try {
            if (intent.resolveActivity(requireActivity().getPackageManager()) != null) {
                imageCaptureIntent.launch(intent);
            }
        } catch (ActivityNotFoundException e) {
            System.out.println("Image Capture Error: " + e.getLocalizedMessage());
            Toast.makeText(getContext(), "Could not open camera. Please enable and try again", Toast.LENGTH_SHORT).show();
        }

    }

    private void replaceFragment(Bitmap imageBitmap){
        requireActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.FL_createActivityFragmentContainer, LicenseFragment.newInstance(imageBitmap, licenseNumber))
                .setReorderingAllowed(true)
                .commit();
    }

    private void sendDataBackToParent(String data){
        String[] names = data.split(" ");
        StringBuilder finalName = new StringBuilder();
        for (String name : names) {
            finalName.append(name.substring(0, 1).toUpperCase()).append(name.substring(1)).append(" ");
        }
        this.activityDataPasser.passData("Final Full Name -> " + finalName);
    }

    private void uploadImageToS3() {
        String imageFilename = licenseNumber + "_driverslicense.jpg";
        File imageFile = bitmapToFile(imageBitmap, imageFilename);

        TransferUtility transferUtility = TransferUtility.builder()
                .context(requireContext())
                .awsConfiguration(AWSMobileClient.getInstance().getConfiguration())
                .s3Client(new AmazonS3Client(AWSMobileClient.getInstance()))
                .defaultBucket(S3_BUCKET_NAME)
                .build();

        TransferObserver uploadObserver = transferUtility.upload(
                DRIVERS_LICENSES_S3_BUCKET_PATH + imageFilename,
                imageFile
        );

        uploadObserver.setTransferListener(new TransferListener() {
            @Override
            public void onStateChanged(int id, TransferState state) {
                if (COMPLETED == state) {
                    toggleSuccessState();
                    Toast.makeText(getContext(), "Driver's License Uploaded Successfully", Toast.LENGTH_SHORT).show();
                } else if (FAILED == state)
                    Toast.makeText(getContext(), "Driver's License Upload failed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onProgressChanged(int id, long bytesCurrent, long bytesTotal) {
                // Progress notification if needed
            }

            @Override
            public void onError(int id, Exception ex) {
                // Error handling
                toggleErrorState();
                System.out.println("Upload observer error -> " + ex.getLocalizedMessage());
            }
        });
    }

    private File bitmapToFile(Bitmap bitmap, String fileName) {
        File filesDir = requireContext().getFilesDir();
        File imageFile = new File(filesDir, fileName);

        try (FileOutputStream fos = new FileOutputStream(imageFile)) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
        } catch (IOException e) {
            System.out.println("Error converting bitmap to file: " + e.getLocalizedMessage());
        }

        return imageFile;
    }

    private void setAndUploadImage() {
        if (imageBitmap != null) {
            imagePreview.setImageBitmap(imageBitmap);
            // Wait then upload image to S3
            new Handler().postDelayed(this::uploadImageToS3, 2000);
        } else
            Toast.makeText(getContext(), "There was an error setting the image", Toast.LENGTH_LONG).show();
    }

    private void toggleSuccessState() {
        this.loadingLayout.setVisibility(GONE);
        this.extractedDataLayout.setVisibility(VISIBLE);

        new Handler().postDelayed(() -> {
            Objects.requireNonNull(this.driverName.getEditText()).setText("daniel githiomi");
            Objects.requireNonNull(this.driverLicenseNumber.getEditText()).setText(licenseNumber);
            Objects.requireNonNull(this.driverDob.getEditText()).setText("27.08.2001");

            this.confirmButton.setEnabled(true);
        }, 1000);
    }

    private void toggleErrorState() {
        this.loadingLayout.setVisibility(GONE);
        this.errorLayout.setVisibility(VISIBLE);
    }

    private void toggleLoadingState() {
        this.errorLayout.setVisibility(GONE);
        this.extractedDataLayout.setVisibility(GONE);
        this.loadingLayout.setVisibility(VISIBLE);
    }

    private String getEditTextData(TextInputLayout textInputLayout) {
        return Objects.requireNonNull(textInputLayout.getEditText()).getText().toString();
    }

    private void inflateViews(FragmentLicenseBinding root) {
        this.imagePreview = root.IVScanPreview;
        this.loadingLayout = root.LLLoadingLayout;
        this.retakeButton = root.BTNRetakeButton;
        this.errorRetakeButton = root.BTNErrorRetakeButton;
        this.errorLayout = root.LLErrorLayout;
        this.extractedDataLayout = root.LLExtractedDataPreview;
        this.driverName = root.ILDriversName;
        this.driverLicenseNumber = root.ILLicenseNumber;
        this.driverDob = root.ILDriversDateOfBirth;
        this.confirmButton = root.CBConfirmDataTicket;
    }

    private void attachAnimations() {
        this.retakeButton.setAnimation(this.animations.getFromRightAnimation());
        this.extractedDataLayout.setAnimation(this.animations.getFromBottomAnimation());
    }
}
