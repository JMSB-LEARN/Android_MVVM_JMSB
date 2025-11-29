package com.example.mvvm_ejercicio_jmsb;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvvm_ejercicio_jmsb.adapter.BeesAdapter;
import com.example.mvvm_ejercicio_jmsb.databinding.FragmentBeesBinding;
import com.example.mvvm_ejercicio_jmsb.model.Bee;
import com.example.mvvm_ejercicio_jmsb.repository.BeesRepository;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;


public class BeesFragment extends Fragment {

    private FragmentBeesBinding binding;
    private BeesAdapter adapter;
    BeesRepository beesRepository;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBeesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

// Obtenemos la lista desde el Repository
        beesRepository = new BeesRepository(true,false);
        List<Bee> beesList = beesRepository.getBees();

// Configuramos el RecyclerView
        adapter = new BeesAdapter(requireContext(), beesList);
        binding.recyclerViewBees.setAdapter(adapter);

// Definimos el LayoutManager (en cuadrícula de 2 columnas)
        binding.recyclerViewBees.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        eventoEliminarElto(view);
    }

    private void eventoEliminarElto(View view) {
        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(
                0, // No permitimos mover elementos (drag)
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT // Permitimos deslizar a izquierda o derecha
        ) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                // No necesitamos implementar el movimiento (solo eliminación)
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                // 1. Obtenemos la posición del elemento deslizado
                int position = viewHolder.getBindingAdapterPosition();

                if (position != RecyclerView.NO_POSITION) {
                    // 2. Recuperamos el animal correspondiente
                    Bee deletedBee = beesRepository.getbee(position);

                    // 3. Lo eliminamos del repositorio
                    beesRepository.getBees().remove(deletedBee);

                    // 4. Notificamos al adaptador para que actualice la interfaz
                    adapter.notifyItemRemoved(position);

                    // 5.(Opcional) Mostramos un mensaje al usuario
                    Snackbar.make(view, getString(deletedBee.getCommonName())  + " borrada con exito ", Snackbar.LENGTH_SHORT).show();
                }
            }
        };

        // Asociamos el callback al RecyclerView
        new ItemTouchHelper(callback).attachToRecyclerView(binding.recyclerViewBees);
    }
}