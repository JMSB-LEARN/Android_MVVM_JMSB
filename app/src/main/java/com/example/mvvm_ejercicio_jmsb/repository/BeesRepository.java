package com.example.mvvm_ejercicio_jmsb.repository;

import com.example.mvvm_ejercicio_jmsb.R;
import com.example.mvvm_ejercicio_jmsb.model.Bee;
import com.example.mvvm_ejercicio_jmsb.model.Habitat;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BeesRepository {
    private List<Bee> beeList;
    private static List<Bee> browserBeeList;

    static {
        browserBeeList = new ArrayList<>();
        //Tier 1
        //Social
        Bee commonBee = new Bee(R.string.common_bee, "Apis Communia", R.drawable.common_bee, new int[][]{{4, 5}, {4}, {4}, {4, 5}}, new boolean[]{true, false, false}, new boolean[]{false, false, false}, Habitat.Forest);
        browserBeeList.add(commonBee);
        Bee forestBee = new Bee(R.string.forest_bee, "Apis Silva", R.drawable.forest_bee, new int[][]{{4}, {3}, {4, 5}, {4}}, new boolean[]{true, false, false}, new boolean[]{true, false, false}, Habitat.Forest);
        browserBeeList.add(forestBee);

        Bee vergeBee = new Bee(R.string.verge_bee, "Apis Paene", R.drawable.verge_bee, new int[][]{{3, 4}, {4}, {4}, {4, 5}}, new boolean[]{true, false, false}, new boolean[]{false, false, false}, Habitat.Forest);
        browserBeeList.add(vergeBee);

        Bee uncommonBee = new Bee(R.string.uncommon_bee, "Apis Raris", R.drawable.uncommon_bee, new int[][]{{3, 4}, {4}, {4}, {4, 5}}, new boolean[]{true, false, false}, new boolean[]{false, false, false}, Habitat.Forest);
        browserBeeList.add(uncommonBee);

        //Asocial
        Bee cutterBee = new Bee(R.string.cutter_bee, "Megachile folium", R.drawable.cutter_bee, Habitat.Forest);
        browserBeeList.add(cutterBee);

        Bee scrapperBee = new Bee(R.string.scrapper_bee, "Anthidium fragmen", R.drawable.scrapper_bee, Habitat.Forest);
        browserBeeList.add(scrapperBee);

        Bee delverbee = new Bee(R.string.delver_bee, "Andrena fodio", R.drawable.delver_bee, Habitat.Forest);
        browserBeeList.add(delverbee);

        Bee fellerBee = new Bee(R.string.feller_bee, "Halictus cadere", R.drawable.feller_bee, Habitat.Forest);
        browserBeeList.add(fellerBee);

        //Tier 2

        Set<AbstractMap.SimpleEntry<Bee, Bee>> verdantParents = new HashSet<>();
        AbstractMap.SimpleEntry<Bee, Bee> combination1Verdant = new AbstractMap.SimpleEntry<>(commonBee, forestBee);
        verdantParents.add(combination1Verdant);

        Bee verdantBee = new Bee(verdantParents, R.string.verdant_bee, "Apis Floreo", R.drawable.verdant_bee, new int[][]{{4, 5}, {3, 4}, {4, 5}, {4, 5}}, new boolean[]{true, false, false}, new boolean[]{false, false, false}, Habitat.Forest, 2);
        browserBeeList.add(verdantBee);

        Set<AbstractMap.SimpleEntry<Bee, Bee>> vibrantParents = new HashSet<>();
        AbstractMap.SimpleEntry<Bee, Bee> combination1Vibrant = new AbstractMap.SimpleEntry<>(commonBee, vergeBee);
        vibrantParents.add(combination1Vibrant);

        Bee vibrantBee = new Bee(vibrantParents, R.string.vibrant_bee, "Apis Vividus", R.drawable.vibrant_bee, new int[][]{{4, 5}, {3, 4}, {4, 5}, {4, 5}}, new boolean[]{true, false, false}, new boolean[]{false, false, false}, Habitat.Forest, 2);
        browserBeeList.add(vibrantBee);

        Set<AbstractMap.SimpleEntry<Bee, Bee>> drowsyParents = new HashSet<>();
        AbstractMap.SimpleEntry<Bee, Bee> combination1Drowsy = new AbstractMap.SimpleEntry<>(commonBee, forestBee);
        drowsyParents.add(combination1Drowsy);
        Bee drowsyBee = new Bee(drowsyParents, R.string.drowsy_bee, "Apis Somnola", R.drawable.drowsy_bee, new int[][]{{4, 5}, {3, 4}, {4, 5}, {4, 5}}, new boolean[]{false, false, true}, new boolean[]{false, false, false}, Habitat.Forest, 2);
        browserBeeList.add(drowsyBee);

        Set<AbstractMap.SimpleEntry<Bee, Bee>> mistyParents = new HashSet<>();
        AbstractMap.SimpleEntry<Bee, Bee> combination1misty = new AbstractMap.SimpleEntry<>(forestBee, vergeBee);
        mistyParents.add(combination1misty);
        Bee mistyBee = new Bee(mistyParents, R.string.misty_bee, "Apis Nebula", R.drawable.misty_bee, new int[][]{{4, 5}, {3, 4}, {4, 5}, {4, 5}}, new boolean[]{true, false, false}, new boolean[]{false, false, false}, Habitat.Forest, 2);
        browserBeeList.add(mistyBee);

        Set<AbstractMap.SimpleEntry<Bee, Bee>> dreamParents = new HashSet<>();
        AbstractMap.SimpleEntry<Bee, Bee> combination1dream = new AbstractMap.SimpleEntry<>(commonBee, uncommonBee);
        dreamParents.add(combination1dream);

        Bee dreamBee = new Bee(dreamParents, R.string.dream_bee, "Apis Somnia", R.drawable.dream_bee, new int[][]{{4, 5}, {3, 4}, {4, 5}, {4, 5}}, new boolean[]{false, true, false}, new boolean[]{false, false, true}, Habitat.Forest, 2);
        browserBeeList.add(dreamBee);

        Set<AbstractMap.SimpleEntry<Bee, Bee>> murkyParents = new HashSet<>();
        AbstractMap.SimpleEntry<Bee, Bee> combination1murky = new AbstractMap.SimpleEntry<>(vergeBee, uncommonBee);
        murkyParents.add(combination1murky);


        Bee murkyBee = new Bee(murkyParents, R.string.murky_bee, "Apis Obscura", R.drawable.murky_bee, new int[][]{{4, 5}, {3, 4}, {4, 5}, {4, 5}}, new boolean[]{true, false, false}, new boolean[]{false, false, false}, Habitat.Forest, 2);
        browserBeeList.add(murkyBee);

        //Tier 3

        Set<AbstractMap.SimpleEntry<Bee, Bee>> muggyParents = new HashSet<>();
        muggyParents.add(new AbstractMap.SimpleEntry<>(drowsyBee, mistyBee));
        Bee muggyBee = new Bee(muggyParents, R.string.muggy_bee, "Apis Vapora", R.drawable.muggy_bee, new int[][]{{4, 5}, {3, 4}, {4, 5}, {4, 5}}, new boolean[]{true, false, false}, new boolean[]{false, false, false}, Habitat.Forest, 3);
        browserBeeList.add(muggyBee);

        Set<AbstractMap.SimpleEntry<Bee, Bee>> glowingParents = new HashSet<>();
        glowingParents.add(new AbstractMap.SimpleEntry<>(vibrantBee, verdantBee));
        glowingParents.add(new AbstractMap.SimpleEntry<>(verdantBee, dreamBee));
        Bee glowingBee = new Bee(glowingParents, R.string.glowing_bee, "Apis Lumen", R.drawable.glowing_bee, new int[][]{{4, 5}, {3, 4}, {4, 5}, {4, 5}}, new boolean[]{true, false, false}, new boolean[]{false, false, false}, Habitat.Forest, 3);
        browserBeeList.add(glowingBee);

        Set<AbstractMap.SimpleEntry<Bee, Bee>> rockyParents = new HashSet<>();
        rockyParents.add(new AbstractMap.SimpleEntry<>(murkyBee, verdantBee));
        Bee rockyBee = new Bee(rockyParents, R.string.rocky_bee, "Apis Petrosa", R.drawable.rocky_bee, new int[][]{{4, 5}, {3, 4}, {4, 5}, {4, 5}}, new boolean[]{true, false, false}, new boolean[]{false, false, false}, Habitat.Forest, 3);
        browserBeeList.add(rockyBee);

        Set<AbstractMap.SimpleEntry<Bee, Bee>> coralParents = new HashSet<>();
        coralParents.add(new AbstractMap.SimpleEntry<>(mistyBee, vibrantBee));
        Bee coralBee = new Bee(coralParents, R.string.coral_bee, "Apis Coralis", R.drawable.coral_bee, new int[][]{{4, 5}, {3, 4}, {4, 5}, {4, 5}}, new boolean[]{true, false, false}, new boolean[]{false, false, false}, Habitat.Forest, 3);
        browserBeeList.add(coralBee);

        Set<AbstractMap.SimpleEntry<Bee, Bee>> custodianParents = new HashSet<>();
        custodianParents.add(new AbstractMap.SimpleEntry<>(dreamBee, drowsyBee));
        Bee custodianBee = new Bee(custodianParents, R.string.custodian_bee, "Apis Cuidadora", R.drawable.custodian_bee, new int[][]{{4, 5}, {3, 4}, {4, 5}, {4, 5}}, new boolean[]{true, false, false}, new boolean[]{false, false, false}, Habitat.Forest, 3);
        browserBeeList.add(custodianBee);

        Set<AbstractMap.SimpleEntry<Bee, Bee>> artisanParents = new HashSet<>();
        artisanParents.add(new AbstractMap.SimpleEntry<>(mistyBee, murkyBee));
        Bee artisanBee = new Bee(artisanParents, R.string.artisan_bee, "Apis Artista", R.drawable.artisan_bee, new int[][]{{4, 5}, {3, 4}, {4, 5}, {4, 5}}, new boolean[]{true, false, false}, new boolean[]{false, false, false}, Habitat.Swamp, 3);
        browserBeeList.add(artisanBee);
    }

    public BeesRepository() {
        beeList = new ArrayList<>(browserBeeList);
    }

    public List<Bee> getBees() {
        return beeList;
    }

    public static List<Bee> getBrowserBees() {
        return browserBeeList;
    }

    public void updateBee(Bee bee) {
        beeList.replaceAll(bee1 -> bee1.equals(bee) ? bee : bee1);
    }
}


