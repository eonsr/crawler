package com.mx.crawler.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mx.crawler.dto.DemandDetailDTO;
import com.mx.crawler.enums.TableDemandDetailEnum;

/**
 * Mapper para detalle de demanda {@link DemandDetailDTO}
 * 
 * @author Noe Salazar Ramirez
 * 
 * @version 1.0
 *
 */
public class DemandDetailMapper implements RowMapper<DemandDetailDTO> {

	@Override
	public DemandDetailDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		DemandDetailDTO demandDetailDTO = new DemandDetailDTO();
		
		String actor = rs.getString(TableDemandDetailEnum.DEMAND_ACTOR.getValue());
		String defendant = rs.getString(TableDemandDetailEnum.DEMAND_DEFENDANT.getValue());
		Integer id = rs.getInt(TableDemandDetailEnum.DEMAND_ID.getValue());
		String location = rs.getString(TableDemandDetailEnum.DEMAND_LOCATION.getValue());
		String name = rs.getString(TableDemandDetailEnum.DEMAND_PERSON_NAME.getValue());
		String num = rs.getString(TableDemandDetailEnum.DEMAND_NUM.getValue());
		String versus = rs.getString(TableDemandDetailEnum.DEMAND_VERSUS.getValue());
		
		demandDetailDTO.setActor(actor);
		demandDetailDTO.setDefendant(defendant);
		demandDetailDTO.setId(id);
		demandDetailDTO.setLocation(location);
		demandDetailDTO.setName(name);
		demandDetailDTO.setNum(num);
		demandDetailDTO.setVersus(versus);
		
		return demandDetailDTO;
		
	}
	
}
