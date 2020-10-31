package com.mx.crawler.template;

import static com.mx.crawler.constants.CrawlerConstants.MSG_ERR_INTERNAL;
import static com.mx.crawler.constants.CrawlerConstants.MSG_ERR_NAME;
import static com.mx.crawler.constants.CrawlerConstants.MSG_ERR_NO_DATA;

import java.util.List;

import com.mx.crawler.assembler.CrawlerAssembler;
import com.mx.crawler.dto.DemandDetailDTO;
import com.mx.crawler.dto.ResponseDTO;

import lombok.extern.log4j.Log4j;

/**
 * Clase abstracta con Template para proceso principal
 * 
 * @author Noe Salazar Ramirez
 * 
 * @version 1.0 
 *
 */
@Log4j
public abstract class CrawlerTemplate {

	/**
	 * Metodo para extraer informacion usando el nombre de entrada
	 * 
	 * @param name Nombre para procesar
	 * @return {@link ResponseDTO} Con datos del proceso
	 */
	public ResponseDTO findInfoByName(String name) {
		
		log.info("findInfoByName");
		
		ResponseDTO responseDTO = new ResponseDTO();
		
		if (isNameValid(name)) {
			
			List<String> elementStrList = executeWebScript(name);
			
			if (elementStrList != null && !elementStrList.isEmpty()) {
				
				List<DemandDetailDTO> processedList = processList(name, elementStrList);
				
				if (storeInformation(processedList)) {
					
					responseDTO = retrieveStoredData(name);
					
				} else {
					responseDTO = buildErrorMsg(MSG_ERR_INTERNAL);
					log.error(MSG_ERR_INTERNAL);
				}
				
			} else {
				responseDTO = buildErrorMsg(MSG_ERR_NO_DATA);
				log.error(MSG_ERR_NO_DATA);
			}
			
		} else {
			responseDTO = buildErrorMsg(MSG_ERR_NAME);
			log.error(MSG_ERR_NAME);
		}
		
		log.info("Respuesta del proceso [" + responseDTO + "]");
		
		return responseDTO;
		
	}

	/**
	 * Metodo para validar nombre
	 * 
	 * @param name String con nombre a valdiar
	 * @return true si es valdio, false de lo contrario
	 */
	public abstract boolean isNameValid(String name);


	/**
	 * Metodo para ejecutar el script web
	 * 
	 * @param name String con nombre a procesar
	 * @return Lista con datos encontrados
	 */
	public abstract List<String> executeWebScript(String name);
	
	/**
	 * Metodo para procesar la lista con los datos encotnrados
	 * 
	 * @param name Nombre a procesar
	 * @param elementStrList elementStrList Lista con datos encontrados
	 * @return Lista de datos procesados {@link CrawlerAssembler}
	 */
	public abstract List<DemandDetailDTO> processList(String name, List<String> elementStrList);

	/**
	 * Metodo para almacenar datos procesados en la base
	 * 
	 * @param processedList Lista de datos procesados {@link DemandDetailDTO}
	 * @return true si se guardo la infroamcion, false de lo contrario
	 */
	public abstract boolean storeInformation(List<DemandDetailDTO> processedList);

	/**
	 * Metodo para recuperar la informacion de base de datos
	 * 
	 * @param name Nombre a buscar
	 * @return {@link ResponseDTO} que contendra el resultado de la busqueda
	 */
	public abstract ResponseDTO retrieveStoredData(String name);

	/**
	 * Metodo para construir un {@link ResponseDTO} con un mensaje de error
	 * 
	 * @param msgErrNoData String con mensaje de error 
	 * @return {@link ResponseDTO} con mensaje de error integrado
	 */
	public abstract ResponseDTO buildErrorMsg(String errMsg);
	
}
