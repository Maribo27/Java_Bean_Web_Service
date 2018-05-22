package by.maribo.java_beans_handbook.tag;


import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class FullNameTag extends TagSupport {
	private String name;
	private String lastName;

	public void setName(String name) {
		this.name = name;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public int doStartTag() throws JspException {
		if (name.isEmpty() || lastName.isEmpty()) {
			return SKIP_BODY;
		}
		JspWriter out = pageContext.getOut();

		String tag = name +
				" " +
				lastName;
		return TagUtil.writeTag(out, tag, "Cannot write user name to page");
	}
}