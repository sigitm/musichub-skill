package it.musichub.skill.handlers;

import static com.amazon.ask.request.Predicates.requestType;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;

import it.musichub.rest.model.HealthDto;
import it.musichub.skill.rest.ex.ApiErrorException;
import it.musichub.skill.rest.ex.RestClientException;
import it.musichub.skill.rest.routes.Health;

public class LaunchRequestHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(requestType(LaunchRequest.class));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
    	String incipit = "Welcome to Music Hub! ";
    	
    	String content;
    	boolean success = false;
    	try {
			HealthDto healthDto = Health.getHealth();
			
			content = "Your home server is ready. What should I do?";
    		success = true;
    	} catch (ApiErrorException e) {
    		content = "<say-as interpret-as=\"interjection\">ouch!</say-as> Your home server is not ready";
    	} catch (RestClientException e) {
			content = "<say-as interpret-as=\"interjection\">uh oh!</say-as> Your home server is not responding";
		}
    	
    	
        String speechText = incipit+content;
//        String repromptSpeechText = "What can I help you with?";
        
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("Music Hub", speechText)
//                .withReprompt(repromptSpeechText)
                .withShouldEndSession(!success)
                .build();
    }

}
