package ua.com.foxminded.jee_task6;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RaceReportBuilderTest {
	
	Map<String, List<String>> raceData = new HashMap<>();
	RaceReportBuilder reportBuilder = new RaceReportBuilder();
	
	@BeforeEach
	void setUp() {
		raceData.put(
				"startLog",
				Arrays.asList(
						"RGH2018-05-24_12:05:14.511",
						"SVM2018-05-24_12:18:37.735",
						"CLS2018-05-24_12:09:41.921")
			);
		
		raceData.put(
				"endLog",
				Arrays.asList(
						"RGH2018-05-24_12:06:27.441",
						"SVM2018-05-24_12:19:50.198",
						"CLS2018-05-24_12:10:54.750")
			);
		
		raceData.put(
				"driverInfo",
				Arrays.asList(
						"RGH_Romain Grosjean_HAAS FERRARI",
						"SVM_Stoffel Vandoorne_MCLAREN RENAULT",
						"CLS_Charles Leclerc_SAUBER FERRARI")
			);
	}

	@Test
	@DisplayName("building report from loaded data")
	void testBuildReport() throws Exception {
		List<Racer> result = reportBuilder.build(raceData);
		
		String expected = "["
				+ "SVM Stoffel Vandoorne MCLAREN RENAULT 00:01:12.463, "
				+ "CLS Charles Leclerc SAUBER FERRARI 00:01:12.829, "
				+ "RGH Romain Grosjean HAAS FERRARI 00:01:12.930"
				+ "]";
		String actual = result.toString();
		
		assertEquals(expected.hashCode(), actual.hashCode(), 
				"expected and actual hashCode() results should match");
	}

}
