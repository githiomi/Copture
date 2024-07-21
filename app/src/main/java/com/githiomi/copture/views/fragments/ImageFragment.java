package com.githiomi.copture.views.fragments;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import static com.githiomi.copture.utils.Constants.ARG_IMAGE_BITMAP;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;

import com.githiomi.copture.databinding.FragmentImageBinding;
import com.githiomi.copture.utils.Animations;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class ImageFragment extends Fragment {

    // Layout
    AppCompatImageView imagePreview;
    LinearLayout loadingLayout, extractedDataLayout;
    TextInputLayout driverName, licenseNumber;

    // Data
    Animations animations;

    private Bitmap imageBitmap;

    public ImageFragment() {
        // Required empty public constructor
    }

    public static ImageFragment newInstance(Bitmap imageBitmap) {
        ImageFragment imageFragment = new ImageFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_IMAGE_BITMAP, imageBitmap);
        imageFragment.setArguments(args);
        return imageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            imageBitmap = getArguments().getParcelable(ARG_IMAGE_BITMAP);
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

        // Set Image to preview
        setImage();

        new Handler().postDelayed(this::toggleExtractedData, 3000);

        return fragmentImageBinding.getRoot();
    }

    private void setImage() {
        if (imageBitmap != null)
            imagePreview.setImageBitmap(imageBitmap);
        else
            Toast.makeText(getContext(), "There was an error setting the image", Toast.LENGTH_LONG).show();
    }

    private void toggleExtractedData() {
        this.loadingLayout.setVisibility(GONE);
        this.extractedDataLayout.setVisibility(VISIBLE);

        new Handler().postDelayed(() -> {
            Objects.requireNonNull(this.driverName.getEditText()).setText("Daniel Githiomi");
            Objects.requireNonNull(this.licenseNumber.getEditText()).setText("ABC123");
        }, 2000);
    }

    private void inflateViews(FragmentImageBinding root) {
        this.imagePreview = root.IVScanPreview;
        this.loadingLayout = root.LLLoadingLayout;
        this.extractedDataLayout = root.LLExtractedDataPreview;
        this.driverName = root.ILDriversName;
        this.licenseNumber = root.ILLicenseNumber;
    }

    private void attachAnimations() {
        this.extractedDataLayout.setAnimation(this.animations.getFromBottomAnimation());
    }
}
