package com.mx.crawler.constants;

/**
 * Clase con constantes para el proyecto
 * 
 * @author Noe Salazar Ramirez
 * 
 * @version 1.0
 *
 */
public class CrawlerConstants {
	
	/**
	 * cadena vacia
	 */
	public static final String STR_VOID = "";
	
	/**
	 * Regex nombre
	 */
	public static final String REGEX_NAME = "^(([a-zA-z]*)(\\s*))*";
	
	/**
	 * Salto de linea
	 */
	public static final String LINE_BREAK = "\n";
	
	/*
	 * ========================================================================
	 *                                Prefijos
	 * ========================================================================
	 */

	/**
	 * Prefijo para numero
	 */
	public static final String PREFIX_NUMBER = "Número: ";
	
	/**
	 * Prefijo para actor
	 */
	public static final String PREFIX_ACTOR = "Actor: ";
	
	/**
	 * Prefijo para demandado
	 */
	public static final String PREFIX_DEFENDANDT = "Demandado: ";

	/*
	 * ========================================================================
	 *                                Mensajes
	 * ========================================================================
	 */
	
	/**
	 * Mensaje de proceso exitoso
	 */
	public static final String MSG_OK = "Proceso Exitoso";
	
	/**
	 * Mensaje de error para nombre
	 */
	public static final String MSG_ERR_NAME = "El nombre no es valido";
	
	/**
	 * Mensaje de error en recuperacion de datos
	 */
	public static final String MSG_ERR_NO_DATA = "No se recuperaron datos";
	
	/**
	 * Mensaje de error interno
	 */
	public static final String MSG_ERR_INTERNAL = "Error interno";
	
}
