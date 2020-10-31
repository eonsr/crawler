package com.mx.crawler.dao;

import java.util.List;

import com.mx.crawler.dto.DemandDetailDTO;

/**
 * Interfaz para acceso a datos del crawler
 * 
 * @author Noe Salazar Ramírez
 * 
 * @version 1.0
 *
 */
public interface CrawlerDAO {

	/**
	 * Metodo para traer la lista de detalles de demanda por nombre 
	 * 
	 * @param name nombre a consultar
	 * @return lista de {@link DemandDetailDTO}
	 * @throws Exception Error en la ejecucion
	 */
	public List<DemandDetailDTO> getDemandDetailListByName(String name) throws Exception;
	
	/**
	 * Metodo para insrtar un {@link DemandDetailDTO} en base de datos
	 * 
	 * @param demandDetailDTO {@link DemandDetailDTO} con datos a insertar
	 * @return numero de registros afectados
	 * @throws Exception Error en la ejecucion
	 */
	public int insertDemandDetail(DemandDetailDTO demandDetailDTO) throws Exception;
	
	/**
	 * Metodo para borrar registros por nombre
	 * 
	 * @param name nombre para borrado
	 * @return numero de registros afectados
	 * @throws Exception Error en la ejecucion
	 */
	public int deleteDemandDetailbyName(String name) throws Exception;
	
}
