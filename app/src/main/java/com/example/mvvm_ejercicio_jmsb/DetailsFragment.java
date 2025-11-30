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

    private Bee bee;
    private FragmentDetailsBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
            binding.tvNombreDetalle.setText(getString(bee.getCommonName()));
            binding.ivDetalle.setImageResource(bee.getIconId());
            binding.tvDescripcion.setText(bee.getHabitat().getDescription(view.getContext()));
        } else {
            Toast.makeText(requireContext(), "No se pudo cargar el detalle de la abeja", Toast.LENGTH_SHORT).show();
            requireView().post(() -> {
                if (isAdded()) {
                    requireActivity().onBackPressed();
                }
            });
        }
    }
}