package ua.com.foxminded.jee_task6;

import java.time.LocalTime;

public class Racer implements Comparable<Racer> {
	
	private final String acronym;
	private final String name;
	private final String team;
	private LocalTime lapTime;
	
	Racer(String driverInfoLine) {
		String[] infoValues = driverInfoLine.split("_");
		this.acronym = infoValues[0];
		this.name = infoValues[1];
		this.team = infoValues[2];
	}
	
	@Override
	public int compareTo(Racer otherRacer) {
		return this.lapTime.compareTo(otherRacer.getLapTime());
	}
	
	@Override
	public String toString() {
		return this.acronym + 
				" " + this.name +
				" " + this.team + 
				" " + this.lapTime;
	}

	String getName() {
		return name;
	}

	String getTeam() {
		return team;
	}

	String getAcronym() {
		return acronym;
	}

	LocalTime getLapTime() {
		return lapTime;
	}

	void setLapTime(LocalTime lapTime) throws UnsupportedOperationException {
		if (this.lapTime == null) {
			this.lapTime = lapTime;
		} else {
			throw new UnsupportedOperationException(
					"Once set, lapTime variable cannot be reassigned");
		}
		
	}
	
	
	
	
	
	
}
