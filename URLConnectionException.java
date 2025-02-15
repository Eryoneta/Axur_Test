
@SuppressWarnings("serial")
public class URLConnectionException extends Exception {
	
	// Variables
	private static final String MESSAGE = "URL connection error";
	
	// Main
	URLConnectionException() {
		super(MESSAGE);
	}
	
	// Functions
	@Override public String toString() {
		return MESSAGE;
	}
	
}
