package it.musichub.skill.rest.routes;

import org.apache.http.HttpStatus;

import com.amazon.ask.model.services.ApiClientResponse;
import com.amazonaws.HttpMethod;

import it.musichub.rest.model.ApiError;
import it.musichub.rest.model.HealthDto;
import it.musichub.skill.rest.JsonTransformer;
import it.musichub.skill.rest.RestClient;
import it.musichub.skill.rest.ex.ApiErrorException;
import it.musichub.skill.rest.ex.RestClientException;

public class Health {
	
	private static final String ROUTE = "health";
	
	public static HealthDto getHealth() throws RestClientException {
		ApiClientResponse response = RestClient.invokeEndpoint(HttpMethod.GET.toString(), ROUTE, null);
		int status = response.getStatusCode();
		String body = response.getBody();
		if (status == HttpStatus.SC_OK){
			HealthDto health = JsonTransformer.toHealthDto(body);
			return health;
		}else{
			ApiError apiError = JsonTransformer.toApiError(body);
			throw new ApiErrorException(apiError);
		}
	}
	
}
