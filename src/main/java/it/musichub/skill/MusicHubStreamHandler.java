package it.musichub.skill;

import java.util.UUID;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;

import it.musichub.skill.handlers.CancelandStopIntentHandler;
import it.musichub.skill.handlers.HelpIntentHandler;
import it.musichub.skill.handlers.LaunchRequestHandler;
import it.musichub.skill.handlers.SessionEndedRequestHandler;

public class MusicHubStreamHandler extends SkillStreamHandler {

    private static Skill getSkill() {
        return Skills.standard()
                .addRequestHandlers(
//                		new BabyFFactIntentHandler(),
                		new CancelandStopIntentHandler(),
                        new HelpIntentHandler(),
                        new LaunchRequestHandler(),
//                        new PriettiestPersonIntentHandler(),
//                        new SmallestPersonIntentHandler(),
//                        new SmartestPersonIntentHandler(),
                        new SessionEndedRequestHandler()
                		)
                // Add your skill id below
                //.withSkillId("")
                .build();
    }

    public MusicHubStreamHandler() {
        super(getSkill());
        instance = this;
        uniqueID = UUID.randomUUID();
        staticUniqueID = UUID.randomUUID();
    }
    
    
    
    
    private static MusicHubStreamHandler instance = null;
    
	public synchronized static MusicHubStreamHandler getInstance() {
		if (instance == null)
			instance = new MusicHubStreamHandler(); 
			
		return instance;
	}
    
    private UUID uniqueID = null;
    private static UUID staticUniqueID = null;

	public UUID getUniqueID() {
		return uniqueID;
	}
	
	public static UUID getStaticUniqueID() {
		return staticUniqueID;
	}
	

}
