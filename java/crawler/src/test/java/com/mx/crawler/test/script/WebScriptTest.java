package com.mx.crawler.test.script;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mx.crawler.script.WebScript;

import lombok.extern.log4j.Log4j;

/**
 * Clase para probar {@link WebScript}
 * 
 * @author Noe Salzar Ramirez
 * 
 * @version 1.0
 *
 */
@Log4j
@SpringBootTest
public class WebScriptTest {
	
	/**
	 * {@link WebScript}
	 */
	@Autowired
	private WebScript webScript;

	/**
	 * Metodo para probar getPageInfo 
	 */
	@Test
	public void testGetPageInfo() {
		
		log.info("testGetPageInfo");
		
		List<String> elementStrList = null;
		
		String name = null;
		
		elementStrList = webScript.getPageInfo(name);
		
		log.info("elementStrList creada " + elementStrList);
		
		assertTrue(elementStrList == null, "se esperaba un null");
		
		name = "Juan Perez";
		
		elementStrList = webScript.getPageInfo(name);
		
		log.info("elementStrList creada " + elementStrList);
		
	}
	
}
