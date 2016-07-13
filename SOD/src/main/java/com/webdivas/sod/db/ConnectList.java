package com.webdivas.sod.db;

import java.util.Map;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.webdivas.sod.db.core.IConnectList;

public class ConnectList implements IConnectList {

    private Map<Object, Object> connects;
    
    @Override
    public DriverManagerDataSource getDataSource(String name) {
	
	DriverManagerDataSource connect = (DriverManagerDataSource)connects.get(name);
	
	return connect;
    }
    
    public Map<Object, Object> getConnects() {
	return connects;
    }

    public void setConnects(Map<Object, Object> connects) {
        this.connects = connects;
    }
    
    
}
