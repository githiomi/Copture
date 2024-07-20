package com.githiomi.copture.views.fragments;

import static android.content.Intent.ACTION_DIAL;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.githiomi.copture.data.adapters.HotlineAdapter;
import com.githiomi.copture.data.enums.HotlineContacts;
import com.githiomi.copture.data.interfaces.RecyclerViewItemClickListener;
import com.githiomi.copture.data.models.Hotline;
import com.githiomi.copture.databinding.FragmentHotlineBinding;
import com.githiomi.copture.utils.Animations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HotlineFragment extends Fragment implements RecyclerViewItemClickListener<Hotline> {

    // Layout
    Animations animations;
    RelativeLayout policeCard, lawyerCard, medicalCard, fireFighterCard;
    RecyclerView policeRecyclerView, lawyerRecyclerView, medicalRecyclerView, fireFighterRecyclerView;

    public HotlineFragment() {
        // Required empty public constructor
    }

    public static HotlineFragment newInstance() {
        return new HotlineFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentHotlineBinding fragmentHotlineBinding = FragmentHotlineBinding.inflate(inflater, container, false);

        // Init animations
        this.animations = new Animations(getContext());

        // Bind Views
        bindViews(fragmentHotlineBinding);

        // Attach Animations
        attachAnimations();

        // On click listeners
        this.policeCard.setOnClickListener(view -> cardOnClickListener(policeRecyclerView, "police"));
        this.lawyerCard.setOnClickListener(view -> cardOnClickListener(lawyerRecyclerView, "lawyer"));
        this.medicalCard.setOnClickListener(view -> cardOnClickListener(medicalRecyclerView, "medical"));
        this.fireFighterCard.setOnClickListener(view -> cardOnClickListener(fireFighterRecyclerView, "firefighter"));

        return fragmentHotlineBinding.getRoot();
    }

    /**
     * Method to bind views
     *
     * @param root The root layout
     */
    private void bindViews(FragmentHotlineBinding root) {
        this.policeCard = root.RLPoliceCard;
        this.policeRecyclerView = root.RVPoliceDetails;
        this.lawyerCard = root.RLLawyerCard;
        this.lawyerRecyclerView = root.RVLawyerDetails;
        this.medicalCard = root.RLMedicalCard;
        this.medicalRecyclerView = root.RVMedicalDetails;
        this.fireFighterCard = root.RLFireFighterCard;
        this.fireFighterRecyclerView = root.RVFireFighterDetails;
    }

    /**
     * Method to attach animations to views
     */
    private void attachAnimations() {
        this.policeCard.setAnimation(this.animations.getFromTopAnimation());
        this.lawyerCard.setAnimation(this.animations.getFromRightAnimation());
        this.medicalCard.setAnimation(this.animations.getFromLeftAnimation());
        this.fireFighterCard.setAnimation(this.animations.getFromBottomAnimation());
    }

    /**
     * Method to set an on click listener to the hotline card vies
     *
     * @param recyclerView the respective recycler view for the category
     * @param category     the category of the card view
     */
    private void cardOnClickListener(RecyclerView recyclerView, String category) {
        int visibility = recyclerView.getVisibility() == GONE ? VISIBLE : GONE;
        recyclerView.setVisibility(visibility);

        if (visibility == VISIBLE) {
            // Get data from the hotline contact enum
            setAdapter(recyclerView, getAdapterData(category));
            recyclerView.setAnimation(new Animations(getContext()).getFromTopAnimation());
        }
    }

    /**
     * Method to get the respective hotline contact data
     *
     * @param hotlineCategory The category for which to get the contact data
     * @return the list of category contact data
     */
    private List<Hotline> getAdapterData(String hotlineCategory) {

        List<Hotline> adapterData;

        switch (hotlineCategory) {
            case "police":
                adapterData = new ArrayList<>(Arrays.asList(
                        HotlineContacts.DIRECT.getHotline(),
                        HotlineContacts.CID.getHotline(),
                        HotlineContacts.POLICE_INFO.getHotline(),
                        HotlineContacts.NORTH.getHotline(),
                        HotlineContacts.SOUTH.getHotline(),
                        HotlineContacts.CENTRAL.getHotline(),
                        HotlineContacts.EAST.getHotline(),
                        HotlineContacts.WEST.getHotline()
                ));
                break;
            case "lawyer":
                adapterData = new ArrayList<>(Arrays.asList(
                        HotlineContacts.SUPREME.getHotline(),
                        HotlineContacts.MAGISTRATE.getHotline()
                ));
                break;
            case "medical":
                adapterData = new ArrayList<>(Arrays.asList(
                        HotlineContacts.SAMU.getHotline(),
                        HotlineContacts.C_CARE.getHotline(),
                        HotlineContacts.SSR.getHotline()
                ));
                break;
            case "firefighter":
                adapterData = new ArrayList<>(Arrays.asList(
                        HotlineContacts.FIRE_SERVICES_1.getHotline(),
                        HotlineContacts.FIRE_SERVICES_2.getHotline(),
                        HotlineContacts.RAPID_RESPONSE.getHotline()
                ));
                break;
            default:
                adapterData = new ArrayList<>();
                break;
        }
        return adapterData;
    }

    /**
     * Method to set adapter to the recycler view
     */
    private void setAdapter(RecyclerView recyclerView, List<Hotline> hotlines) {
        HotlineAdapter hotlineAdapter = new HotlineAdapter(getContext(), hotlines, this);
        recyclerView.setAdapter(hotlineAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    /**
     * Method to open call application when item is clicked
     *
     * @param recyclerViewPosition this is the position of the item clicked
     * @param hotlines             the hotlines in the respective recycler view
     */
    @Override
    public void setOnRecyclerItemClick(int recyclerViewPosition, List<Hotline> hotlines, View itemView) {

        try {
            requireActivity().startActivity(
                            new Intent(ACTION_DIAL, Uri.parse("tel:" + hotlines.get(recyclerViewPosition).getHotlineNumber()))
            );
        } catch (ActivityNotFoundException e) {
            System.out.println("Activity not found exception: " + e.getLocalizedMessage());
            Toast.makeText(getContext(), "Call app not found", Toast.LENGTH_SHORT).show();
        }
    }
}
