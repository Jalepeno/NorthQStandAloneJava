package com.northqstandalone.maven.services;

import java.io.IOException;

import javax.xml.ws.http.HTTPException;

import com.northqstandalone.maven.NorthQ.AppTest;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TokenServiceTest extends TestCase {
	
	private NorthQRestfulUtils utils; 
	private TokenService service;
	
    public TokenServiceTest() {
    	utils = new NorthQRestfulUtils();
    	service = new TokenService();
    	
    	service.setNorthQService(utils);
    }
    
    public void testGetToken()
    {
    	String token = null;
    	
    	try {
			token = service.get();
			assertNotNull(token);
		} catch (HTTPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	// Assert that token is not null
    	assertNotNull(token);
    }
}
