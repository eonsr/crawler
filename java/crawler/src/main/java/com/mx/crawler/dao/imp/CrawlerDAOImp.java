package com.mx.crawler.dao.imp;

import static com.mx.crawler.constants.QueryConstants.DELETE_DEMAND_DETAIL_BY_NAME;
import static com.mx.crawler.constants.QueryConstants.GET_DEMAND_DETAIL_BY_NAME;
import static com.mx.crawler.constants.QueryConstants.INSERT_DEMAND_DETAIL;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mx.crawler.dao.CrawlerDAO;
import com.mx.crawler.dto.DemandDetailDTO;
import com.mx.crawler.mapper.DemandDetailMapper;
import com.mx.crawler.utils.SQLUtil;

import lombok.extern.log4j.Log4j;

/**
 * Clase qeu implementa {@link CrawlerDAO}
 * 
 * @author Noe Salazar Ramirez
 * 
 * @version 1.0
 *
 */
@Log4j
@Repository
public class CrawlerDAOImp implements CrawlerDAO {
	
	/**
	 * {@link JdbcTemplate}
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * Utileria para lectura de archivos *.sql
	 */
	@Autowired
	private SQLUtil sqlUtil;
	
	/**
	 * Archivo SQL
	 */
	@Value("${sql.file.path}")
	private String sqlFile;
	
	/**
	 * String para sql
	 */
	private String sql;

	@Override
	public List<DemandDetailDTO> getDemandDetailListByName(String name) throws Exception {
		
		log.info("getDemandDetailListByName");
		
		sql = sqlUtil.getQuery(GET_DEMAND_DETAIL_BY_NAME, sqlFile);
		
		Object[] params = {name};
		
		List<DemandDetailDTO> demandList = jdbcTemplate.query(sql, params, new DemandDetailMapper());
		
		log.debug("Lista de demandas " + demandList);
		
		return demandList;
		
	}

	@Override
	public int insertDemandDetail(DemandDetailDTO demandDetailDTO) throws Exception {
		
		log.info("insertDemandDetail");
		
		sql = sqlUtil.getQuery(INSERT_DEMAND_DETAIL, sqlFile);
		
		Object[] params = {
				demandDetailDTO.getName(),
				demandDetailDTO.getLocation(),
				demandDetailDTO.getVersus(),
				demandDetailDTO.getNum(),
				demandDetailDTO.getActor(),
				demandDetailDTO.getDefendant()
		};
		
		int rowsAffected = jdbcTemplate.update(sql, params);
		
		log.debug("rowsAffected [" + rowsAffected + "]");
		
		return rowsAffected;
		
	}

	@Override
	public int deleteDemandDetailbyName(String name) throws Exception {
		
		log.info("deleteDemandDetailbyName");
		
		sql = sqlUtil.getQuery(DELETE_DEMAND_DETAIL_BY_NAME, sqlFile);
		
		Object[] params = {name};
		
		int rowsAffected = jdbcTemplate.update(sql, params);
		
		log.debug("rowsAffected [" + rowsAffected + "]");
		
		return rowsAffected;
		
	}

}
