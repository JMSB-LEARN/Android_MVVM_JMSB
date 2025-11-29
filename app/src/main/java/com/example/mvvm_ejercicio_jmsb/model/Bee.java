package com.example.mvvm_ejercicio_jmsb.model;

import com.example.mvvm_ejercicio_jmsb.R;
import com.example.mvvm_ejercicio_jmsb.model.Habitat;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Objects;
import java.util.Set;

public class Bee implements Serializable {
    static final int mysterybee = R.drawable.mystery_bee;
    private Set<AbstractMap.SimpleEntry<Bee, Bee>> posibleParents;
    private String scientificName;
    private int commonName;
    int iconId, blessedIconId, tier;
    int[][] dominantStats, recesiveStats;
    boolean[] behaviour, climate;
    Habitat habitat;

    public Bee(Set<AbstractMap.SimpleEntry<Bee, Bee>> posibleParents,int commonName, String scientificName, int iconId, int blessedIconId, int[][] dominantStats, int[][] recesiveStats, boolean[] behaviour, boolean[] climate, Habitat habitat, int tier) {
        this.posibleParents = posibleParents;
        this.scientificName = scientificName;
        this.commonName=commonName;
        this.iconId = iconId;
        this.blessedIconId = blessedIconId;
        this.dominantStats = dominantStats;
        this.recesiveStats = recesiveStats;
        this.behaviour = behaviour;
        this.climate = climate;
        this.habitat = habitat;
        this.tier = tier;
    }

    public Bee(Set<AbstractMap.SimpleEntry<Bee, Bee>> posibleParents,int commonName, String scientificName, int iconId, int blessedIconId, int[][] dominantStats, boolean[] behaviour, boolean[] climate, Habitat habitat, int tier) {
        this(posibleParents,commonName, scientificName, iconId, blessedIconId, dominantStats, dominantStats, behaviour, climate, habitat, tier);
    }

    public Bee(int commonName,String scientificName, int iconId, int blessedIconId, int[][] dominantStats, boolean[] behaviour, boolean[] climate, Habitat habitat) {
        this(new java.util.HashSet<>(),commonName, scientificName, iconId, blessedIconId, dominantStats, behaviour, climate, habitat, 1);
    }

    public Bee(Set<AbstractMap.SimpleEntry<Bee, Bee>> posibleParents,int commonName, String scientificName, int iconId, int[][] dominantStats, boolean[] behaviour, boolean[] climate, Habitat habitat, int tier) {
        this(posibleParents,commonName, scientificName, iconId, mysterybee, dominantStats, behaviour, climate, habitat, tier);
    }

    public Bee(int commonName,String scientificName, int iconId, int[][] dominantStats, boolean[] behaviour, boolean[] climate, Habitat habitat) {
        this(new java.util.HashSet<>(),commonName, scientificName, iconId, mysterybee, dominantStats, behaviour, climate, habitat, 1);
    }

    public Bee(Set<AbstractMap.SimpleEntry<Bee, Bee>> posibleParents,int commonName, String scientificName, int[][] dominantStats, boolean[] behaviour, boolean[] climate, Habitat habitat, int tier) {
        this(posibleParents,commonName, scientificName, mysterybee, dominantStats, behaviour, climate, habitat, tier);
    }

    public Bee(int commonName,String scientificName, int[][] dominantStats, boolean[] behaviour, boolean[] climate, Habitat habitat) {
        this(new java.util.HashSet<>(),commonName, scientificName, mysterybee, mysterybee, dominantStats, behaviour, climate, habitat, 1);
    }

    public Bee(int commonName, String scientificName, int iconId, Habitat habitat) {
        this(commonName, scientificName, iconId,null, null, null, habitat);
    }

    public Set<AbstractMap.SimpleEntry<Bee, Bee>> getPosibleParents() {
        return posibleParents;
    }

    public int getCommonName() {
        return commonName;
    }
    public String getScientificName() {
        return scientificName;
    }

    public int getIconId() {
        return iconId;
    }

    public int getBlessedIconId() {
        return blessedIconId;
    }

    public int getTier() {
        return tier;
    }

    public int[][] getDominantStats() {
        return dominantStats;
    }

    public int[][] getRecesiveStats() {
        return recesiveStats;
    }

    public boolean[] getBehaviour() {
        return behaviour;
    }

    public boolean[] getClimate() {
        return climate;
    }

    public Habitat getHabitat() {
        return habitat;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Bee)) return false;
        Bee bee = (Bee) o;
        return commonName == bee.commonName && Objects.equals(posibleParents, bee.posibleParents) && Objects.equals(tier, bee.tier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commonName, posibleParents, tier);
    }
}