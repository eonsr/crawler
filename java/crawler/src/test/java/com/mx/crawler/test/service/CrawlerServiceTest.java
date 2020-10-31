package com.mx.crawler.test.service;

import static com.mx.crawler.constants.CrawlerConstants.MSG_ERR_NAME;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mx.crawler.assembler.CrawlerAssembler;
import com.mx.crawler.dto.ResponseDTO;
import com.mx.crawler.service.CrawlerService;

import lombok.extern.log4j.Log4j;

/**
 * Clase para probar {@link CrawlerService}
 * 
 * @author Noe Salazar Ramirez
 *
 */
@Log4j
@SpringBootTest
public class CrawlerServiceTest {
	
	/**
	 * {@link CrawlerService}
	 */
	@Autowired
	private CrawlerService crawlerService;
	
	/**
	 * {@link CrawlerAssembler}
	 */
	@Autowired
	private CrawlerAssembler crawlerAssembler;
	
	/**
	 * metodo para probar findInfoByName
	 */
	@Test
	public void testFindInfoByName() {
		
		log.info("testFindInfoByName");
		
		String name = null;
		
		ResponseDTO responseDTO = null;
		
		responseDTO = crawlerService.findInfoByName(name);
		
		log.info("Respuesta generada [" + responseDTO + "]");
		
		assertTrue(responseDTO != null, "el bean no debe ser null");
		
		assertTrue(responseDTO.getMsg().equals(MSG_ERR_NAME), "el mensaje debe ser el de entrada");
		
		
		
		name = "Juan Perez";
		
		responseDTO = crawlerService.findInfoByName(name);
		
		log.info("Respuesta generada [" + responseDTO + "]");
		
		assertTrue(responseDTO != null, "el bean no debe ser null");
		
		assertTrue(responseDTO.isStatus(), "se esperaba un true");
		
		assertTrue(responseDTO.getObject() != null, "se esperaba un true");
		
		boolean resp = crawlerAssembler.deleteDemandDetailbyName(name);
		
		log.info("deleteDemandDetailbyName? [" + resp + "]");
		
		assertTrue(resp, "se esperaba el delete");
		
	}

}
