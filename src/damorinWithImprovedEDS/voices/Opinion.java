package damorinWithImprovedEDS.voices;

import ontology.Types.ACTIONS;

public class Opinion {
	
	private ACTIONS action;
	private double value;
	
	public ACTIONS getAction() {
		return action;
	}
	public void setAction(ACTIONS action) {
		this.action = action;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
}
