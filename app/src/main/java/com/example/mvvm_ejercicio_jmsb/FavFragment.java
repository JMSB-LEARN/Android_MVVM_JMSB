package com.example.mvvm_ejercicio_jmsb;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvvm_ejercicio_jmsb.adapter.BeesAdapter;
import com.example.mvvm_ejercicio_jmsb.databinding.FragmentFavBinding;
import com.example.mvvm_ejercicio_jmsb.model.Bee;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class FavFragment extends Fragment {

    private FragmentFavBinding binding;
    private BeesAdapter beesAdapter;
    private BeesViewModel beesViewModel;
    List<Bee> filteredList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFavBinding.inflate(inflater, container, false);
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
                beesAdapter.establecerLista(getFavBees(beeList));
            } else {
                filteredList= filterByCommonName(currentQuery);
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

        eventOutFromList(view);
    }

    private List<Bee> getFavBees(List<Bee> bees) {
        return bees.stream().filter(bee -> bee.isFav()).collect(Collectors.toList());
    }


    private List<Bee> filterByCommonName(java.lang.String query) {
        List<Bee> fullList = beesViewModel.getAllBees();

        if (fullList == null) return null;

        List<Bee> filtered = new ArrayList<>();
        String lowerQuery = query.toLowerCase();

        for (Bee bee : fullList) {
            String name = getString(bee.getCommonName()).toLowerCase();

            if (name.contains(lowerQuery)&&bee.isFav()) {
                filtered.add(bee);
            }
        }
        beesAdapter.establecerLista(filtered);
        return filtered;
    }

    private void eventOutFromList(View view) {
        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getBindingAdapterPosition();

                if (position != RecyclerView.NO_POSITION) {
                    Bee bee = beesAdapter.getBeeAt(position);

                    if (bee != null) {
                        bee.setFav(false);
                        beesViewModel.updateBee(bee);
                    }
                }
            }

        };

        new ItemTouchHelper(callback).attachToRecyclerView(binding.recyclerViewBees);
    }
}