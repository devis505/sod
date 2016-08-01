package com.webdivas.sod.db.core;

import java.util.List;

import com.webdivas.sod.db.model.Query;
import com.webdivas.sod.request.json.JsonRequestParams;

public interface IRepository {

    public List<?> create(JsonRequestParams params, Query query);
    
    public List<?> update(JsonRequestParams params, Query query);
    
    public List<?> delete(JsonRequestParams params, Query query);
    
    public List<?> getByName(String name);	
    
    public List<?> getByParams(JsonRequestParams params, Query query);
    
    public List<?> getAll();
} 
