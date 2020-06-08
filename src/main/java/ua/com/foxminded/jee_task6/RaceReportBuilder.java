package ua.com.foxminded.jee_task6;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class RaceReportBuilder {
	
	List<Racer> build (Map<String, List<String>> input ) throws InvalidRaceDataException {
		
		RaceDataValidator validator = new RaceDataValidator();
		validator.validate(input);
		
		Map<String, Racer> racerRecords = buildRacerRecords(input.get("driverInfo")); 
		racerRecords = populateLapTimes(racerRecords, input);
		
		List<Racer> raceReport = 
				racerRecords
				.entrySet()
				.stream()
				.map(entry -> entry.getValue())
				.sorted()
				.collect(Collectors.toList());
		
		return raceReport;
	}
	
	
	private Map<String, Racer> buildRacerRecords (List<String> driverInfo) {
		
		Map<String, Racer> output = new HashMap<>();
		
		driverInfo.forEach(s -> output.put(getAcronym(s), new Racer(s)));
		
		return output;
	}
	
	
	private Map<String, Racer> populateLapTimes 
					(Map<String, Racer> records, Map<String, List<String>> input) {
		
		Map<String, Racer> output = new HashMap<>(records);
		
		Map<String, String> startTimes = new HashMap<>();
		Map<String, String> endTimes = new HashMap<>();
		
		input.get("startLog").forEach(
				logLine -> startTimes.put(
						getAcronym(logLine), 
						getTimeSubstring(logLine)
					)
				);
		
		input.get("endLog").forEach(
				logLine -> endTimes.put(
						getAcronym(logLine), 
						getTimeSubstring(logLine)
					)
				);
		
		Map<String, LocalTime> lapTimes = calculateLapTimes(startTimes, endTimes);
		
		lapTimes.entrySet().forEach(
				entry -> output.get(entry.getKey())
				.setLapTime(entry.getValue())
			);
		
		
		return output;
	}
	
	
	private Map<String, LocalTime> calculateLapTimes
					(Map<String, String> startTimes, Map<String, String> endTimes) {
		
		Map<String, LocalTime> output = new HashMap<>();
		startTimes.entrySet().forEach(
					entry -> output.put(
								entry.getKey(),
								subtractTime(
										parseTime(entry.getValue()), 
										parseTime(endTimes.get(entry.getKey()))
								)
					)
		);
		
		return output;
	}

	
	private LocalTime parseTime(String input) {
		return LocalTime.parse(input, DateTimeFormatter.ofPattern("HH:mm:ss.SSS"));
	}
	
	
	private String getTimeSubstring(String input) {
		return input.substring(input.indexOf('_') + 1);
	}
	
	
	private LocalTime subtractTime(LocalTime startTime, LocalTime endTime) {
		
		LocalTime timeDifference = endTime.minusNanos(startTime.toNanoOfDay());
		return timeDifference;
	}
	
	
	private String getAcronym(String input) {
		return input.substring(0,3);
	}

}
