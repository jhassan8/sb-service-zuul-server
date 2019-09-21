package com.app.zuul.filters;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PreTimeElapsedFilter extends ZuulFilter {
	
	private Logger log = LoggerFactory.getLogger(PreTimeElapsedFilter.class);

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		
		RequestContext rtx = RequestContext.getCurrentContext();
		HttpServletRequest request = rtx.getRequest();
		
		log.info(String.format("%s request ruted to %s", request.getRequestURL().toString()));
		
		Long startTime = System.currentTimeMillis();
		request.setAttribute("startTime", startTime);
		
		return null;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
