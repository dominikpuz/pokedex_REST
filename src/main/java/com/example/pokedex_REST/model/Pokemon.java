package com.example.pokedex_REST.model;

import java.util.HashMap;
import java.util.List;

public class Pokemon {
    private final String name;
    private final String spriteUrl;

    private final List<String> types;

    private final HashMap<String, Integer> stats;

    public Pokemon(String name, String spriteUrl, List<String> types, HashMap<String, Integer> stats) {
        this.name = name;
        this.spriteUrl = spriteUrl;
        this.types = types;
        this.stats = stats;
    }

    public String getName() {
        return name;
    }

    public String getSpriteUrl() {
        return spriteUrl;
    }

    public List<String> getTypes() {
        return types;
    }

    public HashMap<String, Integer> getStats() {
        return stats;
    }
}
