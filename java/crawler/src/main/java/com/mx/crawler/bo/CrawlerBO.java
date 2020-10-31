package com.mx.crawler.bo;

import static com.mx.crawler.constants.CrawlerConstants.MSG_ERR_INTERNAL;
import static com.mx.crawler.constants.CrawlerConstants.MSG_ERR_NAME;
import static com.mx.crawler.constants.CrawlerConstants.MSG_OK;
import static com.mx.crawler.constants.CrawlerConstants.REGEX_NAME;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mx.crawler.assembler.CrawlerAssembler;
import com.mx.crawler.dto.DemandDetailDTO;
import com.mx.crawler.dto.ResponseDTO;
import com.mx.crawler.script.WebScript;
import com.mx.crawler.template.CrawlerTemplate;

import lombok.extern.log4j.Log4j;

/**
 * Clase de Negocio para implementar {@link CrawlerTemplate}
 * 
 * @author Noe Salazar Ramirez
 * 
 * @version 1.0
 *
 */
@Log4j
@Component
public class CrawlerBO extends CrawlerTemplate {
	
	/**
	 * {@link WebScript}
	 */
	@Autowired
	private WebScript webScript;
	
	/**
	 * {@link CrawlerAssembler}
	 */
	@Autowired
	private CrawlerAssembler crawlerAssembler;

	@Override
	public boolean isNameValid(String name) {

		log.info("isNameValid");
		
		log.info("Nombre a validar [" + name + "]");
		
		boolean resp = false;

		if (name != null && !name.isEmpty()) {
			
			if (name.matches(REGEX_NAME)) {
				resp = true;
			} else {
				log.info("El nombre no tiene un formato valido");
			}
			
		} else {
			log.error("El nombre no puede ser nulo ni estar vacio");
		}
		
		log.info("isNameValid? [" + resp + "]");
		
		return resp;
		
	}

	@Override
	public List<String> executeWebScript(String name) {
		
		log.info("executeWebScript");
		
		List<String> elementStrList = webScript.getPageInfo(name);
		
		log.debug("Lista extraida " + elementStrList);
		
		return elementStrList;
		
	}

	@Override
	public List<DemandDetailDTO> processList(String name, List<String> elementStrList) {
		
		log.info("processList");
		
		List<DemandDetailDTO> processedList = null;
		
		if(elementStrList != null && !elementStrList.isEmpty() &&
				name != null && !name.isEmpty()) {
			
			processedList = new ArrayList<DemandDetailDTO>();
			
			for (String detailStr : elementStrList) {
				
				DemandDetailDTO demandDetailDTO = crawlerAssembler.getDetailFromStr(name, detailStr);
				
				if (demandDetailDTO != null) {
					processedList.add(demandDetailDTO);
				} else {
					log.error("Bean generado null para cadena [" + detailStr + "]");
				}
			}
			
		} else {
			log.error("parametros no validos");
		}
		
		log.debug("lista procesada " + processedList);
		
		return processedList;
		
	}

	@Override
	public boolean storeInformation(List<DemandDetailDTO> processedList) {
		
		log.info("storeInformation");
		
		boolean resp = false;
		
		int count = 0;
		
		if (processedList != null && !processedList.isEmpty()) {
			
			for (DemandDetailDTO demandDetailDTO : processedList) {
				
				resp = crawlerAssembler.insertDemandDetail(demandDetailDTO);
				
				if (resp) {
					count++;
				}
				
			}
			
			if (count > 0) {
				resp = true;
			} else {
				log.info("No se inserto ningu registro");
			}
			
		} else {
			log.error("Las lista debe contener datos");
		}
		
		
		
		log.info("storeInformation? [" + resp + "]");
		
		return resp;
		
	}

	@Override
	public ResponseDTO retrieveStoredData(String name) {
		
		log.info("retrieveStoredData");
		
		ResponseDTO responseDTO = new ResponseDTO();
		
		if (name != null && !name.isEmpty()) {
			
			List<DemandDetailDTO> processedList = 
					crawlerAssembler.getDemandDetailListByName(name);
			
			if (processedList != null && !processedList.isEmpty()) {
				
				responseDTO.setMsg(MSG_OK);
				responseDTO.setObject(processedList);
				responseDTO.setStatus(true);
				
			} else {
				responseDTO = buildErrorMsg(MSG_ERR_INTERNAL);
			}
			
			
		} else {
			responseDTO = buildErrorMsg(MSG_ERR_NAME);
		}
		
		log.info("Respuesta generada [" + responseDTO + "]");
		
		return responseDTO;
		
	}

	@Override
	public ResponseDTO buildErrorMsg(String errMsg) {
		
		log.info("buildErrorMsg");
		
		ResponseDTO responseDTO = new ResponseDTO();
		
		responseDTO.setObject(null);
		responseDTO.setStatus(false);
		
		if (errMsg != null && !errMsg.isEmpty()) {
			responseDTO.setMsg(errMsg);
		} else {
			log.error("El mensaje no puede ser ni null ni vacio, se coloca mensaje de error interno");
			responseDTO.setMsg(MSG_ERR_INTERNAL);
		}
		
		log.info("Respuesta (msj error) generada [" + responseDTO + "]");
		
		return responseDTO;
		
	}

}
