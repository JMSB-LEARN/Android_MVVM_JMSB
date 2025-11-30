package com.example.mvvm_ejercicio_jmsb.adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm_ejercicio_jmsb.R;
import com.example.mvvm_ejercicio_jmsb.databinding.ViewholderBeeBinding;
import com.example.mvvm_ejercicio_jmsb.model.Bee;

import java.util.List;

public class BeesAdapter extends RecyclerView.Adapter<BeesAdapter.BeeViewHolder> {


    public interface OnBeeListener {
        void onFavoriteClick(Bee bee);
    }

    private List<Bee> bees;
    private final LayoutInflater inflater;
    private final OnBeeListener listener;
    public BeesAdapter(Context context, List<Bee> abejas, OnBeeListener listener) {
        this.bees = abejas;
        this.inflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    @NonNull
    @Override
    public BeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.viewholder_bee, parent, false);
        return new BeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BeeViewHolder holder, int position) {
        Bee bee = bees.get(position);

        holder.binding.tvCommonName.setText(bee.getCommonName());
        holder.binding.ivBee.setImageResource(bee.getIconId());
        holder.binding.toggelFav.setOnCheckedChangeListener(null);
        holder.binding.toggelFav.setChecked(bee.isFav());
        holder.binding.toggelFav.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.i("TAG", "onCheckedChanged: " + isChecked);
                bee.setFav(isChecked);
                if (listener != null) {
                    listener.onFavoriteClick(bee);
                }
            }
        });

        holder.itemView.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("bee", bee);

            NavController navController = Navigation.findNavController(v);
            navController.navigate(R.id.action_to_detailsFragment, bundle);
        });
    }

    @Override
    public int getItemCount() {
        return bees != null ? bees.size() : 0;
    }

    public void establecerLista(List<Bee> abejas) {
        this.bees = abejas;
        notifyDataSetChanged();
    }

    public static class BeeViewHolder extends RecyclerView.ViewHolder {
        ViewholderBeeBinding binding;

        public BeeViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ViewholderBeeBinding.bind(itemView);
        }
    }
    public Bee getBeeAt(int position) {
        if (bees != null && position >= 0 && position < bees.size()) {
            return bees.get(position);
        }
        return null;
    }

}
