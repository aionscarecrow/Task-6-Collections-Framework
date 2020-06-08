package ua.com.foxminded.jee_task6;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReportFormatterTest {
	
	List<Racer> report;
	ReportFormatter reportFormatter;
	
	@BeforeEach
	void setUp() {
		report = new LinkedList<>();
		Map<String, LocalTime> lapTimes = new HashMap<>();
		
		Map<String, String> driverInfo = new LinkedHashMap<>();
		driverInfo.put(
				"SVM", "SVM_Stoffel Vandoorne_MCLAREN RENAULT"
				);
		driverInfo.put(
				"CLS", "CLS_Charles Leclerc_SAUBER FERRARI"
				);
		driverInfo.put(
				"RGH", "RGH_Romain Grosjean_HAAS FERRARI"
				);
		
		lapTimes.put(
				"SVM", 
				LocalTime.parse("00:01:12.463", DateTimeFormatter.ofPattern("HH:mm:ss.SSS"))
				);
		lapTimes.put(
				"CLS", 
				LocalTime.parse("00:01:12.829", DateTimeFormatter.ofPattern("HH:mm:ss.SSS"))
				);
		lapTimes.put(
				"RGH", 
				LocalTime.parse("00:01:12.930", DateTimeFormatter.ofPattern("HH:mm:ss.SSS"))
				);
	
		for (Map.Entry<String, String> entry : driverInfo.entrySet()) {
			Racer racer = new Racer(entry.getValue());
			racer.setLapTime(lapTimes.get(entry.getKey()));
			report.add(racer);
		}
	}

	@Test
	@DisplayName("formatting report data")
	void testFormat() {
		reportFormatter = new ReportFormatter();
		String actual = reportFormatter.format(report);
		String expected = 
				" 1.|Stoffel Vandoorne|MCLAREN RENAULT|1:12:463\n" + 
				" 2.|Charles Leclerc  |SAUBER FERRARI |1:12:829\n" + 
				" 3.|Romain Grosjean  |HAAS FERRARI   |1:12:930\n" + 
				"----------------------------------------------\n";
		
		assertEquals(expected.hashCode(), actual.hashCode(), 
				"expected and actual string hashCode() should match");
	}

}
