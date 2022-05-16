package eu.fbk.interlink.gamification.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.data.mongodb.core.mapping.Document;
import eu.trentorise.game.model.core.GameConcept;
import eu.trentorise.game.model.Action;
import eu.trentorise.game.model.ChallengeConcept;
import eu.trentorise.game.model.Level;
import eu.trentorise.game.model.PointConcept;
import eu.trentorise.game.model.core.DBRule;

@Document(collection = "templates")
public class InterlinkGameTemplate extends InterlinkGame{
	
	protected List<DBRule> rules;
	protected List<PointConcept> points;
	protected List<Action> actions;
	
	
	
	
	InterlinkGameTemplate() {
		if (tagList == null) 
			tagList = new ArrayList<String>();
		if (taskList == null) 
			taskList = new ArrayList<InterlinkTask>();
		if (levelList == null) 
			levelList = new ArrayList<Level>();
		if (challengeList == null) 
			challengeList = new ArrayList<ChallengeConcept>();
		if (rules == null) 
			rules = new ArrayList<DBRule>();
		if (points == null) 
			points = new ArrayList<PointConcept>();
	}

	InterlinkGameTemplate(String name, 
			String processId, 
			List<String> tagList, 
			List<InterlinkTask> taskList, 
			List<Level> levelList, 
			List<ChallengeConcept> challengeList,
			List<DBRule> rules, 
			List<PointConcept> points, 
			List<Action> actions) {
		
		this.name = name;
		this.processId = processId;
		this.tagList = tagList;
		this.taskList = taskList;
		this.levelList = levelList;
		this.challengeList = challengeList;
		this.rules = rules;
		this.points = points;
		this.actions = actions;
		
		if (tagList == null) 
			tagList = new ArrayList<String>();
		if (taskList == null) 
			taskList = new ArrayList<InterlinkTask>();
		if (levelList == null) 
			levelList = new ArrayList<Level>();
		if (challengeList == null) 
			challengeList = new ArrayList<ChallengeConcept>();
		if (rules == null) 
			rules = new ArrayList<DBRule>();
		if (points == null) 
			points = new ArrayList<PointConcept>();
		if (actions == null) 
			actions = new ArrayList<Action>();
		
	}


	public List<DBRule> getRules() {
		return rules;
	}





	public void setRules(List<DBRule> rules) {
		this.rules = rules;
	}





	public List<Action> getActions() {
		return actions;
	}

	public void setActions(List<Action> actions) {
		this.actions = actions;
	}

	public List<PointConcept> getPoints() {
		return points;
	}





	public void setPoints(List<PointConcept> points) {
		this.points = points;
	}


	
	public Set<GameConcept> pointToGameConceptSet() {
		Set<GameConcept> conceptSet = new  HashSet<GameConcept>();
		for(PointConcept point : this.points) {
			conceptSet.add(point);
		}
		return conceptSet;
	}

	public Set<String> actionToStringSet() {
		Set<String> set = new HashSet<String>();
		for (Action action : actions) {
			set.add(action.getId());
		}
		return set;
	}


	@Override
	public String toString() {
		return "{" 
				+ "id=" + this.getId() 
				+ ", name='" + this.getName() + '\'' 
				+ ", processId='" + this.getProcessId() + '\'' 
				+", tagList=[" + this.getTagList()  
				+", taskList=[" + this.getTaskList()  
				+", levelList=[" + this.getLevelList() + 
				"]}";
	}

}
