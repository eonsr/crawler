package com.mx.crawler.frontcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.crawler.dto.ResponseDTO;
import com.mx.crawler.service.CrawlerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.log4j.Log4j;

/**
 * FrontController para servicios del crawler
 * 
 * @author Noe Salazar Ramirez
 * 
 * @version 1.0
 *
 */
@Log4j
@RestController
@CrossOrigin
@Api(value = "Controller de crawler")
@RequestMapping("/api/v1/crawler")
public class CrawlerFrontController {

	/**
	 * {@link CrawlerService}
	 */
	@Autowired
	private CrawlerService crawlerService;
	
	/**
	 * Metodo para extraer informacion usando el nombre de entrada
	 * 
	 * @param name Nombre para procesar
	 * @return {@link ResponseDTO} Con datos del proceso
	 */
	@ApiOperation("Regresa el JSON/DTO con respuesta al procesamiento de busqueda de datos por nombre")
	@ApiResponses(value = { @ApiResponse(code = 100, message = "100 is the message"),
			@ApiResponse(code = 200, message = "Successful"), @ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 403, message = "Forbidden"), @ApiResponse(code = 404, message = "Not Found") })
	@GetMapping(value = "findInfoByName")
	public ResponseDTO findInfoByName(String name) {
		
		ResponseDTO responseDTO = crawlerService.findInfoByName(name);
		
		log.info("Respuesta generada [" + responseDTO + "]");
		
		return responseDTO;

	}
	
}
