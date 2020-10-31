getDemandDetailListByName<[
	SELECT
		demand_id,
    	demand_person_name,
    	demand_location,
    	demand_versus,
		demand_num,
		demand_actor,
		demand_defendant
	FROM
		tbl_demand_detail
	WHERE
		demand_person_name = ?
	
]>
insertDemandDetail<[
	INSERT INTO 
		tbl_demand_detail (
			demand_person_name,
			demand_location,
			demand_versus,
			demand_num,
			demand_actor,
			demand_defendant
		) VALUES (?, ?, ?, ?, ?, ?)
]>
deleteDemandDetailbyName<[
	DELETE
	FROM tbl_demand_detail
	WHERE
		demand_person_name = ?
]>