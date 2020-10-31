package com.mx.crawler.enums;

/**
 * Enum para la tabla tbl_demand_detail
 * 
 * @author Noe Salazar Ramirez
 * 
 * @version 1.0
 *
 */
public enum TableDemandDetailEnum {
	
	/**
	 * campo demand_id
	 */
	DEMAND_ID("demand_id"),
	
	/**
	 * campo demand_person_name
	 */
	DEMAND_PERSON_NAME("demand_person_name"),
	
	/**
	 * campo demand_location
	 */
	DEMAND_LOCATION("demand_location"),
	
	/**
	 * campo demand_versus
	 */
	DEMAND_VERSUS("demand_versus"),
	
	/**
	 * campo demand_num
	 */
	DEMAND_NUM("demand_num"),
	
	/**
	 * campo demand_actor
	 */
	DEMAND_ACTOR("demand_actor"),
	
	/**
	 * campo demand_defendant
	 */
	DEMAND_DEFENDANT("demand_defendant");
	
	/**
	 * Valor del nombre del campo de la tabla
	 */
	private String value;
	
	/**
	 * Constructor
	 * 
	 * @param value nombre del campo
	 */
	private TableDemandDetailEnum(String value) {
		this.value = value;
	}

	/**
	 * Devuelve el nombre del campo de la tabla
	 * 
	 * @return String
	 */
	public String getValue() {
		return value;
	}

}
