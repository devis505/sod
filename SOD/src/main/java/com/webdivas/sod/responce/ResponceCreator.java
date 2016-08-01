package com.webdivas.sod.responce;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.webdivas.sod.db.model.Query;
import com.webdivas.sod.db.repository.ExecuteQueryRepository;
import com.webdivas.sod.db.repository.QueryRepository;
import com.webdivas.sod.request.json.JsonRequestParams;
import com.webdivas.sod.responce.core.IResponceCreator;
import com.webdivas.sod.responce.json.JsonResponceParams;

@Component
public class ResponceCreator implements IResponceCreator {

    @Autowired
    QueryRepository queryRepo;

    @Autowired
    ExecuteQueryRepository executeQueryRepo;

    private JsonRequestParams requestParams;
    private JsonResponceParams responceParams;

    @Override
    public void loadData() {

	List<Query> queryRes = queryRepo.getByName(requestParams.getQueryName());

	responceParams = new JsonResponceParams();

	if (queryRes.size() == 1) {

	    Query query = queryRes.get(0);
	    List<Map<String, Object>> listQueryItems = null;
	    List<Map<String, Object>> listQueryResult = null;
	    
	    listQueryItems = new ArrayList<>();
	    listQueryResult = new ArrayList<>();

	    int type = query.getId_query_type();
	    
	    switch (type) {
	    case 1:
		Map<String, Object> result = new HashMap<>();
		result.put("result", 0);
		result.put("kd_error", 0);
		result.put("msg", "");	
		
		listQueryItems = executeQueryRepo.getByParams(requestParams, query);
		listQueryResult.add(result);
		
		break;
	    case 2:
		listQueryResult = executeQueryRepo.create(requestParams, query);
		break;
	    case 3:
		listQueryResult = executeQueryRepo.update(requestParams, query);
		break;
	    case 4:
		listQueryResult = executeQueryRepo.delete(requestParams, query);
		break;
	    }

	    responceParams.setItems(listQueryItems);
	    responceParams.setResult(listQueryResult);
	    responceParams.setItemsCount(listQueryItems.size());

	} else {
	    responceParams.setItemsCount(0);
	}

    }

    @Override
    public String getResponceParamsToString() {
	Gson gson = new Gson();
	return gson.toJson(responceParams);
    }

    public ResponceCreator() {

    }

    @Override
    public JsonRequestParams getRequestParams() {
	return requestParams;
    }

    @Override
    public void setRequestParams(String requestParams) {
	Gson gson = new Gson();
	setRequestParams(gson.fromJson(requestParams, JsonRequestParams.class));
    }

    @Override
    public void setRequestParams(JsonRequestParams requestParams) {
	this.requestParams = requestParams;
    }

    public JsonResponceParams getResponceParams() {
	return responceParams;
    }

    public void setResponceParams(JsonResponceParams responceParams) {
	this.responceParams = responceParams;
    }

}
