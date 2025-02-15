
@SuppressWarnings("serial")
public class MalformedHTMLException extends Exception {
	
	// Variables
	private static final String MESSAGE = "malformed HTML";
	
	// Main
	MalformedHTMLException() {
		super(MESSAGE);
	}
	
	// Functions
	@Override public String toString() {
		return MESSAGE;
	}
	
}
