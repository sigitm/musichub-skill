package it.musichub.skill.rest.ex;

import it.musichub.rest.model.ApiError;

public class ApiErrorException extends RestClientException {

	private ApiError apiError;

	public ApiErrorException(ApiError apiError) {
		super(apiError.getMessage());
		this.apiError = apiError;
	}
	
	public ApiError getApiError() {
		return apiError;
	}

}
