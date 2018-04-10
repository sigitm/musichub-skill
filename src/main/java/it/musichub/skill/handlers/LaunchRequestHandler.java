package it.musichub.skill.handlers;

import static com.amazon.ask.request.Predicates.requestType;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.services.ApiClientResponse;

import it.musichub.rest.model.HealthDto;
import it.musichub.skill.MusicHubStreamHandler;
import it.musichub.skill.rest.JsonTransformer;
import it.musichub.skill.rest.RestClient;

public class LaunchRequestHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(requestType(LaunchRequest.class));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
    	String incipit = "Welcome to Music Hub! Id is "+MusicHubStreamHandler.getInstance().getUniqueID()+"; static id is "+MusicHubStreamHandler.getStaticUniqueID();
    	
    	boolean success = false;
    	String content = null;
		try {
			ApiClientResponse response = RestClient.invokeEndpoint("GET", "health");
			int status = response.getStatusCode();
	    	String body = response.getBody();
	    	HealthDto health = JsonTransformer.renderHealth(body);
	    	if (status == 200){
	    		content = "Server version "+health.getVersion()+", server status "+health.getStatus();
	    		success = true;
	    	}else{
	    		content = "<say-as interpret-as=\"interjection\">ouch!</say-as> Server ERROR! Version "+health.getVersion()+", server status "+health.getStatus();
	    	}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			content = "<say-as interpret-as=\"interjection\">uh oh!</say-as> Server not responding!";
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
