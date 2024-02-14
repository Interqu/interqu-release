package com.interqu.preflight;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@Component
public class PreFlightRequestFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(PreFlightRequestFilter.class);
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
    	try {
    	    // Set the necessary CORS headers
    	    response.setHeader("Access-Control-Allow-Origin", "*");
    	    response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
    	    response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, X-Requested-With, Accept");
    	    response.setHeader("Access-Control-Max-Age", "3600");
    	    
    		if(request.getMethod().equalsIgnoreCase("OPTIONS")) {
    	        // Pre-flight request, set the status code to 200 OK
    	        response.setStatus(HttpServletResponse.SC_OK);
    		}else {
    			// Proceed with the filter chain
    	 		chain.doFilter(request, response);
    		}

    	}catch(Exception e) {
    		logger.info("doFilterInternal(): could not analyze preflight condition");
    		logger.debug(e.getLocalizedMessage());
    		//send forbidden error
    		response.sendError(500);
    	}
    }
}
