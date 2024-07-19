package com.githiomi.copture.views.fragments;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
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
    LottieAnimationView scanAnimationView;
    RecyclerView createNewTicketRecyclerView;
    AppCompatButton createNewTicketButton;

    public CreateFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static CreateFragment newInstance(String param1, String param2) {
        return new CreateFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentCreateBinding fragmentCreateBinding = FragmentCreateBinding.inflate(inflater, container, false);

        // Init animations
        this.animations = new Animations(getContext());

        // Inflate views
        inflateViews(fragmentCreateBinding);

        // Attach animations
        attachAnimations();

        // setAdapter
        setAdapter();

        return fragmentCreateBinding.getRoot();
    }

    @Override
    public void setOnRecyclerItemClick(int recyclerViewPosition, List<ScanItem> recyclerViewItems) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        try {
            if (intent.resolveActivity(requireActivity().getPackageManager()) != null) {
                startActivity(intent);
            }
            requireActivity().startActivity(intent);
        } catch (ActivityNotFoundException e) {
            System.out.println("Could not open camera");
            Toast.makeText(getContext(), "Could not open camera", Toast.LENGTH_SHORT).show();
        }
    }

    private void setAdapter() {
        ScanAdapter scanAdapter = new ScanAdapter(getContext(), this);
        this.createNewTicketRecyclerView.setAdapter(scanAdapter);
        this.createNewTicketRecyclerView.setHasFixedSize(true);
        this.createNewTicketRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void inflateViews(FragmentCreateBinding root) {
        this.scanAnimationView = root.LAScanAnimation;
        this.createNewTicketRecyclerView = root.RVCreateNewTicket;
    }

    private void attachAnimations() {
        this.scanAnimationView.setAnimation(this.animations.getFromTopAnimation());
        this.createNewTicketRecyclerView.setAnimation(this.animations.getFromBottomAnimation());
    }
}
