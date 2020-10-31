package com.mx.crawler.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * DTO Para detalles de demanda
 * 
 * @author Noe Salazar Ramirez
 * 
 * @version 1.0
 *
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DemandDetailDTO {
	
	/**
	 * id
	 */
	private Integer id;
	
	/**
	 * Nombre
	 */
	private String name;
	
	/**
	 * Locacion
	 */
	private String location;
	
	/**
	 * versus
	 */
	private String versus;
	
	/**
	 * numero
	 */
	private String num;
	
	/**
	 * Actor
	 */
	private String actor;
	
	/**
	 * Demandado
	 */
	private String defendant;
	

}
