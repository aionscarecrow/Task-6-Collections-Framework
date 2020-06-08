package ua.com.foxminded.jee_task6;

@SuppressWarnings("serial")
public class InvalidRaceDataException extends Exception {

	public InvalidRaceDataException(String message) {
		super(message);
	}

	public InvalidRaceDataException(String message, Throwable cause) {
		super(message, cause);
	}

}
