package com.example.mvvm_ejercicio_jmsb;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mvvm_ejercicio_jmsb.databinding.FragmentDetailsBinding;
import com.example.mvvm_ejercicio_jmsb.model.Bee;


public class DetailsFragment extends Fragment {

    // Variable para almacenar el animal recibido
    private Bee bee;
    private FragmentDetailsBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
// Recuperamos el argumento enviado desde el adapter (antes de crear la vista)
        if (getArguments() != null) {
            bee = (Bee) getArguments().getSerializable("bee");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (bee != null) {
// Mostramos los datos del animal en la interfaz
            binding.tvNombreDetalle.setText(getString(bee.getCommonName()));
            binding.ivDetalle.setImageResource(bee.getIconId());
            binding.tvDescripcion.setText(bee.getHabitat().getDescription(view.getContext()));
        } else {
// En caso de error, podríamos volver atrás o mostrar un mensaje
            Toast.makeText(requireContext(), "No se pudo cargar el detalle del animal", Toast.LENGTH_SHORT).show();
            requireView().post(() -> {
                if (isAdded()) { // Check if fragment is still attached to an activity
                    requireActivity().onBackPressed();
                }
            });

        }
    }
}