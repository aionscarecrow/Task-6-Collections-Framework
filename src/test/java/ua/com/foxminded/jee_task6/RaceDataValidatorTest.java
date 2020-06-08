package ua.com.foxminded.jee_task6;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RaceDataValidatorTest {
	
	Map<String, List<String>> raceData = new HashMap<>();
	RaceDataValidator validator = new RaceDataValidator();

	@BeforeEach
	void setUp() throws Exception {
		raceData.put(
				"startLog",
				new LinkedList<>(Arrays.asList(
						"RGH2018-05-24_12:05:14.511",
						"SVM2018-05-24_12:18:37.735",
						"CLS2018-05-24_12:09:41.921"))
			);
		
		raceData.put(
				"endLog",
				new LinkedList<>(Arrays.asList(
						"RGH2018-05-24_12:06:27.441",
						"SVM2018-05-24_12:19:50.198",
						"CLS2018-05-24_12:10:54.750"))
			);
		
		raceData.put(
				"driverInfo",
				new LinkedList<>(Arrays.asList(
						"RGH_Romain Grosjean_HAAS FERRARI",
						"SVM_Stoffel Vandoorne_MCLAREN RENAULT",
						"CLS_Charles Leclerc_SAUBER FERRARI"))
			);
	}

	@Test
	@DisplayName("test with valid input")
	void testValidData() {
		assertDoesNotThrow(() -> validator.validate(raceData), "no exceptions expected");
	}
	
	@Test
	@DisplayName("test with line count mismatch")
	void testLineCountMismatch() {
		raceData.get("startLog").remove(0);
		assertThrows(InvalidRaceDataException.class, () -> validator.validate(raceData),
													"different line count");
	}
	
	@Test
	@DisplayName("test with line pattern mismatch")
	void testLinePatternMismatch() {
		
		raceData.get("driverInfo").set(0, "pattern disruption");
		assertThrows(InvalidRaceDataException.class, () -> validator.validate(raceData),
													"different line pattern");
	}
	
	@Test
	@DisplayName("test with missing elements")
	void testMissingElements() {
		raceData.put("startLog", null);
		assertThrows(InvalidRaceDataException.class, () -> validator.validate(raceData),
													"missing element");
	}
}
