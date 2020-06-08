package ua.com.foxminded.jee_task6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class RaceDataLoader {
	
	Map<String, List<String>> loadRaceData() throws IOException {
		
		Map<String, List<String>> output = new HashMap<>();
		
		output.put("startLog", loadFileLines("start.log"));
		output.put("endLog", loadFileLines("end.log"));
		output.put("driverInfo", loadFileLines("abbreviations.txt"));
		
		return output;
	}
	
	
	private List<String> loadFileLines(String fileName) throws IOException {
		List<String> output;
		
		try (Stream<String> fileStream = Files.lines(Paths.get(fileName))) {
			output = fileStream.collect(Collectors.toList());
		}
		
		return output;
	}

}
