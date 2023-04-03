package com.example.pokedex_REST.controllers;

import com.example.pokedex_REST.model.Pokemon;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class SearchController {
    RestTemplate restTemplate;
    final String POKEMON_API_PATH = "https://pokeapi.co/api/v2/";

    public SearchController() {
        restTemplate = new RestTemplate();
    }

    @GetMapping(value = "/pokemons")
    @CrossOrigin
    public ResponseEntity<List<String>> getAllPokemonNames() {
        ResponseEntity<String> response = restTemplate.getForEntity(POKEMON_API_PATH + "pokemon?limit=10000&offset=0", String.class);
        if (response.getStatusCode().value() != 200) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            JSONObject jsonObject = new JSONObject(response.getBody());
            JSONArray pokemonNames = jsonObject.getJSONArray("results");
            List<String> names = new ArrayList<>();
            for(int i = 0; i < pokemonNames.length(); i++) {
                names.add(pokemonNames.getJSONObject(i).get("name").toString());
            }
            return ResponseEntity.status(HttpStatus.OK).body(names);
        }
    }

    @RequestMapping(value = "/pokemon/{name}")
    @CrossOrigin
    public ResponseEntity<Pokemon> getPokemon(@PathVariable final String name) {
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(POKEMON_API_PATH + "pokemon/" + name.toLowerCase(), String.class);
            if (response.getStatusCode().value() != 200) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            } else {
                String jsonString = response.getBody();
                JSONObject pokemonDetails = new JSONObject(jsonString);
                String pokemonName = pokemonDetails.get("name").toString();
                JSONObject sprites = pokemonDetails.getJSONObject("sprites");
                String spriteUrl = sprites.getJSONObject("other").getJSONObject("official-artwork").get("front_default").toString();
                JSONArray types = new JSONArray(pokemonDetails.get("types").toString());
                List<String> pokemonTypes = new ArrayList<>();
                for (int i = 0; i < types.length(); i++) {
                    pokemonTypes.add(types.getJSONObject(i).getJSONObject("type").get("name").toString());
                }
                HashMap<String, Integer> pokemonStats = new HashMap<>();
                JSONArray stats = new JSONArray(pokemonDetails.get("stats").toString());
                for (int i = 0; i < stats.length(); i++) {
                    String statName = stats.getJSONObject(i).getJSONObject("stat")
                            .get("name").toString();
                    if (statName.contains("-")) {
                        statName = statName.split("-")[0] +
                                statName.split("-")[1].substring(0, 1).toUpperCase() +
                                statName.split("-")[1].substring(1);
                    }
                    pokemonStats.put(statName,
                            Integer.parseInt(stats.getJSONObject(i).get("base_stat").toString()));
                }

                return ResponseEntity.status(HttpStatus.OK).body(new Pokemon(pokemonName, spriteUrl, pokemonTypes, pokemonStats));
            }
        } catch (HttpClientErrorException e) {
            return null;
        }

    }
}
