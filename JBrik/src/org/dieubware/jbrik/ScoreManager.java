package org.dieubware.jbrik;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

public class ScoreManager extends Observable {
	
	
	public ScoreManager() {
		otherScore = new HashMap<String, Integer>();
	}
	
	
	protected int score;
	protected Map<String, Integer> otherScore;
	
	public void addScore(int score) {
		setScore(this.score + score);
	}
	public void setScore(int score) {
		this.score = score;
		setChanged();
		notifyObservers();
	}
	
	public void addOtherScore(String key, int score) {
		setOtherScore(key, this.getOtherScore(key) + score);
	}
	public void setOtherScore(String key, int score) {
		this.otherScore.put(key, score);
		setChanged();
		notifyObservers();
	}
	public int getScore() {
		return score;
	}
	public int getOtherScore(String key) {
		return otherScore.get(key);
	}
	
	

}
