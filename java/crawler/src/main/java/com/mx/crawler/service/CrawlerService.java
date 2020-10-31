package com.mx.crawler.service;

import com.mx.crawler.dto.ResponseDTO;

/**
 * Servicio para extraer informacion
 * 
 * @author Noe Salazar Ramirez
 * 
 * @version 1.0
 *
 */
public interface CrawlerService {

	/**
	 * Metodo para extraer informacion usando el nombre de entrada
	 * 
	 * @param name Nombre para procesar
	 * @return {@link ResponseDTO} Con datos del proceso
	 */
	public ResponseDTO findInfoByName(String name);
	
}
