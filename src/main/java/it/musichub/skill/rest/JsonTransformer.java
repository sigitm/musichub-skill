package it.musichub.skill.rest;

import com.google.gson.Gson;

import it.musichub.rest.model.ApiError;
import it.musichub.rest.model.DeviceDto;
import it.musichub.rest.model.HealthDto;

public class JsonTransformer {

    public static HealthDto toHealthDto(String json) {
        return getGson().fromJson(json, HealthDto.class);
    }
    
    public static DeviceDto toDeviceDto(String json) {
        return getGson().fromJson(json, DeviceDto.class);
    }
    
    public static ApiError toApiError(String json) {
        return getGson().fromJson(json, ApiError.class);
    }

	private static Gson getGson(){
    	Gson gson = new Gson();
    	return gson;
    }
}
