package com.webdivas.sod.db.model;

public class Connector {
	
	private Long id_connector;
	private String nm_connector;
	private String nm_class;
	private String nm_url;
	private String nm_user;
	private String nm_pass;
	
	public Connector() {
		
	}
	
	public Connector(Long id_connector, String nm_connector, String nm_class, String nm_url, String nm_user,
			String nm_pass) {
		super();
		this.id_connector = id_connector;
		this.nm_connector = nm_connector;
		this.nm_class = nm_class;
		this.nm_url = nm_url;
		this.nm_user = nm_user;
		this.nm_pass = nm_pass;
	}

	public Long getId_connector() {
		return id_connector;
	}

	public void setId_connector(Long id_connector) {
		this.id_connector = id_connector;
	}

	public String getNm_connector() {
		return nm_connector;
	}

	public void setNm_connector(String nm_connector) {
		this.nm_connector = nm_connector;
	}

	public String getNm_class() {
		return nm_class;
	}

	public void setNm_class(String nm_class) {
		this.nm_class = nm_class;
	}

	public String getNm_url() {
		return nm_url;
	}

	public void setNm_url(String nm_url) {
		this.nm_url = nm_url;
	}

	public String getNm_user() {
		return nm_user;
	}

	public void setNm_user(String nm_user) {
		this.nm_user = nm_user;
	}

	public String getNm_pass() {
		return nm_pass;
	}

	public void setNm_pass(String nm_pass) {
		this.nm_pass = nm_pass;
	}

	@Override
	public String toString() {
		return "Connector [id_connector=" + id_connector + ", nm_connector=" + nm_connector + ", nm_class=" + nm_class
				+ ", nm_url=" + nm_url + ", nm_user=" + nm_user + ", nm_pass=" + nm_pass + "]";
	}
	
	
}
