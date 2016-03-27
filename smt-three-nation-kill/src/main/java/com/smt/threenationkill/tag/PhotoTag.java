package com.smt.threenationkill.tag;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.web.servlet.support.RequestContextUtils;

public class PhotoTag extends MessageTag{

	private String id;
	
	public void setId(String id){
		this.id = id;
	}
	
	public void doTag() throws JspException, IOException{
		MessageSource resource = getMessageSource();
		String photoName = null;
		try{
			photoName = resource.getMessage(this.id, null, null);	
		}catch(NoSuchMessageException e){
			photoName = "default";
		}
		
		String photoTag = "<img src=images/" + photoName + ".jpg  width=100 height=100/>";
		getJspContext().getOut().print(photoTag);
		
	}
	
	
	
}
