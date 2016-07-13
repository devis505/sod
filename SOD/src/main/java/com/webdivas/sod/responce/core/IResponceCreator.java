package com.webdivas.sod.responce.core;

import com.webdivas.sod.request.json.JsonRequestParams;

public interface IResponceCreator {
    
    public void loadData();
    
    public String getResponceParamsToString();
    
    public JsonRequestParams getRequestParams();
    
    public void setRequestParams(JsonRequestParams requestParams);
    
    public void setRequestParams(String requestParams);
}
