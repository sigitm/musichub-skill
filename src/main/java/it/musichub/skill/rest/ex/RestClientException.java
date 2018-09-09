package it.musichub.skill.rest.ex;

public class RestClientException extends MusicHubSkillException {

	public RestClientException() {
		super();
	}

	public RestClientException(String message) {
		super(message);
	}

	public RestClientException(Throwable cause) {
		super(cause);
	}

	public RestClientException(String message, Throwable cause) {
		super(message, cause);
	}

}
