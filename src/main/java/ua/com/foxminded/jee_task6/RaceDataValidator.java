package ua.com.foxminded.jee_task6;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class RaceDataValidator {
	
	void validate(Map<String, List<String>> input) throws InvalidRaceDataException{
		
		String message = "";
		
		if (!hasNoMissingElements(input)) {
			message = "Race data entirely or partially missing";
		} else if (!hasSameLineCount(input)) {
			message = "Race data consistancy violation. Line count mismatch";
		} else if (!matchExpectedPatterns(input)) {
			message = "Race data integrity violation. Line pattern mismatch";
		}
		
		if (!message.equals("")) {
			throw new InvalidRaceDataException(message);
		}
	}
	
	private boolean hasNoMissingElements(Map<String, List<String>> input) {
		if (input != null && 
				input.containsKey("startLog") && input.get("startLog") != null &&
				input.containsKey("endLog") && input.get("endLog") != null &&
				input.containsKey("driverInfo") && input.get("driverInfo") != null
			) {
				return true;
			} else {
				return false;
			}
	}
	
	
	private boolean hasSameLineCount(Map<String, List<String>> input) {
		boolean valid = 
				(input.get("startLog").size() == input.get("endLog").size()) &&
				(input.get("endLog").size() == input.get("driverInfo").size());
		
		return valid;
	}
	
	
	private boolean matchExpectedPatterns(Map<String, List<String>> input) {
		Pattern logLinePattern = 
				Pattern.compile("^[A-Z]{3}[\\d-]{10}_[0-2][\\d]:[0-5][\\d]:[0-5][\\d].\\d{3}$");
		Pattern infoLinePattern = 
				Pattern.compile("^[A-Z]{3}_[a-zA-Z-\\s]{3,}_[a-zA-Z\\s]{3,}$");
		
		if (
				!checkPatternMatch(input.get("startLog"), logLinePattern) ||
				!checkPatternMatch(input.get("endLog"), logLinePattern) ||
				!checkPatternMatch(input.get("driverInfo"), infoLinePattern)
			) {
			
			return false;
		}
		
		return true;
	}
	
	
	private boolean checkPatternMatch(List<String> input, Pattern linePattern) {
		for (String line : input) {
			if (!linePattern.matcher(line).matches()) {
				return false;
			}
		}
		return true;
	}

}
