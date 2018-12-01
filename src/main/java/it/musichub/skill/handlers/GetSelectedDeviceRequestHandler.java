package it.musichub.skill.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.model.Response;
import com.amazon.ask.response.ResponseBuilder;

import it.musichub.rest.model.DeviceDto;
import it.musichub.skill.MusicHubRequestHandler;
import it.musichub.skill.rest.ex.RestClientException;
import it.musichub.skill.rest.routes.Devices;

public class GetSelectedDeviceRequestHandler extends MusicHubRequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("GetSelectedDeviceIntent"));
    }
    
    @Override
    public Optional<Response> handle(HandlerInput input) {
    	String speechText;
    	try {
			DeviceDto selectedDevice = Devices.getSelectedDevice();
			
			if (selectedDevice == null)
				speechText = i18n("GetSelectedDevice.noSelectedDevice", input);
			else {
				Map<String, Object> params = new HashMap<>();
				params.put("device", selectedDevice.getCustomName());
				speechText = i18n("GetSelectedDevice.withSelectedDevice", params, input);
			}
    	} catch (RestClientException e) {
    		speechText = i18n("Generic.serverConnectionError", input);
		}
    	
    	
//        String repromptSpeechText = "What can I help you with?";
        
        ResponseBuilder rb = input.getResponseBuilder()
                				  .withSpeech(speechText)
                				  .withSimpleCard("Music Hub", speechText)
//                .withReprompt(content)
                				  .withShouldEndSession(true);
         return rb.build();
    }

}
