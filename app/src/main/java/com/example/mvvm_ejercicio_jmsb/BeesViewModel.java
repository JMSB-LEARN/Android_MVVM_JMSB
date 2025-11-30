package com.example.mvvm_ejercicio_jmsb;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvm_ejercicio_jmsb.model.Bee;
import com.example.mvvm_ejercicio_jmsb.repository.BeesRepository;

import java.util.List;

public class BeesViewModel extends ViewModel {
    private final BeesRepository beeRepository;
    // Lo hacemos Mutable porque cambiará su valor (p.e. cuando eliminamos un Animal)
    public MutableLiveData<List<Bee>> bees = new MutableLiveData<>();


    public BeesViewModel(){
        beeRepository = new BeesRepository();
        bees.setValue(beeRepository.getBees());
        bees.postValue(BeesRepository.getBrowserBees());
    }

    public void deleteBee(Bee bee) {
        beeRepository.deleteBee(bee);
        bees.setValue(beeRepository.getBees());
    }
    public void getBees() {
        bees.setValue(beeRepository.getBees());
    }

    public Bee getBee(int position) {
        return beeRepository.getbee(position);
    }
    public void updateBee(Bee bee) {
        beeRepository.updateBee(bee);
        // Refresh the LiveData so the UI updates
        bees.setValue(beeRepository.getBees());
    }

}