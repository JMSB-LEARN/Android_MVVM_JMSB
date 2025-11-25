package com.example.mvvm_ejercicio_jmsb.model;

import android.content.Context;

import androidx.annotation.StringRes;

import com.example.mvvm_ejercicio_jmsb.R;


public enum Habitat {
    Forest(R.string.forest),
    Swamp(R.string.swamp);

    @StringRes
    private final int descriptionResId;

    Habitat(@StringRes int descriptionResId) {
        this.descriptionResId = descriptionResId;
    }

    public String getDescription(Context context) {
        return context.getString(this.descriptionResId);
    }
}
