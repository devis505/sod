CREATE TABLE sod_connector (
  id_connector INTEGER PRIMARY KEY,
  nm_connector VARCHAR(500),
  nm_class 	   VARCHAR(1000),
  nm_url       VARCHAR(2000),
  nm_user      VARCHAR(500),
  nm_pass      VARCHAR(500)
);

CREATE TABLE sod_query_type (
  id_query_type INTEGER PRIMARY KEY,
  nm_query_type VARCHAR(200)
);

CREATE TABLE sod_query (
  id_query      INTEGER PRIMARY KEY,
  nm_query      VARCHAR(500),
  vl_query      VARCHAR(2000),
  id_connector  INTEGER,
  id_query_type INTEGER,
  FOREIGN KEY (id_connector) REFERENCES sod_connector(id_connector),
  FOREIGN KEY (id_query_type) REFERENCES sod_query_type(id_query_type)
);

