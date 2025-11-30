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
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.stream.Collectors;


public class FavFragment extends Fragment {

    private FragmentFavBinding binding;
    private BeesAdapter adapter;
    private BeesViewModel beesViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFavBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 1. Initialize ViewModel (scoped to Activity to share data)
        beesViewModel = new ViewModelProvider(requireActivity()).get(BeesViewModel.class);

        // 2. Setup Adapter and RecyclerView
        adapter = new BeesAdapter(requireContext(), new ArrayList<>());
        binding.recyclerViewBees.setAdapter(adapter);
        binding.recyclerViewBees.setLayoutManager(new GridLayoutManager(requireContext(), 2));

        // 3. Observe changes
        beesViewModel.bees.observe(getViewLifecycleOwner(), lista -> {
            adapter.establecerLista(lista);
        });

        // 4. ONLY load data if the list is currently empty or null.
        // This prevents resetting the data when you navigate back to this fragment.
        if (beesViewModel.bees.getValue() == null || beesViewModel.bees.getValue().isEmpty()) {
            beesViewModel.getBees();
        }
    }

    private void eventoEliminarElto(View view) {
        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0, // No permitimos mover elementos (drag)
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT // Permitimos deslizar a izquierda o derecha
        ) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                // No necesitamos implementar el movimiento (solo eliminación)
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            }
        };
        beesViewModel.bees.observe(getViewLifecycleOwner(), listBee -> {
            adapter.establecerLista(listBee); // Actualiza el RecyclerView
        });
        // Asociamos el callback al RecyclerView
        new ItemTouchHelper(callback).attachToRecyclerView(binding.recyclerViewBees);
    }
}