package com.webdivas.sod.db.model;

public class Query {

    private Long id_query;
    private String nm_query;
    private String vl_query;
    private Integer id_connector;
    private Integer id_query_type;
    
    public Query() {
	
    }
    
    public Query(Long id_query, String nm_query, String vl_query, Integer id_connector, Integer id_query_type) {
	super();
	this.id_query = id_query;
	this.nm_query = nm_query;
	this.vl_query = vl_query;
	this.id_connector = id_connector;
	this.id_query_type = id_query_type;
    }

    public Long getId_query() {
        return id_query;
    }
    
    public void setId_query(Long id_query) {
        this.id_query = id_query;
    }
    
    public String getNm_query() {
        return nm_query;
    }
    
    public void setNm_query(String nm_query) {
        this.nm_query = nm_query;
    }
    
    public String getVl_query() {
        return vl_query;
    }
    
    public void setVl_query(String vl_query) {
        this.vl_query = vl_query;
    }
    
    public Integer getId_connector() {
        return id_connector;
    }
    
    public void setId_connector(Integer id_connector) {
        this.id_connector = id_connector;
    }

    public Integer getId_query_type() {
	return id_query_type;
    }

    public void setId_query_type(Integer id_query_type) {
	this.id_query_type = id_query_type;
    }
    
}
