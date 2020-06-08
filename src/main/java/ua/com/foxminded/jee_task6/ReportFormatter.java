package ua.com.foxminded.jee_task6;

import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ReportFormatter {
	
	String format (List<Racer> input) {
		
		final int topPositions = 15;
		StringBuilder outputBuilder = new StringBuilder();
		
		List<String> formattedLines = formatLines(input);
		List<String> formattedReport = addSeparator(formattedLines, topPositions);
		
		formattedReport.forEach(s -> outputBuilder.append(s + "\n"));
		
		return outputBuilder.toString();
	}
	
	
	private List<String> formatLines(List<Racer> input) {
		List<String> output = new LinkedList<>();
		
		int nameColumnWidth = maxValueLengthOf(
				input.stream()
				.map(driver -> driver.getName())
				.collect(Collectors.toList())
			);
		
		int teamColumnWidth = maxValueLengthOf(
				input.stream()
				.map(driver -> driver.getTeam())
				.collect(Collectors.toList())
			);
		
		int gridPosition = 0;
		String nameColumnFormat = "%-" + nameColumnWidth + "s|";
		String teamColumnFormat = "%-" + teamColumnWidth + "s|";
		String timePattern = "m:ss:SSS";
		
		for (Racer driver : input) {
			StringBuilder lineBuilder = new StringBuilder();
			gridPosition++;
			
			lineBuilder.append(
					String.format("%2d.|", gridPosition) +
					String.format(nameColumnFormat, driver.getName()) +
					String.format(teamColumnFormat, driver.getTeam()) +
					driver.getLapTime().format(DateTimeFormatter.ofPattern(timePattern))
				);
			
			output.add(lineBuilder.toString());
		}
		
		return output;
	}
	
	
	private List<String> addSeparator(List<String> input, int separatorOffset) {
		List<String> output = new LinkedList<>(input);
		
		String separator = duplicateChar('-', maxValueLengthOf(output));
		
		if (input.size() >= separatorOffset) {
			output.add(separatorOffset, separator);
		} else {
			output.add(separator);
		}
		
		return output;
	}
	
	
	private int maxValueLengthOf(List<String> input) {
		Optional<String> longestString = (
				input.stream()
				.reduce((s1, s2) -> s1.length() > s2.length()? s1 : s2)
			);
		
		int result = 0;
		
		if (longestString.isPresent()) {
			result =  longestString.get().length();
		}
		
		return result;
	}
	
	
	private String duplicateChar(char inputChar, int length) {
		StringBuilder outputBuilder = new StringBuilder();
		
		Stream.generate(() -> inputChar)
		.limit(length)
		.forEach(c -> outputBuilder.append(c));
		
		return outputBuilder.toString();
	}

}
