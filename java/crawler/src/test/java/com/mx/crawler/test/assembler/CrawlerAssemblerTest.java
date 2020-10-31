package com.mx.crawler.test.assembler;

import static com.mx.crawler.constants.CrawlerConstants.PREFIX_ACTOR;
import static com.mx.crawler.constants.CrawlerConstants.PREFIX_DEFENDANDT;
import static com.mx.crawler.constants.CrawlerConstants.PREFIX_NUMBER;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mx.crawler.assembler.CrawlerAssembler;
import com.mx.crawler.dto.DemandDetailDTO;

import lombok.extern.log4j.Log4j;

/**
 * Clase para probar {@link CrawlerAssembler}
 * 
 * @author Noe Salazar Ramirez
 * 
 * @version 1.0
 *
 */
@Log4j
@SpringBootTest
public class CrawlerAssemblerTest {

	/**
	 * {@link CrawlerAssembler}
	 */
	@Autowired
	private CrawlerAssembler crawlerAssembler;
	
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
		
		boolean resp = false;
		
		resp = crawlerAssembler.insertDemandDetail(demandDetailDTO);
		
		log.info("insertDemandDetail? [" + resp + "]");
		
		assertTrue(resp, "se esperaba el insert");
		
		List<DemandDetailDTO> demandList = crawlerAssembler.getDemandDetailListByName(name);
		
		log.info("Lista de demandas " + demandList);
		
		assertTrue(demandList != null && !demandList.isEmpty(), "se esperaban datos");
		
		resp = crawlerAssembler.deleteDemandDetailbyName(name);
		
		log.info("deleteDemandDetailbyName? [" + resp + "]");
		
		assertTrue(resp, "se esperaba el delete");
		
	}
	
	/**
	 * Metodo para probar getDetailFromStr
	 */
	@Test
	public void testGetDetailFromStr() {
		
		log.info("testGetDetailFromStr");
		
		DemandDetailDTO demandDetailDTO = null;
		
		String detailStr =  null;
		
		String name = "Juan Perez";
		
		demandDetailDTO = crawlerAssembler.getDetailFromStr(name, detailStr);
		
		log.info("Bean generado [" + demandDetailDTO + "]");
		
		assertTrue(demandDetailDTO == null, "se esperaba un null");
		
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
		
		detailStr = stringBuilder.toString();
		
		demandDetailDTO = crawlerAssembler.getDetailFromStr(name, detailStr);
		
		log.info("Bean generado [" + demandDetailDTO + "]");
		
		assertTrue(demandDetailDTO != null, "se esperaba el objeto con datos");
		
		assertFalse(demandDetailDTO.getActor().contains(PREFIX_ACTOR), "se esparaba false");
		assertFalse(demandDetailDTO.getNum().contains(PREFIX_NUMBER), "se esparaba false");
		assertFalse(demandDetailDTO.getDefendant().contains(PREFIX_DEFENDANDT), "se esparaba false");
		
	}
	
}
