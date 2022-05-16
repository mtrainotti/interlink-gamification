package eu.fbk.interlink.gamification.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import eu.trentorise.game.model.ChallengeConcept;
import eu.trentorise.game.model.Level;

import java.util.List;
import java.util.Objects;

@Document(collection = "games")
public class InterlinkGame {

	protected @Id String id;
	protected String name;
	protected String processId;
	protected List<String> tagList;
	protected List<InterlinkTask> taskList;
	protected List<Level> levelList;
	protected boolean active = true;
	protected List<ChallengeConcept> challengeList;

	public InterlinkGame() {
	}

	public InterlinkGame(String name, String processId, List<String> tagList, List<InterlinkTask> taskList, List<Level> levelList, List<ChallengeConcept> challengeList) {

		this.name = name;
		this.processId = processId;
		this.tagList = tagList;
		this.taskList = taskList;
		this.levelList = levelList;
		this.challengeList = challengeList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public List<String> getTagList() {
		return tagList;
	}

	public void setTagList(List<String> tags) {
		this.tagList = tags;
	}
	
	public void addTag(String tag) {
		this.tagList.add(tag);
	}
	
	public void removeTag(String tag) {
		this.tagList.remove(tag);
	}
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<Level> getLevelList() {
		return levelList;
	}

	public void setLevelList(List<Level> levelList) {
		this.levelList = levelList;
	}
	
	public void addLevel(Level level) {
		this.levelList.add(level);
	}
	
	public void removeLevel(String levelName) {
		Level toRemove = null;
		for (Level level : this.levelList) {
		    if (level.getName().equals(levelName)) {
		    	toRemove = level;
		    	break;
		    }
		}
		this.levelList.remove(toRemove);
		
	}
	
	

	public List<ChallengeConcept> getChallengeList() {
		return challengeList;
	}

	public void setChallengeList(List<ChallengeConcept> challengeList) {
		this.challengeList = challengeList;
	}

	public void addChallenge(ChallengeConcept challenge) {
		this.challengeList.add(challenge);
	}
	
	public void removeChallenge(String challengeId) {
		ChallengeConcept toRemove = null;
		for (ChallengeConcept challenge : this.challengeList) {
		    if (challenge.getId().equals(id)) {
		    	toRemove = challenge;
		    	break;
		    }
		}
		this.challengeList.remove(toRemove);
		
	}
	public List<InterlinkTask> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<InterlinkTask> taskList) {
		this.taskList = taskList;
	}
	
	public void addTask(InterlinkTask task) {
		this.taskList.add(task);
	}
	
	public void removeTask(String taskId) {
		InterlinkTask toRemove = null;
		for (InterlinkTask task : this.taskList) {
		    if (task.getId().equals(id)) {
		    	toRemove = task;
		    	break;
		    }
		}
		this.taskList.remove(toRemove);
		
	}

	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		if (!(o instanceof InterlinkGame))
			return false;
		InterlinkGame game = (InterlinkGame) o;
		return Objects.equals(this.id, game.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.name, this.processId, this.tagList);
	}

	@Override
	public String toString() {
		return "{" 
				+ "id=" + this.id 
				+ ", name='" + this.name + '\'' 
				+ ", processId='" + this.processId + '\'' 
				+", tagList=[" + this.tagList  
				+", taskList=[" + this.taskList  
				+", levelList=[" + this.levelList + 
				"]}";
	}

}
