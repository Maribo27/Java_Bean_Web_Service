package by.maribo.java_beans_handbook.tag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.jsp.JspWriter;
import java.io.IOException;

import static javax.servlet.jsp.tagext.Tag.SKIP_BODY;

class TagUtil {
	private final static Logger logger = LoggerFactory.getLogger(TableName.class);

	static int writeTag(JspWriter out, String tag, String message) throws TagWritingException {
		try {
			out.write(tag);
			return SKIP_BODY;
		} catch (IOException e) {
			logger.error(message, e);
			throw new TagWritingException(message, e);
		}
	}
}