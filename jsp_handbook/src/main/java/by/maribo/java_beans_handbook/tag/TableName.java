package by.maribo.java_beans_handbook.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class TableName extends TagSupport {
	private String type;

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public int doStartTag() throws JspException {
		if (type == null) {
			return SKIP_BODY;
		}

		String tag = TableLocale.getName(type);
		JspWriter out = pageContext.getOut();
		return TagUtil.writeTag(out, tag, "Cannot write table data to page");
	}
}