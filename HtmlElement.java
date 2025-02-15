import java.util.ArrayList;
import java.util.List;

public class HtmlElement {
	
	// Variables
	private String tag;
	private HtmlElement parent;
	private List<HtmlElement> children;
	private int level;
	private String content;
	
	// Main
	public HtmlElement(String tag, HtmlElement parent, int level, String content) {
		this.tag = tag;
		this.parent = parent;
		this.level = level;
		this.content = content;
		this.children = new ArrayList<HtmlElement>();
	}
	public HtmlElement() {
		this.tag = "";
		this.parent = null;
		this.level = -1;
		this.content = "";
		this.children = new ArrayList<HtmlElement>();
	}
	
	// Functions: Getters
	public String getTag() {
		return tag;
	}
	public HtmlElement getParent() {
		return parent;
	}
	public int getLevel() {
		return level;
	}
	public String getContent() {
		return content;
	}
	public List<HtmlElement> getChildren() {
		return children;
	}

	// Functions
	public boolean isText() {
		return (getTag().isEmpty() && !getContent().isEmpty());
	}
	public boolean hasChildren() {
		return (!getChildren().isEmpty());
	}
	public HtmlElement getDeepestElement() {
		HtmlElement deepestElement = this;
		if(deepestElement.hasChildren()) {
			for(HtmlElement child : deepestElement.getChildren()) { // Check each child own deepestElement result
				final HtmlElement childDeepestElement = child.getDeepestElement();
				if(childDeepestElement.getLevel() > deepestElement.getLevel()) { // Consider ONLY the first deepest one
					deepestElement = childDeepestElement;
				}
			}
		}
		return deepestElement;
	}
	
	// Extra
	public String toString() {
		final String lineBreak = System.getProperty("line.separator");
		if(isText()) {
			final String content = ("\t".repeat(getLevel()) + getContent());
			return content;
		} else {
			final String startTag = ("\t".repeat(getLevel()) + "<" + getTag() + ">");
			final String endTag = ("\t".repeat(getLevel()) + "</" + getTag() + ">");
			final StringBuilder content = new StringBuilder();
			for(HtmlElement child : getChildren()) content.append(child.toString()).append(lineBreak);
			return startTag + lineBreak + content.toString() + endTag;
		}
	}
	
}
