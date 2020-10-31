package com.mx.crawler.test.bo;

import static com.mx.crawler.constants.CrawlerConstants.MSG_ERR_INTERNAL;
import static com.mx.crawler.constants.CrawlerConstants.MSG_ERR_NAME;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mx.crawler.assembler.CrawlerAssembler;
import com.mx.crawler.bo.CrawlerBO;
import com.mx.crawler.dto.DemandDetailDTO;
import com.mx.crawler.dto.ResponseDTO;

import lombok.extern.log4j.Log4j;

/**
 * Clase para probar {@link CrawlerBO}
 * 
 * @author Noe Salazar Ramirez
 * 
 * @version 1.0
 *
 */
@Log4j
@SpringBootTest
public class CrawlerBOTest {
	
	/**
	 * {@link CrawlerBO}
	 */
	@Autowired
	private CrawlerBO crawlerBO;
	
	/**
	 * {@link CrawlerAssembler}
	 */
	@Autowired
	private CrawlerAssembler crawlerAssembler;

	/**
	 * Metodo para probar isNameValid
	 */
	@Test
	public void testIsNameValid() {
		
		log.info("testIsNameValid");
		
		boolean resp = false;
		
		String name = null;
		
		resp = crawlerBO.isNameValid(name);
		
		log.info("isNameValid? [" + resp + "]");
		
		assertFalse(resp, "se esperaba false");
		
		
		
		name = "Juan";
		
		resp = crawlerBO.isNameValid(name);
		
		log.info("isNameValid? [" + resp + "]");
		
		assertTrue(resp, "se esperaba un true");
		
		
		
		name = "Juan Perez";
		
		resp = crawlerBO.isNameValid(name);
		
		log.info("isNameValid? [" + resp + "]");
		
		assertTrue(resp, "se esperaba un true");
		
		
		name = "Juan Perez Hernandez";
		
		resp = crawlerBO.isNameValid(name);
		
		log.info("isNameValid? [" + resp + "]");
		
		assertTrue(resp, "se esperaba un true");
		
		
		
		name = "Juan 123";
		
		resp = crawlerBO.isNameValid(name);
		
		log.info("isNameValid? [" + resp + "]");
		
		assertFalse(resp, "se esperaba un false");
		
	}
	
	/**
	 * metodo para probar buildErrorMsg
	 */
	@Test
	public void testBuildErrorMsg() {
		
		log.info("testBuildErrorMsg");
		
		ResponseDTO responseDTO = null;
		
		String errMsg = null;
		
		responseDTO = crawlerBO.buildErrorMsg(errMsg);
		
		log.info("Respuesta generada [" + responseDTO + "]");
		
		assertTrue(responseDTO != null, "el bean no debe ser null");
		
		assertTrue(responseDTO.getMsg().equals(MSG_ERR_INTERNAL), "el mensaje debe ser el interno");
		
		
		
		
		errMsg = MSG_ERR_NAME;
		
		responseDTO = crawlerBO.buildErrorMsg(errMsg);
		
		log.info("Respuesta generada [" + responseDTO + "]");
		
		assertTrue(responseDTO != null, "el bean no debe ser null");
		
		assertTrue(responseDTO.getMsg().equals(MSG_ERR_NAME), "el mensaje debe ser el de entrada");
		
	}
	
	/**
	 * Metodo para probar executeWebScript
	 */
	@Test
	public void testExecuteWebScript() {
		
		log.info("testExecuteWebScript");
		
		String name = "Juan Perez";
		
		List<String> elementStrList = crawlerBO.executeWebScript(name);
		
		log.debug("Lista extraida " + elementStrList);
		
		assertTrue(elementStrList != null && !elementStrList.isEmpty(), 
				"la lista no deberia ser nula ni vacia");
		
		name = null;
		
		elementStrList = crawlerBO.executeWebScript(name);
		
		log.debug("Lista extraida " + elementStrList);
		
		assertTrue(elementStrList == null, "la lista deberia ser nula");
		
	}
	
	/**
	 * Metodo para probar processList
	 */
	@Test
	public void testProcessList() {
		
		log.info("testProcessList");
		
		String name = null;
		
		List<String> elementStrList = null;
		
		List<DemandDetailDTO> processedList = null;
		
		processedList = crawlerBO.processList(name, elementStrList);
		
		log.info("Lista procesada [" + processedList + "]");
		
		assertTrue(processedList == null, "la lista deberia ser nula");
		
		
		
		name = "Juan Perez";
		
		elementStrList = new ArrayList<String>();
		
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append("Michoacán > Maravatío > Juzgado Primero Civil");
		stringBuilder.append("\n");
		stringBuilder.append("Juan Perez Gerardo - Juan Perez Calzada");
		stringBuilder.append("\n");
		stringBuilder.append("Número: 409/2006");
		stringBuilder.append("\n");
		stringBuilder.append("Actor: Juan Perez Gerardo");
		stringBuilder.append("\n");
		stringBuilder.append("Demandado: Juan Perez Calzada");
		
		log.info("\n" + stringBuilder);
		
		String detailStr =  null;
		
		detailStr = stringBuilder.toString();
		
		elementStrList.add(detailStr);
		
		log.info("Lista creada [" + elementStrList + "]");
		
		processedList = crawlerBO.processList(name, elementStrList);
		
		log.info("Lista procesada [" + processedList + "]");
		
		assertTrue(processedList != null && !processedList.isEmpty(), 
				"la lista no deberia ser nula ni estar vacia");
		
	}
	
	/**
	 * Metodo para probar storeInformation 
	 */
	@Test
	public void testStoreInformation() {
		
		log.info("testStoreInformation");
		
		boolean resp = false;
		
		List<DemandDetailDTO> processedList = null;
		
		resp = crawlerBO.storeInformation(processedList);
		
		log.info("storeInformation? [" + resp + "]");
		
		assertFalse(resp, "se esperaba un false");
		
		
		
		
		DemandDetailDTO demandDetailDTO = new DemandDetailDTO();
		
		String actor = "Juan Perez Gerardo";
		String defendant = "Juan Perez Calzada";
		String location = "Michoacán > Maravatío > Juzgado Primero Civil";
		String name = "Juan Perez";
		String num = "409/2006";
		String versus = "Juan Perez Gerardo - Juan Perez Calzada";
		
		demandDetailDTO.setActor(actor);
		demandDetailDTO.setDefendant(defendant);
		demandDetailDTO.setLocation(location);
		demandDetailDTO.setName(name);
		demandDetailDTO.setNum(num);
		demandDetailDTO.setVersus(versus);
		
		processedList = new ArrayList<DemandDetailDTO>();
		
		processedList.add(demandDetailDTO);
		
		resp = crawlerBO.storeInformation(processedList);
		
		log.info("storeInformation? [" + resp + "]");
		
		assertTrue(resp, "se esperaba un true");
		
		resp = crawlerAssembler.deleteDemandDetailbyName(name);
		
		log.info("deleteDemandDetailbyName? [" + resp + "]");
		
		assertTrue(resp, "se esperaba el delete");
		
	}
	
	/**
	 * Metodo para probar retrieveStoredData
	 */
	@Test
	public void testRetrieveStoredData() {
		
		log.info("testRetrieveStoredData");
		
		String name = null;
		
		ResponseDTO responseDTO = null;
		
		responseDTO = crawlerBO.retrieveStoredData(name);
		
		log.info("Respuesta generada [" + responseDTO + "]");
		
		assertTrue(responseDTO != null, "el bean no debe ser null");
		
		assertTrue(responseDTO.getMsg().equals(MSG_ERR_NAME), "el mensaje debe ser el de entrada");
		
		
		
		DemandDetailDTO demandDetailDTO = new DemandDetailDTO();
		
		String actor = "Juan Perez Gerardo";
		String defendant = "Juan Perez Calzada";
		String location = "Michoacán > Maravatío > Juzgado Primero Civil";
		name = "Juan Perez";
		String num = "409/2006";
		String versus = "Juan Perez Gerardo - Juan Perez Calzada";
		
		demandDetailDTO.setActor(actor);
		demandDetailDTO.setDefendant(defendant);
		demandDetailDTO.setLocation(location);
		demandDetailDTO.setName(name);
		demandDetailDTO.setNum(num);
		demandDetailDTO.setVersus(versus);
		
		boolean resp = false;
		
		resp = crawlerAssembler.insertDemandDetail(demandDetailDTO);
		
		log.info("insertDemandDetail? [" + resp + "]");
		
		assertTrue(resp, "se esperaba el insert");
		
		responseDTO = crawlerBO.retrieveStoredData(name);
		
		log.info("Respuesta generada [" + responseDTO + "]");
		
		assertTrue(responseDTO != null, "el bean no debe ser null");
		
		assertTrue(responseDTO.isStatus(), "se esperaba un true");
		
		assertTrue(responseDTO.getObject() != null, "se esperaba un true");
		
		resp = crawlerAssembler.deleteDemandDetailbyName(name);
		
		log.info("deleteDemandDetailbyName? [" + resp + "]");
		
		assertTrue(resp, "se esperaba el delete");
		
	}
	
	/**
	 * metodo para probar findInfoByName
	 */
	@Test
	public void testFindInfoByName() {
		
		log.info("testFindInfoByName");
		
		String name = null;
		
		ResponseDTO responseDTO = null;
		
		responseDTO = crawlerBO.findInfoByName(name);
		
		log.info("Respuesta generada [" + responseDTO + "]");
		
		assertTrue(responseDTO != null, "el bean no debe ser null");
		
		assertTrue(responseDTO.getMsg().equals(MSG_ERR_NAME), "el mensaje debe ser el de entrada");
		
		
		
		name = "Juan Perez";
		
		responseDTO = crawlerBO.findInfoByName(name);
		
		log.info("Respuesta generada [" + responseDTO + "]");
		
		assertTrue(responseDTO != null, "el bean no debe ser null");
		
		assertTrue(responseDTO.isStatus(), "se esperaba un true");
		
		assertTrue(responseDTO.getObject() != null, "se esperaba un true");
		
		boolean resp = crawlerAssembler.deleteDemandDetailbyName(name);
		
		log.info("deleteDemandDetailbyName? [" + resp + "]");
		
		assertTrue(resp, "se esperaba el delete");
		
	}
	
}
