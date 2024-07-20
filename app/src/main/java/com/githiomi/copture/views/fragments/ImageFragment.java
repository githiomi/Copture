package com.githiomi.copture.views.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;

import com.githiomi.copture.databinding.FragmentImageBinding;

public class ImageFragment extends Fragment {

    // Layout
    AppCompatImageView imagePreview;

    private static final String ARG_IMAGE_BITMAP = "image_bitmap";
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

        // Bind Views
        bindViews(fragmentImageBinding);

        // Set Image to preview
        setImage();

        return fragmentImageBinding.getRoot();
    }

    private void setImage() {
        if (imageBitmap != null)
            imagePreview.setImageBitmap(imageBitmap);
        else
            Toast.makeText(getContext(), "There was an error setting the image", Toast.LENGTH_LONG).show();
    }

    private void bindViews(FragmentImageBinding root) {
        this.imagePreview = root.IVScanPreview;
    }
}
