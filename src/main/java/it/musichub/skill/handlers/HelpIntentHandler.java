package it.musichub.skill.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

public class HelpIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AMAZON.HelpIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText =
                "You can ask Baby World, tell me something about the baby f, or, ask who's the most little person in the world. "
                        + "What can I help you with?";
        
        String repromptSpeechText =
                "Knock knock, are you there? I'm waiting for a baby request!";
        
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("HelloWorld", speechText)
                .withReprompt(repromptSpeechText)
                .withShouldEndSession(false)
                .build();
    }
}
