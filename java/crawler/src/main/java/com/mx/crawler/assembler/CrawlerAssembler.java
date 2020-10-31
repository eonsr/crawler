package com.mx.crawler.assembler;

import static com.mx.crawler.constants.CrawlerConstants.LINE_BREAK;
import static com.mx.crawler.constants.CrawlerConstants.STR_VOID;
import static com.mx.crawler.constants.CrawlerConstants.PREFIX_ACTOR;
import static com.mx.crawler.constants.CrawlerConstants.PREFIX_DEFENDANDT;
import static com.mx.crawler.constants.CrawlerConstants.PREFIX_NUMBER;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mx.crawler.dao.CrawlerDAO;
import com.mx.crawler.dto.DemandDetailDTO;

import lombok.extern.log4j.Log4j;

/**
 * Assembler para reglas de negocio de Crawler
 * 
 * @author Noe Salazar Ramirez
 * 
 * @version 1.0
 *
 */
@Log4j
@Component
public class CrawlerAssembler {
	
	/**
	 * {@link CrawlerDAO}
	 */
	@Autowired
	private CrawlerDAO crawlerDAO;

	/**
	 * Metodo para procesar la cadena de entrada y generar un {@link DemandDetailDTO}
	 * 
	 * @param detailStr Cadena a procesar
	 * @return {@link DemandDetailDTO}
	 */
	public DemandDetailDTO getDetailFromStr(String name, String detailStr) {
		
		log.info("getDetailFromStr");
		
		DemandDetailDTO demandDetailDTO = null;
		
		if (detailStr != null && !detailStr.isEmpty() &&
				name != null && !name.isEmpty()) {
			
			String [] splitStr = detailStr.split(LINE_BREAK);
			
			log.debug("Split String [" + splitStr + "]");
			
			if(splitStr != null && splitStr.length == 5) {
				
				String location = splitStr[0];
				String versus = splitStr[1];
				String num = splitStr[2];
				String actor = splitStr[3];
				String defendant = splitStr[4];
				
				demandDetailDTO = new DemandDetailDTO(
						null, 
						name, 
						location, 
						versus, 
						num.replace(PREFIX_NUMBER, STR_VOID), 
						actor.replace(PREFIX_ACTOR, STR_VOID), 
						defendant.replace(PREFIX_DEFENDANDT, STR_VOID));
				
			} else {
				
				// Esta seccion puede ser mas dinamica
				log.info("El arreglo no es valido");
				
			}
			
		} else {
			log.info("La cadena a procesar no puede ser nula ni estar vacia");
		}
		
		return demandDetailDTO;
		
	}
	
	/**
	 * Metodo para traer la lista de detalles de demanda por nombre 
	 * 
	 * @param name nombre a consultar
	 * @return lista de {@link DemandDetailDTO}
	 */
	public List<DemandDetailDTO> getDemandDetailListByName(String name) {
		
		log.info("getDemandDetailListByName");
		
		List<DemandDetailDTO> demandList = null;
		
		if (name != null && !name.isEmpty()) {
			try {
				demandList = crawlerDAO.getDemandDetailListByName(name);
			} catch (Exception e) {
				log.error(e);
			}
		} else {
			log.error("El nombre no puede ser null ni estar vacio");
		}
		
		log.debug("Lista de demandas " + demandList);
		
		return demandList;
		
	}
	
	/**
	 * Metodo para insrtar un {@link DemandDetailDTO} en base de datos
	 * 
	 * @param demandDetailDTO {@link DemandDetailDTO} con datos a insertar
	 * @return true si se realizo la operacion, false de lo contrario
	 * @throws Exception Error en la ejecucion
	 */
	public boolean insertDemandDetail(DemandDetailDTO demandDetailDTO) {
		
		log.info("insertDemandDetail");
		
		boolean resp = false;
		
		int rowsAffected = 0;
		
		if (demandDetailDTO != null) {
			
			try {
				rowsAffected = crawlerDAO.insertDemandDetail(demandDetailDTO);
			} catch (Exception e) {
				log.error(e);
			}
			
			log.debug("rowsAffected [" + rowsAffected + "]");
			
			if (rowsAffected > 0) {
				resp = true;
				log.info("Registro insertado");
			} else {
				log.error("No hubo registros afectados");
			}
			
		} else {
			log.error("El bean no es valido");
		}
		
		log.info("insertDemandDetail? [" + resp + "]");
		
		return resp;
		
	}
	
	/**
	 * Metodo para borrar registros por nombre
	 * 
	 * @param name nombre para borrado
	 * @return true si se realizo la operacion, false de lo contrario
	 */
	public boolean deleteDemandDetailbyName(String name) {
		
		log.info("deleteDemandDetailbyName");
		
		boolean resp = false;
		
		int rowsAffected = 0;
		
		if (name != null && !name.isEmpty()) {
			
			try {
				rowsAffected = crawlerDAO.deleteDemandDetailbyName(name);
			} catch (Exception e) {
				log.error(e);
			}
			
			log.debug("rowsAffected [" + rowsAffected + "]");
			
			if (rowsAffected > 0) {
				resp = true;
				log.info("Registro borrado");
			} else {
				log.error("No hubo registros borrados");
			}
			
		} else {
			log.error("El nombre no puede ser null ni estar vacio");
		}
		
		log.info("deleteDemandDetailbyName? [" + resp + "]");
		
		return resp;
		
	}
	
}
