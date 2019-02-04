CREATE TABLE FIX_FUNCTION (
	id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	function_name varchar(50) NOT NULL UNIQUE,
	description varchar(255),
	host_name varchar(100)
);

CREATE TABLE FIX_CLUSTER (
	id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	cluster_name varchar(50) NOT NULL UNIQUE,
	description varchar(255),
	port integer,
	fix_function_id int NOT NULL REFERENCES FIX_FUNCTION(id),
    CONSTRAINT UC_Cluster UNIQUE (cluster_name,fix_function_id)
);