package ua.com.foxminded.jee_task6;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		RaceDataLoader raceDataLoader = new RaceDataLoader(); 
		RaceReportBuilder reportBuilder = new RaceReportBuilder();
		ReportFormatter reportFormatter = new ReportFormatter();
		
		Map<String, List<String>> raceData;
		List<Racer> report;
		
		try {
			raceData = raceDataLoader.loadRaceData();
			report = reportBuilder.build(raceData);
			System.out.println(reportFormatter.format(report));
			
		} catch (IOException | InvalidRaceDataException e) {
			System.out.println(e.getMessage());
		}
	}
}
