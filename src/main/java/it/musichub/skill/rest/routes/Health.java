package it.musichub.skill.rest.routes;

import com.amazon.ask.model.services.ApiClientResponse;

import it.musichub.rest.model.ApiError;
import it.musichub.rest.model.HealthDto;
import it.musichub.skill.rest.JsonTransformer;
import it.musichub.skill.rest.RestClient;
import it.musichub.skill.rest.ex.ApiErrorException;
import it.musichub.skill.rest.ex.RestClientException;

public class Health {
	
	private static final String ROUTE = "health";
	
	public static HealthDto getHealth() throws RestClientException {
		ApiClientResponse response = RestClient.invokeEndpoint("GET", ROUTE, null);
		int status = response.getStatusCode();
		String body = response.getBody();
		if (status == 200){
			HealthDto health = JsonTransformer.toHealthDto(body);
			return health;
		}else{
			ApiError apiError = JsonTransformer.toApiError(body);
			throw new ApiErrorException(apiError);
		}
	}
	
}
