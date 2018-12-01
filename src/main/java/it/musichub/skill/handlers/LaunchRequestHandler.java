package it.musichub.skill.handlers;

import static com.amazon.ask.request.Predicates.requestType;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.response.ResponseBuilder;

import it.musichub.rest.model.DeviceDto;
import it.musichub.rest.model.HealthDto;
import it.musichub.skill.MusicHubRequestHandler;
import it.musichub.skill.rest.ex.ApiErrorException;
import it.musichub.skill.rest.ex.RestClientException;
import it.musichub.skill.rest.routes.Health;

public class LaunchRequestHandler extends MusicHubRequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(requestType(LaunchRequest.class));
    }
    
    

    @Override
    public Optional<Response> handle(HandlerInput input) {
    	String incipit = i18n("LaunchRequest.incipit", input);
    	
    	String content;
    	boolean success = false;
    	try {
			HealthDto healthDto = Health.getHealth();
			
			DeviceDto selectedDevice = healthDto.getSelectedDevice();
			if (selectedDevice == null)
				content = i18n("LaunchRequest.ok-noSelectedDevice", input);
			else {
				Map<String, Object> params = new HashMap<>();
				params.put("device", selectedDevice.getCustomName());
				content = i18n("LaunchRequest.ok-withSelectedDevice", params, input);
			}
    		success = true;
    	} catch (ApiErrorException e) {
    		content = i18n("LaunchRequest.content.serverNotReady", input);
    	} catch (RestClientException e) {
			content = i18n("LaunchRequest.content.serverConnectionError", input);
		}
    	
    	
        String speechText = incipit+" "+content;
//        String repromptSpeechText = "What can I help you with?";
        
        ResponseBuilder rb = input.getResponseBuilder()
                				  .withSpeech(speechText)
                				  .withSimpleCard("Music Hub", speechText)
                .withReprompt(content) ///TODO XXX verificare se va bene tenerlo........
                				  .withShouldEndSession(!success);
         return rb.build();
    }

}
