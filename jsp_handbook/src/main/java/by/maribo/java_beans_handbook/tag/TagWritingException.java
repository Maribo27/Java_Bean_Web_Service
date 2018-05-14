package by.maribo.java_beans_handbook.tag;

import javax.servlet.jsp.JspException;
import java.io.IOException;

class TagWritingException extends JspException {

	TagWritingException(String message, IOException e) {
		super(message, e);
	}
}
