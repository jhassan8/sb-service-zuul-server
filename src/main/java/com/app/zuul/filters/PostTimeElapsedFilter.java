package com.app.zuul.filters;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PostTimeElapsedFilter extends ZuulFilter {
	
	private Logger log = LoggerFactory.getLogger(PostTimeElapsedFilter.class);

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		
		RequestContext rtx = RequestContext.getCurrentContext();
		HttpServletRequest request = rtx.getRequest();
		
		log.info("into post filter");
		
		Long startTime = (Long) request.getAttribute("startTime");
		Long endTime = System.currentTimeMillis();
		
		Long timeLapsed = endTime - startTime;
		
		log.info(String.format("Time lapsed in seconds %s", timeLapsed.doubleValue()/1000.00));
		log.info(String.format("Time lapsed in milliseconds %s", timeLapsed));
				
		return null;
	}

	@Override
	public String filterType() {
		return "post";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
