package eu.fbk.interlink.gamification.component;




import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import eu.fbk.interlink.gamification.domain.InterlinkGame;
import eu.fbk.interlink.gamification.domain.InterlinkGameTemplate;
import eu.fbk.interlink.gamification.domain.InterlinkPlayer;
import eu.fbk.interlink.gamification.sec.IdentityLookupComponent;
import eu.trentorise.game.core.GameContext;
import eu.trentorise.game.managers.NotificationManager;
import eu.trentorise.game.model.Game;
import eu.trentorise.game.model.Level;
import eu.trentorise.game.model.PlayerState;
import eu.trentorise.game.model.PointConcept;
import eu.trentorise.game.model.core.DBRule;
import eu.trentorise.game.model.core.GameConcept;
import eu.trentorise.game.model.core.Rule;
import eu.trentorise.game.repo.RuleRepo;
import eu.trentorise.game.services.GameService;
import eu.trentorise.game.services.PlayerService;
import eu.trentorise.game.services.Workflow;




@Component
public class GamificationEngineFacadeComponent  {
	
	Logger logger = LoggerFactory.getLogger(GamificationEngineFacadeComponent.class);
	

	
	@Autowired
    Workflow workflow;

    @Autowired
    PlayerService playerSrv;

    
    @Autowired
    GameContext gameContext; 

    @Autowired
    GameService gameSrv;

    @Autowired
    NotificationManager notificationSrv;
    
    @Autowired
    IdentityLookupComponent identityLookup;
    
    @Autowired
    RuleRepo ruleRepo;

    
    public GamificationEngineFacadeComponent() {
    }
    
    
    /**
     * Create a game from template
     * @param template
     * @param processId
     */
    public void instanceAndConfigureGame(InterlinkGameTemplate template, String processId) {
    	String user = identityLookup.getName();
        
    	// create and save the game
    	Game game = new Game();
        game.setId(processId);
        game.setName(template.getName());
        game.setDomain(identityLookup.getDomain());
        game.setOwner(identityLookup.getName());
        game.setLevels(template.getLevelList());
        game.setActions(template.actionToStringSet());
        game.setConcepts(template.pointToGameConceptSet());
        gameSrv.saveGameDefinition(game);
        
        // add the game rules
        for (Rule rule : template.getRules()) {
        	rule.setGameId(processId);
        	String ruleUrl = gameSrv.addRule(rule);
        	
        }
       
    }
    
    /**
     * Trigger the user action in the gamification engine 
     * @param gameId
     * @param actionId
     * @param playerId
     * @param data
     */
    
    public void triggerAction(String gameId, String action,  InterlinkPlayer player) {
		
		Map<String, Object> data = new HashMap<String, Object>();
		
		data.put("development", Double.valueOf(2.0));
		//data.put("management", (double)player.getManagement());
		//data.put("exploitation", (double)player.getExploitation());
	

		
		gameId = decodePathVariable(gameId);
		action = decodePathVariable(action);

      
            
                //workflow.apply(gameId, action, player.getId(), data, null);
        	workflow.apply("test_20", "update_player_points", "trainotti", data, null);
        
		
	}
    
    public static String decodePathVariable(String variable) {
        try {
            variable = URLDecoder.decode(variable, "UTF-8");
            return variable;
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(String.format("%s is not UTF-8 encoded", variable));
        }
    }
    


    public PlayerState getPlayerState(String gameId, String playerId) {  
        return this.playerSrv.loadState(gameId, playerId, false, false, false);
    }

    public void addPlayer(String gameId, InterlinkPlayer player) {
    	//
    }

	
	
//	@Override
//	public PlayerStateDTO getPlayerState(String gameId, String playerId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	@Override
//	public Set<String> getGamePlayers(String gameId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	@Override
//	public List<GameStatistics> readGameStatistics(String gameId, DateTime timestamp, String pcName) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
