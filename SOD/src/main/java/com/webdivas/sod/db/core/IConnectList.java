package com.webdivas.sod.db.core;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public interface IConnectList {
    
    public DriverManagerDataSource getDataSource(String name);
    
}
