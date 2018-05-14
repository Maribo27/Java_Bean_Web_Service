package by.maribo.java_beans_handbook.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class Description extends TagSupport {
	private String description;

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int doStartTag() throws JspException {
		if (description == null) {
			return SKIP_BODY;
		}
		String tag = description.replaceAll("\\n", "<br>");
		JspWriter out = pageContext.getOut();
		return TagUtil.writeTag(out, tag, "Cannot write description to page");
	}
}