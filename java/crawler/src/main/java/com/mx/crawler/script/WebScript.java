package com.mx.crawler.script;

import static com.mx.crawler.constants.WebScriptConstants.ELEMENT_1_ID;
import static com.mx.crawler.constants.WebScriptConstants.ELEMENT_2_ID;
import static com.mx.crawler.constants.WebScriptConstants.ELEMENT_3_CLASS;
import static com.mx.crawler.constants.WebScriptConstants.ELEMENT_4_CLASS;
import static com.mx.crawler.constants.WebScriptConstants.ELEMENT_5_ID;
import static com.mx.crawler.constants.WebScriptConstants.ELEMENT_6_CLASS;
import static com.mx.crawler.constants.WebScriptConstants.THREAD_SLEEP;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

/**
 * Clase con script para extraccion de datos
 * 
 * @author Noe Salazar Ramirez
 * 
 * @version 1.0
 *
 */
@Log4j
@Component
public class WebScript {
	
	/**
	 * Constante con el paquete para el webdriver
	 */
	@Value("${web.driver.package}")
	public String WEBDRIVER_PACKAGE;
	
	/**
	 * Constante con la direccion del ejecutable 
	 */
	@Value("${web.driver.dir}")
	public String WEBDRIVER_DIR;
	
	/**
	 * Constante con direccion url
	 */
	@Value("${web.script.url}")
	public String WEB_PAGE;
	
	/**
	 * Constante con usuario
	 */
	@Value("${web.page.user}")
	public String WEB_PAGE_USER;
	
	
	/**
	 * Constante con password
	 */
	@Value("${web.page.password}")
	public String WEB_PAGE_PASSWORD;
	
	/**
	 * Metodo para obtener informacion de la pagina
	 * 
	 * @param name Nombre a procesar
	 * @return Lista con informacion extraida
	 */
	public List<String> getPageInfo(String name) {
		
		log.info("getPageInfo");
		
		List<String> elementStrList = null;
		
		if (isNameValid(name)) {
			
			System.setProperty(WEBDRIVER_PACKAGE, WEBDRIVER_DIR);
			
			WebDriver webDriver = new FirefoxDriver();
			
			webDriver.get(WEB_PAGE);
			
			webDriver.findElement(By.id(ELEMENT_1_ID)).sendKeys(WEB_PAGE_USER);
			
			webDriver.findElement(By.id(ELEMENT_2_ID)).sendKeys(WEB_PAGE_PASSWORD);
			
			webDriver.findElement(By.className(ELEMENT_3_CLASS)).click();
			
			try {
				Thread.sleep(THREAD_SLEEP);
			} catch (InterruptedException e) {
				log.error(e);
			}
			
			webDriver.findElement(By.className(ELEMENT_4_CLASS)).sendKeys(name);
			
			webDriver.findElement(By.id(ELEMENT_5_ID)).click();
			
			try {
				Thread.sleep(THREAD_SLEEP);
			} catch (InterruptedException e) {
				log.error(e);
			}
			
			List<WebElement> elementList = webDriver.findElements(By.className(ELEMENT_6_CLASS));
			
			elementStrList = generateStrList(elementList);
			
			try {
				Thread.sleep(THREAD_SLEEP);
			} catch (InterruptedException e) {
				log.error(e);
			}
			
			webDriver.close();
			
		} else {
			log.info("El nombre no es valido, no se continua con el proceso");
		}
		
		log.debug("Lista con datos generada " + elementStrList);
		
		return elementStrList;
		
	}
	
	/*
	 * ========================================================================
	 *                           Metodos privados
	 * ========================================================================
	 */
	
	/**
	 * Metodo para generar una validacion sencilla del nombre
	 * 
	 * @param name String con el nombre a procesar
	 * @return true si es valido, false de lo contrario
	 */
	private boolean isNameValid(String name) {
		
		log.info("isNameValid");
		
		boolean resp = false;
		
		if (name != null && !name.isEmpty()) {
			
			resp = true;
			
		} else {
			log.error("El nombre no es valido");
		}
		
		log.info("isNameValid? [" + resp + "]");
		
		return resp;
		
	}
	
	/**
	 * Metodo  para generar la lista de Strings con la lista de entrada de los
	 * webelements
	 * 
	 * @param elementList Lsita de webElements a procesar
	 * @return Lista de strings
	 */
	private List<String> generateStrList(List<WebElement> elementList) {
		
		log.info("generateStrList");
		
		List<String> elementStrList = null;
		
		if(elementList != null && !elementList.isEmpty()) {
			
			elementStrList = new ArrayList<String>();
			
			for (WebElement webElement : elementList) {
				log.debug("webElement" + webElement.getText());
				elementStrList.add(webElement.getText());
			}
			
		} else {
			log.error("La lista de elementos web es nula o esta vacia");
		}
		
		return elementStrList;
		
	}

}
