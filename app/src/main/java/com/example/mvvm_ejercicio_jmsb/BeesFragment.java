package com.example.mvvm_ejercicio_jmsb;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvvm_ejercicio_jmsb.adapter.BeesAdapter;
import com.example.mvvm_ejercicio_jmsb.databinding.FragmentBeesBinding;
import com.example.mvvm_ejercicio_jmsb.model.Bee;

import java.util.ArrayList;
import java.util.List;

public class BeesFragment extends Fragment {

    private FragmentBeesBinding binding;
    private BeesAdapter beesAdapter;
    private BeesViewModel beesViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBeesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        beesViewModel = new ViewModelProvider(requireActivity()).get(BeesViewModel.class);

        beesAdapter = new BeesAdapter(getContext(), new ArrayList<>(), bee -> beesViewModel.updateBee(bee));

        binding.recyclerViewBees.setAdapter(beesAdapter);
        binding.recyclerViewBees.setLayoutManager(new GridLayoutManager(requireContext(), 2));

        beesViewModel.bees.observe(getViewLifecycleOwner(), beeList -> {
            String currentQuery = binding.searchView.getQuery().toString();
            if (currentQuery.isEmpty()) {
                beesAdapter.establecerLista(beeList);
            } else {
                filterByCommonName(currentQuery);
            }
        });

        if (beesViewModel.bees.getValue() == null || beesViewModel.bees.getValue().isEmpty()) {
            beesViewModel.getBees();
        }

        binding.searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterByCommonName(newText);
                return true;
            }
        });
    }

    private void filterByCommonName(java.lang.String query) {
        List<Bee> fullList = beesViewModel.getAllBees();

        if (fullList == null) return;

        List<Bee> filtered = new ArrayList<>();
        String lowerQuery = query.toLowerCase();

        for (Bee bee : fullList) {
            String name = getString(bee.getCommonName()).toLowerCase();

            if (name.contains(lowerQuery)) {
                filtered.add(bee);
            }
        }
        beesAdapter.establecerLista(filtered);
    }
}