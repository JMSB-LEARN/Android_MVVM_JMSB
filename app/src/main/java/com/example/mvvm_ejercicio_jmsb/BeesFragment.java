package com.example.mvvm_ejercicio_jmsb;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvvm_ejercicio_jmsb.adapter.BeesAdapter;
import com.example.mvvm_ejercicio_jmsb.databinding.FragmentBeesBinding;
import com.example.mvvm_ejercicio_jmsb.model.Bee;

import java.util.List;


public class BeesFragment extends Fragment {

    private FragmentBeesBinding binding;
    private BeesAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBeesBindinginflate(inflater, container, false);


        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

// Obtenemos la lista desde el Repository

        List<Bee> beesList = new BeeRepository(true,false);

// Configuramos el RecyclerView
        adapter = new BeesAdapter(requireContext(), beesList, R.id.action_browserBeesFragment_to_detailsFragment);
        binding.recyclerViewBees.setAdapter(adapter);

// Definimos el LayoutManager (en cuadrícula de 2 columnas)
        binding.recyclerViewBees.setLayoutManager(new GridLayoutManager(requireContext(), 2));
    }}