package com.smt.threenationkill.tag;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.support.RequestContextUtils;

public class GameResultTag extends MessageTag{

	private String name;
	
	public void setName(String name){
		this.name = name;
	}
	
	public void doTag() throws JspException, IOException{
//		ReloadableResourceBundleMessageSource resource = new ReloadableResourceBundleMessageSource();
//		resource.setBasename("messages");
//		resource.setDefaultEncoding("gb2312");
		MessageSource resource = getMessageSource();
		getJspContext().getOut().print(
				resource.getMessage(this.name, null, null));
	}
	
}
