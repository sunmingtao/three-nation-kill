package com.smt.threenationkill.tag;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.support.RequestContextUtils;

public class MessageTag extends SimpleTagSupport{
	protected MessageSource getMessageSource() {
		ServletRequest servletRquest = ((PageContext) getJspContext()).getRequest();
		ApplicationContext ctx = RequestContextUtils.getWebApplicationContext(servletRquest);
		return (MessageSource)ctx.getBean("messageSource");
	}
}
