package com.example.mvvm_ejercicio_jmsb;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvm_ejercicio_jmsb.model.Bee;
import com.example.mvvm_ejercicio_jmsb.repository.BeesRepository;

import java.util.ArrayList;
import java.util.List;

public class BeesViewModel extends ViewModel {
    private final BeesRepository beeRepository;
    public MutableLiveData<List<Bee>> bees = new MutableLiveData<>();
    private List<Bee> allBees = new ArrayList<>();

    public void getBees() {
        List<Bee> data = beeRepository.getBees();
        allBees = new ArrayList<>(data);
        bees.setValue(data);
    }

    public BeesViewModel() {
        beeRepository = new BeesRepository();
        List<Bee> initialData = BeesRepository.getBrowserBees();
        allBees = new ArrayList<>(initialData);
        bees.setValue(initialData);
    }

    public void updateBee(Bee bee) {
        beeRepository.updateBee(bee);
        bees.setValue(beeRepository.getBees());
    }

    public List<Bee> getAllBees() {
        return allBees;

    }

    public Bee getFavBeeByPosition(int position) {
        return bees.getValue().stream().filter(bee -> bee.isFav()).skip(position).findFirst().orElse(null);
    }

    public void unfavBee(Bee unfavBee) {
        unfavBee.setFav(false);
        updateBee(unfavBee);
    }

}