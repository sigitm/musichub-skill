package it.musichub.skill;

import java.util.Map;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;

import it.musichub.skill.utils.MessageManager;

public abstract class MusicHubRequestHandler implements RequestHandler {

	protected String i18n(String key, HandlerInput input){
		return MessageManager.i18n(key, input.getRequestEnvelope().getRequest().getLocale());
	}
	
	protected String i18n(String key, Map<String, Object> params, HandlerInput input){
		return MessageManager.i18n(key, params, input.getRequestEnvelope().getRequest().getLocale());
	}
}
