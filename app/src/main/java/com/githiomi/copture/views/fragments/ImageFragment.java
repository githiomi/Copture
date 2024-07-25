package com.githiomi.copture.views.fragments;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.amazonaws.mobileconnectors.s3.transferutility.TransferState.COMPLETED;
import static com.amazonaws.mobileconnectors.s3.transferutility.TransferState.FAILED;
import static com.githiomi.copture.utils.AWS.DRIVERS_LICENSES_S3_BUCKET_PATH;
import static com.githiomi.copture.utils.AWS.S3_BUCKET_NAME;
import static com.githiomi.copture.utils.Constants.ARG_IMAGE_BITMAP;
import static com.githiomi.copture.utils.Constants.ARG_LICENSE_NUMBER;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

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
import com.githiomi.copture.databinding.FragmentImageBinding;
import com.githiomi.copture.utils.Animations;
import com.google.android.material.textfield.TextInputLayout;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

public class ImageFragment extends Fragment {

    // Layout
    AppCompatImageView imagePreview;
    LinearLayout loadingLayout, errorLayout, extractedDataLayout;
    AppCompatImageButton retakeButton, errorRetakeButton;
    TextInputLayout driverName, driverLicenseNumber, driverDob;
    AppCompatButton confirmButton;

    // Data
    Animations animations;
    private Bitmap imageBitmap;
    private String licenseNumber;

    public ImageFragment() {
        // Required empty public constructor
    }

    public static ImageFragment newInstance(Bitmap imageBitmap, String licenseNumber) {
        ImageFragment imageFragment = new ImageFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_IMAGE_BITMAP, imageBitmap);
        args.putString(ARG_LICENSE_NUMBER, licenseNumber);
        imageFragment.setArguments(args);
        return imageFragment;
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
        FragmentImageBinding fragmentImageBinding = FragmentImageBinding.inflate(inflater, container, false);

        // Init animations
        animations = new Animations(getContext());

        // Bind Views
        inflateViews(fragmentImageBinding);

        // attach animations
        attachAnimations();

        // Set the image in preview
        setAndUploadImage();

        // Click Listeners
        this.retakeButton.setOnClickListener(view -> {
            this.toggleLoadingState();
            System.out.println("Clicked the retake button");
        });
        this.errorRetakeButton.setOnClickListener(view -> {
            this.toggleLoadingState();
            System.out.println("Clicked the error retake button");
        });

        return fragmentImageBinding.getRoot();
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
            Objects.requireNonNull(this.driverName.getEditText()).setText("Daniel Githiomi");
            Objects.requireNonNull(this.driverLicenseNumber.getEditText()).setText(licenseNumber);
            Objects.requireNonNull(this.driverDob.getEditText()).setText("27.08.2001");

            if (getEditTextData(this.driverName) != null && getEditTextData(this.driverLicenseNumber) != null && getEditTextData(this.driverDob) != null)
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

    private void inflateViews(FragmentImageBinding root) {
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
