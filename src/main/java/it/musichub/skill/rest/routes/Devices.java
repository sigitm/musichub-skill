package it.musichub.skill.rest.routes;

import org.apache.http.HttpStatus;

import com.amazon.ask.model.services.ApiClientResponse;
import com.amazonaws.HttpMethod;

import it.musichub.rest.model.ApiError;
import it.musichub.rest.model.DeviceDto;
import it.musichub.skill.rest.JsonTransformer;
import it.musichub.skill.rest.RestClient;
import it.musichub.skill.rest.ex.ApiErrorException;
import it.musichub.skill.rest.ex.RestClientException;

public class Devices {
	
	private static final String ROUTE = "devices";
	
	public static DeviceDto getSelectedDevice() throws RestClientException {
		ApiClientResponse response = RestClient.invokeEndpoint(HttpMethod.GET.toString(), ROUTE+"/selected", null);
		int status = response.getStatusCode();
		String body = response.getBody();
		if (status == HttpStatus.SC_OK){
			DeviceDto device = JsonTransformer.toDeviceDto(body);
			return device;
		}else if (status == HttpStatus.SC_NOT_FOUND){
			return null;
		}else{
			ApiError apiError = JsonTransformer.toApiError(body);
			throw new ApiErrorException(apiError);
		}
	}
	
}
