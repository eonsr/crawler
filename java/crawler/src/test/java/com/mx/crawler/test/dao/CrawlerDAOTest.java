package com.mx.crawler.test.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mx.crawler.dao.CrawlerDAO;
import com.mx.crawler.dto.DemandDetailDTO;

import lombok.extern.log4j.Log4j;

/**
 * Clase para probar {@link CrawlerDAO}
 * 
 * @author Noe Salazar Ramirez
 * 
 * @version 1.0
 *
 */
@Log4j
@SpringBootTest
public class CrawlerDAOTest {

	/**
	 * {@link CrawlerDAO}
	 */
	@Autowired
	private CrawlerDAO crawlerDAO;
	
	/**
	 * Metodo para probar el ciclo:
	 * 
	 * 	<ol>
     *		<li>insert</li>
     *		<li>select</li>
     *		<li>delete</li>
     *	</ol>
	 * 
	 */
	@Test
	public void testAllFunctionsCycle() {
		
		log.info("testAllFunctionsCycle");
		
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
		
		int rowsAffected = 0;
		
		try {
			rowsAffected = crawlerDAO.insertDemandDetail(demandDetailDTO);
		} catch (Exception e) {
			log.error(e);
		}
		
		log.info("rowsAffected [" + rowsAffected + "]");
		
		assertTrue(rowsAffected > 0, "Se debio insertar el registro");
		
		List<DemandDetailDTO> demandList = null;
		
		try {
			demandList = crawlerDAO.getDemandDetailListByName(name);
		} catch (Exception e) {
			log.error(e);
		}
		
		log.info("Datos extraidos [" + demandList + "]");
		
		assertTrue(demandList != null && !demandList.isEmpty(), "Se deben tener datos");
		
		try {
			rowsAffected = crawlerDAO.deleteDemandDetailbyName(name);
		} catch (Exception e) {
			log.error(e);
		}
		
		log.info("rowsAffected [" + rowsAffected + "]");
		
		assertTrue(rowsAffected > 0, "Se debio borrar la informacion");
		
	}
	
}
