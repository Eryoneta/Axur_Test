import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class HtmlAnalyzer {

	/**
	 * Given a URL, returns the deepest text content from the HTML tree
	 * */
	public static void main(String[] args) {
		if(args.length == 0) return; // No arguments = Nothing

		String url = "";
		String dummyHtmlContent = "";
		if(args.length >= 2 && args[0].equals("--test")) { // Two arguments = Debug stuff(HtmlAnalyzer --test "HTML \n content")
			dummyHtmlContent = args[1];
		} else {
			url = args[0]; // One argument = URL
		}
		
		HtmlElement document = new HtmlElement();
		try {
			final String htmlContent = (dummyHtmlContent.isEmpty()? getURLContent(url) : dummyHtmlContent);
//System.out.println(htmlContent);
			document = textAnalyzer(htmlContent, document);
		}
		catch (MalformedURLException error) {// The URL provided is malformed
			System.out.println(); // Say nothing
			return;
		}
		catch (URLConnectionException error) { // No connection
			System.out.println(error.toString());
			return;
		}
		catch (MalformedHTMLException error) { // The HTML syntax is malformed
			System.out.println(error.toString());
			return;
		}
		catch (IOException error) { // Some IO error
			System.out.println(); // Say nothing
			return;
		}

		System.out.println(document.getDeepestElement().getContent());
		
		// Extra: Print the HTML text back
		//System.out.println(document.getChildren().get(0).toString());
	}

	/**
	 * Returns text content from a URL
	 * */
	private static String getURLContent(String url) throws MalformedURLException, URLConnectionException {
		final StringBuilder response = new StringBuilder();
		try {
			final URL htmlURL = new URL(url);
			final URLConnection connection = htmlURL.openConnection();
			final BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			String inputLine = null;
			while((inputLine = input.readLine()) != null) {
				response.append(inputLine).append(System.getProperty("line.separator"));
			}
	
			input.close();
		} catch (IOException error) { // No connection
			throw new URLConnectionException();
		}
		return response.toString();
	}
	
	/**
	 * Analyzes a text and returns a tree of HTML elements
	 * */
	private static HtmlElement textAnalyzer(String text, HtmlElement document) throws MalformedHTMLException, IOException {
		final BufferedReader input = new BufferedReader(new StringReader(text));
		HtmlElement currentElement = document;
		
		String inputLine = null;
		do {
			inputLine = input.readLine();
			currentElement = lineAnalyzer(inputLine, currentElement);
		} while(inputLine != null);
		
		return currentElement;
	}

	/**
	 * Analyzes a line and returns a HTML element to represent it
	 * Can return a new text element, a element parent, or a new child element
	 * */
	private static HtmlElement lineAnalyzer(String line, HtmlElement element) throws MalformedHTMLException {
		// Note: Anything between "<" and ">" is considered to be valid HTML syntax
		
		if(line == null || line.isBlank()) return element; // Ignore empty lines
		line = line.trim(); // Ignore spaces before and after
		
		if(element.isText()) element = element.getParent(); // Text should not have children, only tags
		
		// End tag
		if(line.startsWith("</")) {
			if(line.endsWith(">")) {
				final String endTag = line.substring(2, line.length() - 1);
				if(endTag.equals(element.getTag())) {
					element = element.getParent();
					return element;
				} else throw new MalformedHTMLException(); // Mismatched tags!
			} else throw new MalformedHTMLException(); // Has a "</" but it's not a tag!
		}
		
		// Start tag
		if(line.startsWith("<")) {
			if(line.endsWith(">")) {
				final String startTag = line.substring(1, line.length() - 1);
				final HtmlElement newElement = new HtmlElement(startTag, element, element.getLevel() + 1, "");
				element.getChildren().add(newElement);
				return newElement;
			} else throw new MalformedHTMLException(); // Has a "<" but it's not a tag!
		}
		
		// Text
		final String text = line;
		final HtmlElement newElement = new HtmlElement("", element, element.getLevel() + 1, text);
		element.getChildren().add(newElement);
		return newElement;
	}

}
