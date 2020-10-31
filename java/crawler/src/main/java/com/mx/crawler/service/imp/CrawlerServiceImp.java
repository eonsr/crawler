package com.mx.crawler.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.crawler.bo.CrawlerBO;
import com.mx.crawler.dto.ResponseDTO;
import com.mx.crawler.service.CrawlerService;

import lombok.extern.log4j.Log4j;

/**
 * Implementacion de {@link }
 * 
 * @author Noe Salazar Ramirez
 * 
 * @version 1.0
 *
 */
@Log4j
@Service
public class CrawlerServiceImp implements CrawlerService {
	
	/**
	 * {@link CrawlerBO}
	 */
	@Autowired
	private CrawlerBO crawlerBO;

	@Override
	public ResponseDTO findInfoByName(String name) {
		
		ResponseDTO responseDTO = crawlerBO.findInfoByName(name);
		
		log.info("Respuesta generada [" + responseDTO + "]");
		
		return responseDTO;
		
	}

}
