/*
 * Script de tabla tbl_demand_detail (tabla de detalle de demanda)
 */
create table tbl_demand_detail(

	demand_id serial primary key,
    demand_person_name character(250) not null,
    demand_location character(250) not null,
    demand_versus character(250) not null,
	demand_num character(250) not null,
	demand_actor character(250) not null,
	demand_defendant character(250) not null
	
)