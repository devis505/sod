package com.webdivas.sod.db.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.SqlParameter;

public class QueryParams {

    private List<SqlParameter> sqlParameter = new ArrayList<SqlParameter>();
    private Map<String, Object> paramValue = new HashMap<String, Object>();

    public void addInParam(String name, int type) {
	sqlParameter.add(new SqlParameter(name, type));
    }

    public void addParamValue(String key, Object value) {
	paramValue.put(key, value);
    }

    public void addParamValue(String key, Object value, int type) {
	addInParam(key, type);
	addParamValue(key, value);
    }

    public List<SqlParameter> getSqlParameter() {
	return sqlParameter;
    }

    public Map<String, Object> getParamsValue() {
	return paramValue;
    }

    public List<SqlParameter> getSqlParameters() {
	return sqlParameter;
    }

}
