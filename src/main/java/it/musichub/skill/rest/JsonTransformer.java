package it.musichub.skill.rest;

import com.google.gson.Gson;

import it.musichub.rest.model.HealthDto;

public class JsonTransformer {

    public static HealthDto renderHealth(String json) {
        return getGson().fromJson(json, HealthDto.class);
    }

	private static Gson getGson(){
    	Gson gson = new Gson();
    	return gson;
    }
}
